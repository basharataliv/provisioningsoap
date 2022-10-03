package com.provisioning.gateway.service;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.provisioning.gateway.config.PropertyFileReading;
import com.provisioning.gateway.config.TibcoAddSoapConfig;
import com.provisioning.gateway.config.TibcoRemoveSoapConfig;
import com.provisioning.gateway.dao.SubscribersDao;
import com.provisioning.gateway.eda.ProvService;
import com.provisioning.gateway.kafka.KafkaSender;
import com.provisioning.gateway.model.Chargeprocess;
import com.provisioning.gateway.model.Subscriber;
import com.provisioning.gateway.payload.SubscriptionRequest;
//import com.provisioning.gateway.repository.SubscriberRepository;
import com.provisioning.gateway.repository.MainRepository;
import com.provisioning.gateway.repository.SubscriberRepositoryFactory;
import com.provisioning.gateway.soap.service.UpdateService;
import com.provisioning.gateway.tabs.VipCallWarid;
import com.provisioning.gateway.utils.DateTimeUtils;
import com.tibco.add.AddProductIn;
import com.tibco.add.AddProductOut;

import wsdl.remove.business.service.RemoveProductIn;
import wsdl.remove.business.service.RemoveProductOut;

@Service
@Transactional
public class SubscriberService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MainRepository repo;
	@Autowired
	private SubscriberRepositoryFactory factory;
	@Autowired
	private TibcoRemoveSoapConfig removeclient;
	@Autowired
	private TibcoAddSoapConfig addClient;
	@Autowired
	private UpdateService callBackService;
	@Autowired
	private ProvService eda;
	@Autowired
	private KafkaSender smsSender;
	@Autowired
	private SubscribersDao subDao;

	private int pendingForCallBackAddStatus = Integer
			.parseInt(PropertyFileReading.getProperty("tibco.soap.pending.for.callback.add.status"));
	private int pendingForCallBackRemoveStatus = Integer
			.parseInt(PropertyFileReading.getProperty("tibco.soap.pending.for.callback.remove.status"));;
	private String sub = PropertyFileReading.getProperty("tibco.soap.sub.parameter");
	private String tibcoUsername = PropertyFileReading.getProperty("tibco.soap.client.username");
	private String tibcoPassword = PropertyFileReading.getProperty("tibco.soap.client.password");
	private String unsub = PropertyFileReading.getProperty("tibco.soap.unsub.parameter");
	private int pendingForCallBackFailedAddStatus = Integer
			.parseInt(PropertyFileReading.getProperty("tibco.soap.pending.for.callback.add.fail.status"));
	private int pendingForCallBackFailedRemoveStatus = Integer
			.parseInt(PropertyFileReading.getProperty("tibco.soap.pending.for.callback.remove.fail.status"));

	public int postDataOnAddTibco(AddProductIn request) {
		AddProductOut res = addClient.addClient(addClient.marshallerAdd()).getAddResponse(addClient.marshallerAdd(),
				request);
		repo = factory.createRepository(request.getMSISDN());
		logger.info("MSISDN::"+request.getMSISDN()+"transectionId" +request.getTransactionID()+"::: response status "+res.getStatus()+"::: response message "+res.getMessage());
		
		if (res.getStatus().equals("OK")) {

			return repo.updateTibcoVipCallsubscriberStatus(request.getMSISDN().substring(1),
					pendingForCallBackAddStatus);
			
		} else {
			logger.info("tibco failed sub");
			return repo.updateTibcoVipCallsubscriberStatus(request.getMSISDN().substring(1),
					pendingForCallBackFailedAddStatus);
		}

	}

	public void save(Subscriber subscriber) throws Exception {
		repo = factory.createRepository(subscriber.getA_party());
		factory.save(subscriber);
	}

	public int addsubscriber(Subscriber subscriber) throws Exception {

		try {
			AddProductIn inTibco = new AddProductIn();

			String sub_type_resp = getSubType(subscriber.getA_party());
			if (sub_type_resp != null) {
				sub_type_resp = sub_type_resp.replace("[", "");
				sub_type_resp = sub_type_resp.replace("]", "");
				String sub_type_arr[] = sub_type_resp.split(",");
				logger.info(subscriber.getA_party(),sub_type_resp+":::: channel "+subscriber.getChannel());
				int sub_type = Integer.parseInt(sub_type_arr[0].replace("\"", ""));
				if (sub_type != -1) {
					subscriber.setOpr_type(sub_type);
					checkuserAlreadyExistAndSave(subscriber);
					if (sub_type == 3) {
						logger.info("warid postpaid..., sending to TABS, " + subscriber.getA_party());
						eda.sendRequest("92"+subscriber.getA_party(), true);
						VipCallWarid.sendTabsRequest(subscriber.getA_party(), "INST");
						subscriber.setStatus(1);
						subscriber.setSub_date(getCurrenttime());
						checkuserAlreadyExistAndSave(subscriber);
						String finalSuccess = PropertyFileReading.getProperty("FINAL_SUB_POSTPAID_SUCCESS_SMS");
						sendSMS(subscriber.getA_party(), finalSuccess);
						return 1;
					} else {
						logger.info("Not warid postpaid..., sending to TIBCO, " + subscriber.getA_party());
						inTibco.setUsername(tibcoUsername);
						inTibco.setPassword(tibcoPassword);
						inTibco.setMSISDN("0" + subscriber.getA_party());
						inTibco.setTransactionID(String.valueOf(System.currentTimeMillis()));
						postDataOnAddTibco(inTibco);
					}
					
				} else {
					logger.info("Unable to get sub type for UCIP: " + subscriber.getA_party());
					throw new Exception("Unable to get sub type for UCIP: \" + subscriber.getA_party()");
				}
			} else {
				logger.info("Unable to get sub type for UCIP: " + subscriber.getA_party());
				throw new Exception("Unable to get sub type for UCIP: \" + subscriber.getA_party()");
			}
		} catch (Exception e) {
			logger.error("Exception in service:", e);
			throw new Exception("Exception in service:" + e);

		}
		return 0;
	}
	private void checkuserAlreadyExistAndSave(Subscriber subscriber) {
		try{
			Subscriber sub = subDao.findByAparty(subscriber.getA_party());
			if(sub.getCreated_date()!=null) {
			subscriber.setCreated_date(sub.getCreated_date());
			}else {
			subscriber.setCreated_date(getCurrenttime());
			}
			
			factory.save(subscriber);
		}catch (Exception e) {
			logger.info("new User Saved");
			if(subscriber.getCreated_date()==null){
				subscriber.setCreated_date(getCurrenttime());
			}
			factory.save(subscriber);
		}
		
	}


	public Subscriber get(String b_party) {
		return repo.findByBParty(b_party);
	}

	public int delete(String b_party, String channel) throws Exception {
		repo = factory.createRepository(b_party);
		Subscriber sub = subDao.findByAparty(b_party);

		if (sub.getOpr_type() == 3) {
			
			eda.sendRequest("92"+b_party, false);
			VipCallWarid.sendTabsRequest(b_party, "DISC");
			String finalSuccessunsub = PropertyFileReading.getProperty("FINAL_UNSUB_POSTPAID_SUCCESS_SMS");
			sendSMS(b_party, finalSuccessunsub);
			repo.unsubscriber(b_party, 6, channel);
			return 1;
		} else {
			repo.unsubscriber(b_party, 0, channel);
			RemoveProductIn re = new RemoveProductIn();
			re.setMSISDN("0"+b_party);
			re.setTransactionID(String.valueOf(System.currentTimeMillis()));
			re.setPassword(tibcoPassword);
			re.setUsername(tibcoUsername);
			postDataOnDeleteTibco(re);
			return 0;
		}

	}

	private int postDataOnDeleteTibco(RemoveProductIn re) {
		RemoveProductOut resp = removeclient.removeClient(removeclient.marshallerRemoved())
				.getRemoveResponse(removeclient.marshallerRemoved(), re);
		repo = factory.createRepository(re.getMSISDN());
		logger.info("MSISDN "+re.getMSISDN()+" transectionId" +re.getTransactionID()+"::: response status "+resp.getStatus()+"::: response message "+resp.getMessage());
		if (resp.getStatus().equals("OK")) {
			return repo.updateTibcoVipCallsubscriberStatus(re.getMSISDN().substring(1), pendingForCallBackRemoveStatus);
		} else {
			logger.info("tibco failed sub");
			return repo.updateTibcoVipCallsubscriberStatus(re.getMSISDN().substring(1),
					pendingForCallBackFailedRemoveStatus);

		}
	}

	public void sendSMS(String msisdn, String text) {
		try {
			smsSender.sendSMSNotification(formatCellNumber(msisdn), text);
		logger.info("Sending sms on this Msisdn:" + msisdn + " with text::" + text);
		} catch (Exception e) {
			logger.error("exception : " + e.getMessage());
		}
	}

	public String formatCellNumber(String cellNumber) {
		if (cellNumber.startsWith("+92")) {
			cellNumber = cellNumber.substring(1);
		} else if (cellNumber.startsWith("0092")) {
			cellNumber = cellNumber.substring(2);
		} else if (cellNumber.startsWith("03")) {
			cellNumber = "92" + cellNumber.substring(1);
		} else if (cellNumber.startsWith("3")) {
			cellNumber = "92" + cellNumber;
		}
		return cellNumber;
	}

	private String getSubType(String msisdn) {
		final String uri = PropertyFileReading.getProperty("charging.url") + msisdn;
		logger.info("getSubType: " + uri);
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(uri, String.class);
		logger.info("getSubType: " + result);
		return result;
	}
	protected Timestamp getCurrenttime() {
		return new Timestamp(System.currentTimeMillis());

	}
}
