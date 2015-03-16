package com.xa3ti.webstart.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import com.alibaba.fastjson.JSON;
import com.xa3ti.webstart.base.XaTestWebBase;

public class TestXaHelloController extends XaTestWebBase {

	@Test
	public void TestHello() {
		try {
			ResultActions ra = mockMvc.perform(post(
					"/XaHello/hello").accept(
					MediaType.APPLICATION_JSON));
			MvcResult mr = ra.andReturn();
			System.out.println("Response:\r\n"
					+ mr.getResponse().getContentAsString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void TestHelloByHtml() {
		try {
			ResultActions ra = mockMvc.perform(post(
					"/XaHello/helloByHtml").accept(
					MediaType.TEXT_HTML));
			MvcResult mr = ra.andReturn();
			System.out.println("Response:\r\n"
					+ mr.getResponse().getContentAsString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testFindDataById() {
		try {
			ResultActions ra = mockMvc.perform(post(
					"/XaHello/findDataById?xiangAoId=1").accept(
					MediaType.APPLICATION_JSON));
			MvcResult mr = ra.andReturn();
			System.out.println("Response:\r\n"
					+ mr.getResponse().getContentAsString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testCreateData() {
		try {

			ResultActions ra = mockMvc.perform(post("/XaHello/createData")
					.param("name", "张三").accept(MediaType.APPLICATION_JSON));
			MvcResult mr = ra.andReturn();
			System.out.println("Response:\r\n"
					+ mr.getResponse().getContentAsString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void TestUpdateData() {
		try {

			ResultActions ra = mockMvc.perform(post(
					"/XaHello/updateData?xiangAoId=2&name=王五").accept(
					MediaType.APPLICATION_JSON));
			MvcResult mr = ra.andReturn();
			System.out.println("Response:\r\n"
					+ mr.getResponse().getContentAsString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void TestDelData() {
		try {

			ResultActions ra = mockMvc.perform(post(
					"/XaHello/delData?xiangAoId=2").accept(
					MediaType.APPLICATION_JSON));
			MvcResult mr = ra.andReturn();
			System.out.println("Response:\r\n"
					+ mr.getResponse().getContentAsString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 构造controller中所需要的参数，测试controller。 这里要主要这种构造参数的方法。
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void testFindByFilter() {
		Map jsonFilter = new HashMap<String, String>();
		jsonFilter.put("search_EQ_name", "张三");
		try {
			ResultActions ra = mockMvc.perform(post(
					"/XaHello/findTestDataByFilter").param("jsonFilter",
					JSON.toJSONString(jsonFilter)).accept(
					MediaType.APPLICATION_JSON));
			MvcResult mr = ra.andReturn();
			System.out.println("Response:\r\n"
					+ mr.getResponse().getContentAsString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void findTestData() {
		try {
			ResultActions ra = mockMvc.perform(post("/XaHello/findTestData")
					.accept(MediaType.APPLICATION_JSON));
			MvcResult mr = ra.andReturn();
			System.out.println("Response:\r\n"
					+ mr.getResponse().getContentAsString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
