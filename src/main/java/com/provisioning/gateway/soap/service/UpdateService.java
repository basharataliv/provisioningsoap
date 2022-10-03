package com.provisioning.gateway.soap.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.provisioning.gateway.config.PropertyFileReading;
import com.provisioning.gateway.dao.SubscribersDao;
import com.provisioning.gateway.enums.SoapResponseCode;
import com.provisioning.gateway.model.Chargeprocess;
import com.provisioning.gateway.model.Subscriber;
import com.provisioning.gateway.repository.MainRepository;

import com.provisioning.gateway.repository.SubscriberRepositoryFactory;
import com.provisioning.gateway.service.SubscriberService;

import provresponse.DeProvResult;
import provresponse.DeProvResultResponse;
import provresponse.ProvResult;
import provresponse.ProvResultResponse;

@Service
public class UpdateService {
	private static final Logger logger = LogManager.getLogger(UpdateService.class);

	private MainRepository repo;
	@Autowired
	private SubscriberService smsService;
	@Autowired
	private SubscriberRepositoryFactory factory;
	@Autowired
	private SubscribersDao subDao;

	private int addStatus = Integer
			.parseInt(PropertyFileReading.getProperty("tibco.soap.callback.add.subscription.success.status"));
	private int remove = Integer
			.parseInt(PropertyFileReading.getProperty("tibco.soap.callback.remove.subscription.success.status"));

	// this service class recieve soap request and update status
	public ProvResultResponse addVipCall(ProvResult request) {
		repo = factory.createRepository(request.getMSISDN());
		ProvResultResponse response = new ProvResultResponse();
		
		logger.info("Request Recieved in add call back:" + request.getMSISDN().substring(1));
		try {
			if (repo.addVipCallsubscriber(request.getMSISDN().substring(1), addStatus) == 1) {
				response.setRESPONSECODE(SoapResponseCode.SUCCESS_CODE.getValue());
				logger.info(request.getMSISDN().substring(1)+" Status Update successfully to active:" + addStatus);
				Subscriber sub = subDao.findByAparty(request.getMSISDN().substring(1));
				if(sub.getOpr_type()==0 ||sub.getOpr_type()==2) {
					Chargeprocess chargeProcess = charge_user(request.getMSISDN().substring(1));
					if(chargeProcess!=null) {
						logger.info("Deduction" + chargeProcess.toString());
					}
					}
				
				if (sub.getOpr_type() == 0 || sub.getOpr_type() == 2) {
					// prepaid sms
					String finalSuccessPrepaid = PropertyFileReading.getProperty("FINAL_SUB_PREPAID_SUCCESS_SMS");
					smsService.sendSMS(request.getMSISDN(), finalSuccessPrepaid);
				} else if (sub.getOpr_type() == 1 || sub.getOpr_type() == 3) {
					// postpaid sms
					String finalSuccessPostpaid = PropertyFileReading.getProperty("FINAL_SUB_POSTPAID_SUCCESS_SMS");
					smsService.sendSMS(request.getMSISDN(), finalSuccessPostpaid);
				}

			} else {
				response.setRESPONSECODE(SoapResponseCode.SUCCESS_CODE.getValue());
				setDataIntoSub(request.getMSISDN().substring(1));
				logger.info("data not found in db added now");
			}

		} catch (Exception e) {
			response.setRESPONSECODE(SoapResponseCode.EXCEPTION_CODE.getValue());
			repo.addVipCallsubscriber(request.getMSISDN().substring(1), 0);
			logger.error("error in service" , e);
		}
		return response;

	}

// this service class recieve soap request and update status
	public DeProvResultResponse removeVipCall(DeProvResult request) {
		repo = factory.createRepository(request.getMSISDN());
		logger.info("Request Recieved in remove call back:" + request.getMSISDN().substring(1));
		DeProvResultResponse response = new DeProvResultResponse();
		response.setRESPONSECODE(SoapResponseCode.SUCCESS_CODE.getValue());
		try {
			if (repo.removeVipCallsubscriber(request.getMSISDN().substring(1), remove) == 1) {
				response.setRESPONSECODE(SoapResponseCode.SUCCESS_CODE.getValue());
				logger.info(request.getMSISDN().substring(1)+" Status Update successfully to active:" + remove);
				sendFinalUnsubMsg(request);

			} else {
				response.setRESPONSECODE(SoapResponseCode.NOT_EXIST_CODE.getValue());
			}

		} catch (Exception e) {
			System.out.println("Exception in Custome service" + e);
			response.setRESPONSECODE(SoapResponseCode.EXCEPTION_CODE.getValue());
			repo.addVipCallsubscriber(request.getMSISDN().substring(1), 0);
			logger.error("error in  remove service" ,e);
		}
		return response;
	}

