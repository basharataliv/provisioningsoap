package com.provisioning.gateway;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.provisioning.gateway.model.Subscriber;

/*
public class SubscriberServiceControllerTest extends AbstractTest {
	@Override
	@Before
	public void setUp() {
		super.setUp();
	}

// subscription test cases
	@Test
	public void getSubscriberList() throws Exception {
		String uri = "/subscribers";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Subscriber[] subscriberList = super.mapFromJson(content, Subscriber[].class);
		assertTrue(subscriberList.length > 0);
	}

	@Test
	public void createSubscriber() throws Exception {
		String uri = "/subscribers";
		Subscriber subscriber = new Subscriber();
		subscriber.setA_party("3335009753");
		subscriber.setOpr_type(1);
		subscriber.setCreated_date(new Timestamp(=System.currentTimeMillis()));
		subscriber.setChannel("IVR");
		subscriber.setStatus(1);
		//subscriber.setDefaultpin("1234");

		String inputJson = super.mapToJson(subscriber);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "");
	}

	@Test
	public void updateSubscriber() throws Exception {
		String uri = "/subscribers/3335009753";
		Subscriber subscriber = new Subscriber();
		subscriber.setA_party("3335009753");
		subscriber.setOpr_type(2);
		subscriber.setChannel("SMS");
		subscriber.setStatus(2);
		String inputJson = super.mapToJson(subscriber);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "");
	}

	@Test
	public void unsubscriberSubscriber() throws Exception {
		String uri = "/subscribers/3335009753";
		Subscriber subscriber = new Subscriber();
		subscriber.setA_party("3335009753");
		subscriber.setStatus(0);
		String inputJson = super.mapToJson(subscriber);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "");
	}

	// whitelist test cases
/*	@Test
	public void getWhitelistList() throws Exception {
		String uri = "/whitelists";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Whitelist[] whitelistList = super.mapFromJson(content, Whitelist[].class);
		assertTrue(whitelistList.length > 0);
	}

	@Test
	public void createWhitelist() throws Exception {
		String uri = "/whitelists";
		Whitelist whitelist = new Whitelist();
		whitelist.setId(1);
		whitelist.setA_party("3335009753");
		whitelist.setA_party("3450042420");
		whitelist.setChannel("IVR");
		String inputJson = super.mapToJson(whitelist);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "");
	}

	@Test
	public void updateWhitelist() throws Exception {
		String uri = "/whitelists/1";
		Whitelist whitelist = new Whitelist();
		whitelist.setId(1);
		whitelist.setA_party("3335009753");
		whitelist.setA_party("3450042420");
		whitelist.setChannel("SMS");
		String inputJson = super.mapToJson(whitelist);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "");
	}

	@Test
	public void deleteWhitelist() throws Exception {
		String uri = "/whitelists/1";
		Whitelist whitelist = new Whitelist();
		whitelist.setId(1);
		whitelist.setA_party("3335009753");
		whitelist.setA_party("3450042420");
		whitelist.setChannel("SMS");
		String inputJson = super.mapToJson(whitelist);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "");
	}

	// blacklist test cases
	@Test
	public void getBlacklistList() throws Exception {
		String uri = "/blackLists";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Blacklist[] blacklistList = super.mapFromJson(content, Blacklist[].class);
		assertTrue(blacklistList.length > 0);
	}

	@Test
	public void createBlacklist() throws Exception {
		String uri = "/blackLists";
		Blacklist blacklist = new Blacklist();
		blacklist.setId(1);
		blacklist.setA_party("3335009753");
		blacklist.setA_party("3450042420");
		blacklist.setChannel("IVR");
		String inputJson = super.mapToJson(blacklist);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "");
	}

	@Test
	public void updateBlacklist() throws Exception {
		String uri = "/blackLists/1";
		Blacklist blacklist = new Blacklist();
		blacklist.setId(1);
		blacklist.setA_party("3335009753");
		blacklist.setA_party("3450042420");
		blacklist.setChannel("SMS");
		String inputJson = super.mapToJson(blacklist);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "");
	}

	@Test
	public void deleteBlacklist() throws Exception {
		String uri = "/blackLists/1";
		Blacklist blacklist = new Blacklist();
		blacklist.setId(1);
		blacklist.setA_party("3335009753");
		blacklist.setA_party("3450042420");
		String inputJson = super.mapToJson(blacklist);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "");
	}

	// group member test cases
	@Test
	public void getGroupMemberList() throws Exception {
		String uri = "/groupmembers";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		GroupMember[] groupMemberList = super.mapFromJson(content, GroupMember[].class);
		assertTrue(groupMemberList.length > 0);
	}

	@Test
	public void createGroupMember() throws Exception {
		String uri = "/groupmembers";
		GroupMember groupMember = new GroupMember();
		groupMember.setId(1);
		groupMember.setA_party("3335009753");
		groupMember.setA_party("3450042420");
		groupMember.setChannel("IVR");
		String inputJson = super.mapToJson(groupMember);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "");
	}

	@Test
	public void updateGroupMember() throws Exception {
		String uri = "/groupmembers/1";
		GroupMember groupMember = new GroupMember();
		groupMember.setId(1);
		groupMember.setA_party("3335009753");
		groupMember.setA_party("3450042420");
		groupMember.setChannel("SMS");
		String inputJson = super.mapToJson(groupMember);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "");
	}

	@Test
	public void deleteGroupMember() throws Exception {
		String uri = "/groupmembers/1";
		GroupMember groupMember = new GroupMember();
		groupMember.setId(1);
		groupMember.setA_party("3335009753");
		groupMember.setA_party("3450042420");
		String inputJson = super.mapToJson(groupMember);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "");
	}
}
*/