package com.xa3ti.webstart.business.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.xa3ti.webstart.base.util.XaResult;
import com.xa3ti.webstart.business.entity.Model;


public interface ModelService {

	public XaResult<Model> findModel(Long modelId);
	
	public XaResult<Page<Model>> findModelByFilter(Map<String, Object> filterParams,
			Pageable pageable);
	
	public XaResult<Model> createModel(Model model);
	
	public XaResult<Model> updateModel(Model model);
	
	public XaResult<Model> deleteModel(Long modelId,Long modifyUser);
	
	
	/**
	 * @Title: publishModel
	 * @Description: 发布一条实体
	 * @param modelId
	 * @return    
	 */
	public XaResult<Model> publishModel(Long modelId,Long modifyUser);
	
}
