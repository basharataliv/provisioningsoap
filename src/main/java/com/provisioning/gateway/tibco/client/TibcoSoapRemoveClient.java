package com.provisioning.gateway.tibco.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.provisioning.gateway.config.PropertyFileReading;

import wsdl.remove.business.service.RemoveProductIn;
import wsdl.remove.business.service.RemoveProductOut;


@Service
public class TibcoSoapRemoveClient extends WebServiceGatewaySupport {
	

	private WebServiceTemplate template;
	 public static final String GATEWAY_REQUEST_URL = PropertyFileReading.getProperty("tibco.remove.url");//"http://10.50.171.8:80/MEFBSLive/Services/MEFBusinessServices/Controller/SOAPMainListener";
	 public static final String CALLBACK_URL = PropertyFileReading.getProperty("tibco.remove.callbackurl");;

	public RemoveProductOut getRemoveResponse(Jaxb2Marshaller marshaller,RemoveProductIn request) {
	
		template = new WebServiceTemplate(marshaller);
		
		final SoapActionCallback soapActionCallback = new SoapActionCallback(CALLBACK_URL);
		RemoveProductOut response = (RemoveProductOut) template
		    .marshalSendAndReceive(GATEWAY_REQUEST_URL,request, soapActionCallback );
		return response;
	}
}

