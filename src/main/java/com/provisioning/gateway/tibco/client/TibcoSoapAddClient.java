package com.provisioning.gateway.tibco.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.provisioning.gateway.config.PropertyFileReading;
import com.tibco.add.AddProductIn;
import com.tibco.add.AddProductOut;


@Service
public class TibcoSoapAddClient extends WebServiceGatewaySupport {

	private WebServiceTemplate template;
	 public static final String GATEWAY_REQUEST_URL = PropertyFileReading.getProperty("tibco.url");//"http://10.50.171.8:80/MEFBSLive/Services/MEFBusinessServices/Controller/SOAPMainListener";
	 public static final String CALLBACK_URL = PropertyFileReading.getProperty("tibco.callbackurl");;

	public AddProductOut getAddResponse(Jaxb2Marshaller marshaller,AddProductIn request) {
		template = new WebServiceTemplate(marshaller);
		
		final SoapActionCallback soapActionCallback = new SoapActionCallback(CALLBACK_URL);
		AddProductOut response = (AddProductOut) template
		    .marshalSendAndReceive(GATEWAY_REQUEST_URL,request, soapActionCallback );
		
		
//		AddProductOut response = (AddProductOut) template.marshalSendAndReceive(GATEWAY_REQUEST_URL,
//				request);
		return response;
	}
}
