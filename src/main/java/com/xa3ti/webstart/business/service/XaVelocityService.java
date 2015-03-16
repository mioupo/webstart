package com.xa3ti.webstart.business.service;

import java.util.Map;

public interface XaVelocityService {
	public Map<String,String> publishModel(Long projectId,Long modelId);
	public Map<String,String> publishRepository(Long projectId,Long modelId);
	public Map<String,String> publishService(Long projectId,Long modelId);
	public Map<String,String> publishServiceImpl(Long projectId,Long modelId);
	public Map<String,String> publishController(Long projectId,Long modelId);
	public Map<String,String> publishVo(Long projectId,Long modelId);
	public Map<String,String> publishApi(Long projectId,Long modelId);
	public Map<String,String> publishTestService(Long projectId,Long modelId);
	public Map<String,String> publishTestController(Long projectId,Long modelId);
	public Map<String,String> publishTestProject(Long projectId);
	public Map<String,String> publishProject(Long projectId);
	//public void init(String path);
}
