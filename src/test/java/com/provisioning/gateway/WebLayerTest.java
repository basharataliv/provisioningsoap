package com.provisioning.gateway;

import static java.util.Collections.singletonList;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.provisioning.gateway.controller.ProvisioningController;
import com.provisioning.gateway.model.Subscriber;

//@RunWith(SpringRunner.class)
//@WebMvcTest(ProvisioningController.class)

//public class WebLayerTest {
//	@Autowired
//	private MockMvc mvc;

//	@MockBean
//	private ProvisioningController provisioningController;

//	@Test
//	public void getSubscribers() throws Exception {
//		Subscriber subscriber = new Subscriber();
//		subscriber.setA_party("03335009753");
//
//		List<Subscriber> allSubscribers = singletonList(subscriber);
//		given(provisioningController.listSubscribers()).willReturn(allSubscribers);
//
//		mvc.perform(get("/subscribers").contentType(APPLICATION_JSON)).andExpect(status().isOk())
//				.andExpect(jsonPath("$", hasSize(1))).andExpect(jsonPath("$[0].b_party", is(subscriber.getA_party())));
//	}
//
//	@Test
//	public void getSubscribersByBparty() throws Exception {
//		Subscriber subscriber = new Subscriber();
//		subscriber.setA_party("03335009753");
//		ResponseEntity<Subscriber> subResponse = new ResponseEntity<Subscriber>(HttpStatus.OK);
//		given(provisioningController.getSubscriber(subscriber.getA_party())).willReturn(subResponse);
//
//		mvc.perform(get("/subscribers/" + subscriber.getA_party()).contentType(APPLICATION_JSON))
//				.andExpect(status().isOk());
//	}
//
//}
