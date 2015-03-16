package com.xa3ti.webstart.business.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.xa3ti.webstart.base.util.XaResult;
import com.xa3ti.webstart.business.entity.Property;

/**
 * @Title: PropertyService.java
 * @Package com.xa3ti.webstart.business.service
 * @Description: 属性控制器
 * @author hchen
 * @date 2014年10月15日 上午11:52:31
 * @version V1.0
 */
public interface PropertyService {

	XaResult<Property>  createProperty(Property property);
	
	XaResult<Property> updateProperty(Property property);
	
	XaResult<Property> deletePropertyById(Long id,Long modifyUser);
	
	XaResult<Property> findPropertyById(Long id);
	
	XaResult<Page<Property>> findPropertyByfilter(Map<String, Object> filterParams,
			Pageable pageable);
}

