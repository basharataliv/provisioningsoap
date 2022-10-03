package com.provisioning.gateway.kafka;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;

import com.provisioning.gateway.model.SMSNotification;


//@PropertySource(ignoreResourceNotFound = true, value = "classpath:application.yml")
@Service
public class KafkaSender {

	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaSender.class);

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	@Value("${kafka.sms.topic.name}")
	private String smsTopicName;
	@Value("${send.sms.shortcode}")
	private String shortcode;
	

	public void sendSMSNotification(String bPartyNumber,String sMSText ) {
		SMSNotification not=new SMSNotification();
		not.setAPartyNumber(shortcode);
		not.setSMSType("1");
		not.setBPartyNumber(bPartyNumber);
		not.setSMSText(sMSText);
		Map<String, Object> headers = new HashMap<>();
		headers.put(KafkaHeaders.TOPIC, smsTopicName);
		kafkaTemplate.send(new GenericMessage<SMSNotification>(not, headers));
		LOGGER.debug("Data - " + not.toString() + " sent to Kafka Topic - " + smsTopicName);
	}
}
