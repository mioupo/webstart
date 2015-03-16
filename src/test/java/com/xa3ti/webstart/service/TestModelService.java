package com.xa3ti.webstart.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.xa3ti.webstart.base.XaTestBase;
import com.xa3ti.webstart.base.util.XaDbStatus;
import com.xa3ti.webstart.base.util.XaResult;
import com.xa3ti.webstart.business.entity.Model;
import com.xa3ti.webstart.business.entity.Property;
import com.xa3ti.webstart.business.service.ModelService;

public class TestModelService extends XaTestBase {

	@Autowired
	private ModelService modelService;
	
	@Test
	public void testCreateModel(){
		Model model = new Model();
		model.setIdentify("Company");
		model.setName("商家");
		model.setDescription("商家数据模型");
		List<Property> properties = new ArrayList<Property>();
		Property property = new Property();
		property.setIdentify("id");
		property.setName("ID");
		property.setDescription("ID");
		property.setType("Long");
		property.setNullAble(1);
		property.setLength(0);
		property.setStatus(XaDbStatus.DB_STATUS_NOMAL);
		properties.add(property);
		model.setProperties(properties);
		XaResult<Model> xr = modelService.createModel(model);
		System.out.println("code:"+xr.getCode()+"   message:"+xr.getMessage()+"  object:"+xr.getObject());
	}
	
	@Test
	public void testUpdateModel(){
		Model model = new Model();
		model.setId(4L);
		XaResult<Model> xr = modelService.findModel(1L);
		System.out.println("code:"+xr.getCode()+"   message:"+xr.getMessage()+"  object:"+xr.getObject());
		xr.getObject().getProperties().get(0).setDescription("属性ID");
		xr = modelService.updateModel(xr.getObject());
		System.out.println("code:"+xr.getCode()+"   message:"+xr.getMessage()+"  object:"+xr.getObject());
	}
}
