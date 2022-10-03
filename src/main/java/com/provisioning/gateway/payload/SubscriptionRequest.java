package com.provisioning.gateway.payload;

import javax.validation.constraints.NotBlank;

public class SubscriptionRequest {
	@NotBlank(message = "Msisdn cannot be null")
	private String msisdn;
	private String action;
	private String channel;
	private String subtype;
	public String getMsisdn() {
		return msisdn;
	}
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getSubtype() {
		return subtype;
	}
	public void setSubtype(String subtype) {
		this.subtype = subtype;
	}
	@Override
	public String toString() {
		return "SubscriptionRequest [msisdn=" + msisdn + ", action=" + action + ", channel=" + channel + ", subtype="
				+ subtype + "]";
	}
	

	
}
