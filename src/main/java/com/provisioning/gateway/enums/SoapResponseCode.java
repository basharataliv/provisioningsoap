package com.provisioning.gateway.enums;

public enum SoapResponseCode {

    SUCCESS_CODE("0", "SUCCESS"),
	EXCEPTION_CODE("1", "Service Exception ERROR"),
	NOT_EXIST_CODE("2", "MSISDN Not exist in System");
  

    private final String value;
    private final String reasonPhrase;
	private SoapResponseCode(String value, String reasonPhrase) {
		this.value = value;
		this.reasonPhrase = reasonPhrase;
	}
	public String getValue() {
		return value;
	}
	public String getReasonPhrase() {
		return reasonPhrase;
	}
    
    
	

}
