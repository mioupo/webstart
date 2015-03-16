package com.xa3ti.webstart.service;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

 
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
 
 




import com.xa3ti.webstart.base.XaTestBase;
import com.xa3ti.webstart.base.util.XaResult;
import com.xa3ti.webstart.business.entity.Property;
import com.xa3ti.webstart.business.service.PropertyService;

public class TestPropertyService extends XaTestBase  {
	@Autowired
	private PropertyService propertyService;
	@Test
	public void testFind(){
		Map<String,Object> filterParams =  new HashMap<String, Object>();
		filterParams.put("EQ_modelId", Long.parseLong("1"));		//条件，是否这个公司的产品
		int pageSize=10;
		int nextPage=0;
 
		Pageable pageable=new PageRequest(nextPage, pageSize);
		XaResult<Page<Property>> xr=propertyService.findPropertyByfilter(filterParams, pageable);
		System.out.println(xr.getCode()+" message="+xr.getMessage());
	}
}
