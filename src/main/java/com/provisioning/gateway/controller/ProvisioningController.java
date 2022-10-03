package com.provisioning.gateway.controller;

import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.provisioning.gateway.config.PropertyFileReading;
import com.provisioning.gateway.model.Subscriber;
import com.provisioning.gateway.service.SubscriberService;
import com.provisioning.gateway.utils.DateTimeUtils;

/**
 * 
 * @author mateen
 *
 */
@RestController
public class ProvisioningController {
	private final Logger logger = LoggerFactory.getLogger(ProvisioningController.class);

	@Autowired
	private SubscriberService subscriberService;

	/**
	 * 
	 * @param subscriber
	 * @return
	 */
	@PostMapping("/subscribers")
	public ResponseEntity<?> addSubscriber(@RequestBody Subscriber subscriber) {
		try {
			logger.info("Saving subscriber: " + subscriber.getA_party());
			subscriber.setService_id(Integer.parseInt(PropertyFileReading.getProperty("DEFAULT_SERVICE_ID")));
			int status = subscriberService.addsubscriber(subscriber);
			if (status == 0) {
				subscriberService.sendSMS(subscriber.getA_party(), PropertyFileReading.getProperty("SUB_SUCCESS_SMS"));
			}
			logger.info("Subscriber saved: " + subscriber.getA_party());
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Exception while saving subscriber: " + subscriber.getA_party() + ": " + e.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * 
	 * @param b_party
	 * @return
	 */
	@DeleteMapping("/subscribers/{b_party}/{channel}")
	public ResponseEntity<?> deleteSubscriber(@PathVariable String b_party, @PathVariable String channel) {
		try {
			logger.info("Un Subscriber subscriber : " + b_party);
			int status = subscriberService.delete(b_party, channel);
			if (status == 0) {
				subscriberService.sendSMS(b_party, PropertyFileReading.getProperty("UNSUB_SUCCESS_SMS"));
			}
			logger.info("Subscriber Un Subscribed : " + b_party);

			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Exception while deleting subscriber: " + b_party,e);

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
