package com.provisioning.gateway.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@Configuration
@EnableWs
@ComponentScan
public class SoapConfig extends WsConfigurerAdapter {
	
	  @Bean
	    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) 
	    {
	        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
	        servlet.setApplicationContext(applicationContext);
	        servlet.setTransformWsdlLocations(true);
	        return new ServletRegistrationBean(servlet, "/hlrresponsecallbackurl/*");
	    }
	 
	    @Bean(name = "TIBCOCallBackAPI")
	    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema countriesSchema) 
	    {
	        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
	        wsdl11Definition.setPortTypeName("TIBCOCallBackEndpoint");
	        wsdl11Definition.setRequestSuffix("Result");
	        wsdl11Definition.setLocationUri("/hlrresponsecallbackurl");
	       // wsdl11Definition.setTargetNamespace("urn:provresponse");
	        wsdl11Definition.setSchema(countriesSchema);
	        return wsdl11Definition;
	    }
	 
	    @Bean
	    public XsdSchema countriesSchema() 
	    {
	        return new SimpleXsdSchema(new ClassPathResource("TIBCOCallBack.xsd"));
	    }
}
