package com.xa3ti.webstart.service;

import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.xa3ti.webstart.base.XaTestBase;
import com.xa3ti.webstart.business.service.XaVelocityService;

public class TestXaVelocityService extends XaTestBase {
	@Autowired
	private XaVelocityService xaVelocityService;

	@Test
	public void testPublishMode() {
		Map<String,String> rt = xaVelocityService.publishModel(1L,16L);
		for(String file : rt.keySet()){
			System.out.println("File:"+file);
			System.out.println("*********************");
			System.out.println(rt.get(file));
		}
		//System.out.println(xaVelocityService.publishModel(16L));
	}

	@Test
	public void testPublishRepository() {
		Map<String,String> rt = xaVelocityService.publishRepository(1L,16L);
		for(String file : rt.keySet()){
			System.out.println("File:"+file);
			System.out.println("*********************");
			System.out.println(rt.get(file));
		}
	}

	@Test
	public void testPublishService() {
		Map<String,String> rt = xaVelocityService.publishService(1L,16L);
		for(String file : rt.keySet()){
			System.out.println("File:"+file);
			System.out.println("*********************");
			System.out.println(rt.get(file));
		}
	}

	@Test
	public void testPublishServiceImpl() {
		Map<String,String> rt = xaVelocityService.publishServiceImpl(1L,16L);
		for(String file : rt.keySet()){
			System.out.println("File:"+file);
			System.out.println("*********************");
			System.out.println(rt.get(file));
		}
	}
	@Test
	public void testPublishController() {
		Map<String,String> rt = xaVelocityService.publishController(1L,16L);
		for(String file : rt.keySet()){
			System.out.println("File:"+file);
			System.out.println("*********************");
			System.out.println(rt.get(file));
		}
	}
	@Test
	public void testPublishVo() {
		Map<String,String> rt = xaVelocityService.publishVo(1L,4L);
		for(String file : rt.keySet()){
			System.out.println("File:"+file);
			System.out.println("*********************");
			System.out.println(rt.get(file));
		}
	}
	@Test
	public void testPublishApi() {
		Map<String,String> rt = xaVelocityService.publishApi(1L,4L);
		for(String file : rt.keySet()){
			System.out.println("File:"+file);
			System.out.println("*********************");
			System.out.println(rt.get(file));
		}
	}
	@Test
	public void testPublishTestService() {
		Map<String,String> rt = xaVelocityService.publishTestService(1L,4L);
		for(String file : rt.keySet()){
			System.out.println("File:"+file);
			System.out.println("*********************");
			System.out.println(rt.get(file));
		}
	}
	@Test
	public void testPublishTestController() {
		Map<String,String> rt = xaVelocityService.publishTestController(1L,4L);
		for(String file : rt.keySet()){
			System.out.println("File:"+file);
			System.out.println("*********************");
			System.out.println(rt.get(file));
		}
	}
}
