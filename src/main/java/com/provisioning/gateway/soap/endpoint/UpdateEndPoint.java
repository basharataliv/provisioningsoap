package com.provisioning.gateway.soap.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.provisioning.gateway.soap.service.UpdateService;

import provresponse.DeProvResult;
import provresponse.DeProvResultResponse;
import provresponse.ProvResult;
import provresponse.ProvResultResponse;






@Endpoint
public class UpdateEndPoint {
	@Autowired
	private UpdateService service;

	 private static final String NAMESPACE_URI = "urn:provresponse";

	    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "ProvResult")
	    @ResponsePayload
	    public ProvResultResponse getResponse(@RequestPayload ProvResult request) {
	    	ProvResultResponse response = new ProvResultResponse();
	        return service.addVipCall(request);
	    }

	    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "DeProvResult")
	    @ResponsePayload
	    public DeProvResultResponse getResponse(@RequestPayload DeProvResult request) {
	        DeProvResultResponse response = new DeProvResultResponse();
	        return service.removeVipCall(request);
	    }
}
