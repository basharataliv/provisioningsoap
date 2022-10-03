package com.provisioning.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.provisioning.gateway.tibco.client.TibcoSoapAddClient;


@Configuration
public class TibcoAddSoapConfig {

	public static final String GATEWAY_REQUEST_URL_ADD = PropertyFileReading.getProperty("tibco.url");

	@Bean
	public Jaxb2Marshaller marshallerAdd() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setPackagesToScan("com.tibco.add");
		return marshaller;
	}

	@Bean
	public TibcoSoapAddClient addClient(Jaxb2Marshaller marshallerAdd) {
		TibcoSoapAddClient client = new TibcoSoapAddClient();
		client.setDefaultUri(GATEWAY_REQUEST_URL_ADD);
		client.setMarshaller(marshallerAdd);
		client.setUnmarshaller(marshallerAdd);
		return client;
	}
}
