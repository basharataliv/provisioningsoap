package com.provisioning.gateway;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.provisioning.gateway.config.PropertyFileReading;
import com.provisioning.gateway.config.TibcoAddSoapConfig;
import com.provisioning.gateway.config.TibcoRemoveSoapConfig;
import com.provisioning.gateway.tibco.client.TibcoSoapAddClient;
import com.provisioning.gateway.tibco.client.TibcoSoapRemoveClient;
import com.provisioning.gateway.utils.DateTimeUtils;

import com.tibco.add.AddProductIn;
import com.tibco.add.AddProductOut;
import com.tibco.add.Attribute;
import com.tibco.add.ListOfAttributes;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import wsdl.remove.business.service.RemoveProductIn;
import wsdl.remove.business.service.RemoveProductOut;

@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages={"com.provisioning.gateway"})
public class ProvisioningGatewayApplication {
	private static final Logger logger = LogManager.getLogger(ProvisioningGatewayApplication.class);

	public static void main(String[] args) {
		System.setProperty("javax.net.ssl.trustStore", PropertyFileReading.getProperty("TRUST_STORE"));
		System.setProperty("javax.net.ssl.trustStorePassword", PropertyFileReading.getProperty("TRUST_STORE_PASSWORD"));
		System.setProperty("javax.net.ssl.trustStoreType", PropertyFileReading.getProperty("TRUST_STORE_TYPE"));
		javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(new javax.net.ssl.HostnameVerifier() {
			public boolean verify(String hostname, javax.net.ssl.SSLSession sslSession) {
				logger.info("-------------------hostname: " + hostname);
				return hostname.equals(PropertyFileReading.getProperty("HOST_IP"));
			}
		});
		
		SpringApplication.run(ProvisioningGatewayApplication.class, args);
	}

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.provisioning.gateway")).build();
	}
}
