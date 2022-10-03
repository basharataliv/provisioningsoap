package com.provisioning.gateway.repository;

import org.springframework.data.repository.query.Param;

import com.provisioning.gateway.model.Subscriber;

public interface MainRepository {
	Subscriber findByBParty(String b_party);
	int unsubscriber(String b_party,int status, String channel);
	public int addVipCallsubscriber(String b_party,int status);
	public int removeVipCallsubscriber(String b_party,int status);
	public int updateTibcoVipCallsubscriberStatus(String b_party,  int status);
	
}
