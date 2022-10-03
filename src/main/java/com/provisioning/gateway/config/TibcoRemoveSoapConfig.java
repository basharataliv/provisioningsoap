package com.provisioning.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.provisioning.gateway.tibco.client.TibcoSoapRemoveClient;

@Configuration
public class TibcoRemoveSoapConfig {
	
		public static final String GATEWAY_REQUEST_URL = PropertyFileReading.getProperty("tibco.remove.url");
		
		@Bean
		public Jaxb2Marshaller marshallerRemoved() {
			Jaxb2Marshaller marshaller=new Jaxb2Marshaller();
			marshaller.setPackagesToScan("wsdl.remove.business.service");
			return marshaller;
		}
		
		 @Bean
		    public TibcoSoapRemoveClient removeClient(Jaxb2Marshaller marshallerRemoved) {
			 TibcoSoapRemoveClient client = new TibcoSoapRemoveClient();
		        client.setDefaultUri(GATEWAY_REQUEST_URL);
		        client.setMarshaller(marshallerRemoved);
		        client.setUnmarshaller(marshallerRemoved);
		        return client;
		    }
		 
		
	

}
