package com.provisioning.gateway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.provisioning.gateway.config.PropertyFileReading;
import com.provisioning.gateway.payload.SubscriptionRequest;
//import com.provisioning.gateway.repository.SubscriberRepository;

import com.provisioning.gateway.utils.DateTimeUtils;

@Service
public class TibcoConnecticityService {
/*
	@Autowired
	private ServiceConfiguration client;

	@Autowired
	private SubscriberRepository repo;

	private int pendingForCallBackAddStatus = Integer
			.parseInt(PropertyFileReading.getProperty("tibco.soap.pending.for.callback.add.status"));
	private int pendingForCallBackRemoveStatus = Integer
			.parseInt(PropertyFileReading.getProperty("tibco.soap.pending.for.callback.remove.status"));
	private int pendingForCallBackFailedAddStatus = Integer
			.parseInt(PropertyFileReading.getProperty("tibco.soap.pending.for.callback.add.fail.status"));
	private int pendingForCallBackFailedRemoveStatus = Integer
			.parseInt(PropertyFileReading.getProperty("tibco.soap.pending.for.callback.remove.fail.status"));
	private String sub = PropertyFileReading.getProperty("tibco.soap.sub.parameter");
	private String unsub = PropertyFileReading.getProperty("tibco.soap.unsub.parameter");

	public int postDataOnTibco(SubscriptionRequest SubscriptionRequest) {
		if (client.countryClient(client.getMarshaller()).getResponse(SubscriptionRequest, DateTimeUtils.timeStamp(),
				client.getMarshaller())) {
			if (SubscriptionRequest.getAction().equals(sub)) {
				return repo.updateTibcoVipCallsubscriberStatus(SubscriptionRequest.getMsisdn(),
						pendingForCallBackAddStatus);
			} else if (SubscriptionRequest.getAction().equals(unsub)) {
				return repo.updateTibcoVipCallsubscriberStatus(SubscriptionRequest.getMsisdn(),
						pendingForCallBackRemoveStatus);
			} else {
				return 0;
			}
		} else {
			if (SubscriptionRequest.getAction().equals(sub)) {
				return repo.updateTibcoVipCallsubscriberStatus(SubscriptionRequest.getMsisdn(),
						pendingForCallBackFailedAddStatus);
			} else if (SubscriptionRequest.getAction().equals(unsub)) {
				return repo.updateTibcoVipCallsubscriberStatus(SubscriptionRequest.getMsisdn(),
						pendingForCallBackFailedRemoveStatus);
			} else {
				return 0;
			}
		}

	}
*/
}
