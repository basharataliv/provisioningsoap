package com.provisioning.gateway.model;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonNaming;

//@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
public class SMSNotification implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private String SMSText;
	private String APartyNumber;
	private String BPartyNumber;
	private String SMSType;
	
	public String getSMSText() {
		return SMSText;
	}
	public void setSMSText(String sMSText) {
		SMSText = sMSText;
	}
	public String getAPartyNumber() {
		return APartyNumber;
	}
	public void setAPartyNumber(String aPartyNumber) {
		APartyNumber = aPartyNumber;
	}
	public String getBPartyNumber() {
		return BPartyNumber;
	}
	public void setBPartyNumber(String bPartyNumber) {
		BPartyNumber = bPartyNumber;
	}
	public String getSMSType() {
		return SMSType;
	}
	public void setSMSType(String sMSType) {
		SMSType = sMSType;
	}
	@Override
	public String toString() {
		return "SMSNotification [SMSText=" + SMSText + ", APartyNumber=" + APartyNumber + ", BPartyNumber="
				+ BPartyNumber + ", SMSType=" + SMSType + "]";
	}
	
	
}