	private void sendFinalUnsubMsg(DeProvResult request) {
		Subscriber sub = subDao.findByAparty(request.getMSISDN().substring(1));
		if(sub.getUnsub_channel()==null ||sub.getUnsub_channel().isEmpty()) {
			sub.setUnsub_channel("CRM");
			save(sub);
		}

		if (sub.getOpr_type() == 0 || sub.getOpr_type() == 2) {
			// prepaid sms
			String finalSuccessPrepaid = PropertyFileReading.getProperty("FINAL_UNSUB_PREPAID_SUCCESS_SMS");
			smsService.sendSMS(request.getMSISDN(), finalSuccessPrepaid);
		} else if (sub.getOpr_type() == 1 || sub.getOpr_type() == 3) {
			String finalSuccessPostpaid = PropertyFileReading.getProperty("FINAL_UNSUB_POSTPAID_SUCCESS_SMS");
			smsService.sendSMS(request.getMSISDN(), finalSuccessPostpaid);
		}

	}

	public Chargeprocess charge_user(String msisdn) {
		Chargeprocess result = null;
		try {
			final String uri = PropertyFileReading.getProperty("charging.url") + msisdn;
			logger.info("charge_user: " + uri);
			JSONObject personJsonObject;
			personJsonObject = new JSONObject();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			personJsonObject = new JSONObject();
			personJsonObject.put("a_party", msisdn);
			RestTemplate restTemplate = new RestTemplate();
			HttpEntity<String> request = new HttpEntity<String>(personJsonObject.toString(), headers);
			result = restTemplate.postForObject(uri, request, Chargeprocess.class);
//			JsonNode root = objectMapper.readTree(result);
			result = restTemplate.getForObject(uri, Chargeprocess.class);
			logger.info("charge_user: " + result);
			return result;
		} catch (Exception ex) {
			logger.info("Exception while charging: ", ex.getLocalizedMessage());

		}
		return result;
	}

	private void setDataIntoSub(String b_party) {
		repo = factory.createRepository(b_party);
		String sub_type_resp = getSubType(b_party);
		if (sub_type_resp != null) {
			sub_type_resp = sub_type_resp.replace("[", "");
			sub_type_resp = sub_type_resp.replace("]", "");
			String sub_type_arr[] = sub_type_resp.split(",");
			logger.info(b_party,sub_type_resp);
			int sub_type = Integer.parseInt(sub_type_arr[0].replace("\"", ""));
			Subscriber sub = new Subscriber();
			sub.setA_party(b_party);
			sub.setService_id(Integer.parseInt(PropertyFileReading.getProperty("DEFAULT_SERVICE_ID")));
			sub.setOpr_type(sub_type);
			sub.setStatus(1);
			sub.setChannel("CRM");
			sub.setCreated_date(new Timestamp(System.currentTimeMillis()));
		    sub.setSub_date(new Timestamp(System.currentTimeMillis()));
		    sub.setHlractivation_date(new Timestamp(System.currentTimeMillis()));
			save(sub);
			Subscriber subs = subDao.findByAparty(b_party);
			if(subs.getOpr_type()==0 ||subs.getOpr_type()==2) {
				Chargeprocess chargeProcess = charge_user(b_party);
				if(chargeProcess!=null) {
					logger.info("Deduction" + chargeProcess.toString());
					}
				
			}

			if (subs.getOpr_type() == 0 || subs.getOpr_type() == 2) {
				// prepaid sms
				String finalSuccessPrepaid = PropertyFileReading.getProperty("FINAL_SUB_PREPAID_SUCCESS_SMS");
				smsService.sendSMS(b_party, finalSuccessPrepaid);
			} else if (subs.getOpr_type() == 1 || subs.getOpr_type() == 3) {
				// postpaid
				String finalSuccessPostpaid = PropertyFileReading.getProperty("FINAL_SUB_POSTPAID_SUCCESS_SMS");
				smsService.sendSMS(b_party, finalSuccessPostpaid);
			}

		}
	}

	private String getSubType(String msisdn) {
		final String uri = PropertyFileReading.getProperty("charging.url") + msisdn;
		logger.info("getSubType: " + uri);
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(uri, String.class);
		logger.info("getSubType: " + result);
		return result;
	}

	private void save(Subscriber sub) {

		try {
			factory.save(factory.mapToJson(sub), sub.getA_party());
		} catch (JsonProcessingException e) {
			logger.error("error",e);;
		}

	}
}
