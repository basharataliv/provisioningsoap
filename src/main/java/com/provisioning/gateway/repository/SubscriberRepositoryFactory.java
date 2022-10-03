package com.provisioning.gateway.repository;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.provisioning.gateway.model.Subscriber;
import com.provisioning.gateway.model.Subscriber_0;
import com.provisioning.gateway.model.Subscriber_1;
import com.provisioning.gateway.model.Subscriber_2;
import com.provisioning.gateway.model.Subscriber_3;
import com.provisioning.gateway.model.Subscriber_4;
import com.provisioning.gateway.model.Subscriber_5;
import com.provisioning.gateway.model.Subscriber_6;
import com.provisioning.gateway.model.Subscriber_7;
import com.provisioning.gateway.model.Subscriber_8;
import com.provisioning.gateway.model.Subscriber_9;

@Service
public class SubscriberRepositoryFactory {
	@Autowired
	private SubscriberRepository_0 repo_0;
	@Autowired
	private SubscriberRepository_1 repo_1;
	@Autowired
	private SubscriberRepository_2 repo_2;
	@Autowired
	private SubscriberRepository_3 repo_3;
	@Autowired
	private SubscriberRepository_4 repo_4;
	@Autowired
	private SubscriberRepository_5 repo_5;
	@Autowired
	private SubscriberRepository_6 repo_6;
	@Autowired
	private SubscriberRepository_7 repo_7;
	@Autowired
	private SubscriberRepository_8 repo_8;
	@Autowired
	private SubscriberRepository_9 repo_9;

	public MainRepository createRepository(String cellno) {
		int index = Integer.parseInt(cellno.substring(cellno.length() - 1));
		switch (index) {
		case 0:
			return repo_0;
		case 1:
			return repo_1;
		case 2:
			return repo_2;
		case 3:
			return repo_3;
		case 4:
			return repo_4;
		case 5:
			return repo_5;
		case 6:
			return repo_6;
		case 7:
			return repo_7;
		case 8:
			return repo_8;
		case 9:
			return repo_9;
		}
		return null;
	}

	public void save(String str, String cellno) {
		try {
			int index = Integer.parseInt(cellno.substring(cellno.length() - 1));
			switch (index) {
			case 0:
				Subscriber_0 sub = mapFromJson(str, Subscriber_0.class);
				repo_0.save(sub);
				break;
			case 1:
				Subscriber_1 sub1 = mapFromJson(str, Subscriber_1.class);
				repo_1.save(sub1);
				break;
			case 2:
				Subscriber_2 sub2 = mapFromJson(str, Subscriber_2.class);
				repo_2.save(sub2);
				break;
			case 3:
				Subscriber_3 sub3 = mapFromJson(str, Subscriber_3.class);
				repo_3.save(sub3);
				break;
			case 4:
				Subscriber_4 sub4 = mapFromJson(str, Subscriber_4.class);
				repo_4.save(sub4);
				break;
			case 5:
				Subscriber_5 sub5 = mapFromJson(str, Subscriber_5.class);
				repo_5.save(sub5);
				break;
			case 6:
				Subscriber_6 sub6 = mapFromJson(str, Subscriber_6.class);
				repo_6.save(sub6);
				break;
			case 7:
				Subscriber_7 sub7 = mapFromJson(str, Subscriber_7.class);
				repo_7.save(sub7);
				break;
			case 8:
				Subscriber_8 sub8 = mapFromJson(str, Subscriber_8.class);
				repo_8.save(sub8);
				break;
			case 9:
				Subscriber_9 sub9 = mapFromJson(str, Subscriber_9.class);
				repo_9.save(sub9);
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}

	protected <T> T mapFromJson(String json, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);
	}

	public void save(Subscriber sub) {
		
		try {
			save(mapToJson(sub),sub.getA_party());
		} catch (JsonProcessingException e) {
			
			e.printStackTrace();
		}
	}
}
