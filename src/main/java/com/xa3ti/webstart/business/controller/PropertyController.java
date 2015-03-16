package com.xa3ti.webstart.business.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xa3ti.webstart.base.util.WebUitl;
import com.xa3ti.webstart.base.util.XaResult;
import com.xa3ti.webstart.business.entity.Property;
import com.xa3ti.webstart.business.service.PropertyService;

/**
 * @Title: PropertyController.java
 * @Package com.xa3ti.webstart.business.controller
 * @Description: 属性控制器
 * @author hchen
 * @date 2014年10月15日 下午12:40:17
 * @version V1.0
 */
@Controller
@RequestMapping("property")
public class PropertyController {

	@Autowired
	private PropertyService propertyService;
	
	/**
	 * @Title: findByfilter
	 * @Description: 分页查询一个实体类的属性
	 * @param nextPage
	 * @param pageSize
	 * @param sortDate
	 * @param jsonFilter
	 * @return    
	 */
	@ResponseBody
	@RequestMapping(method=RequestMethod.GET)
	public XaResult<Page<Property>>  findByfilter(@RequestParam(defaultValue = "0") Integer nextPage,@RequestParam(defaultValue = "10") Integer pageSize,
			String sortDate,String jsonFilter){
		Pageable pageable = WebUitl.buildPageRequest(nextPage, pageSize, sortDate);
		Map<String,Object> filterParams =  WebUitl.getParametersStartingWith(jsonFilter, "search_");
		return propertyService.findPropertyByfilter(filterParams, pageable);
	}
	
	/**
	 * @Title: findModelById
	 * @Description: 根据ID查找单条实体
	 * @param modelId
	 * @return    
	 */
	@ResponseBody
	@RequestMapping(value="{propertyId}",method=RequestMethod.GET)
	public XaResult<Property> findPropertyById(@PathVariable Long propertyId){
		return propertyService.findPropertyById(propertyId);
	}
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.POST)
	public XaResult<Property> save(@RequestBody Property property,HttpServletRequest request){
		Long userId = WebUitl.getCmsLoginedUserId(request);
		property.setModifyUser(userId);
		property.setCreateUser(userId);
		return propertyService.createProperty(property);
	}
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.PUT)
	public XaResult<Property> updateProperty(@RequestBody Property property,HttpServletRequest request){
		Long userId = WebUitl.getCmsLoginedUserId(request);
		property.setModifyUser(userId);
		return propertyService.updateProperty(property);
	}
	@ResponseBody
	@RequestMapping(value="{propertyId}",method=RequestMethod.DELETE)
	public XaResult<Property> deletePropertyById(@PathVariable Long propertyId,HttpServletRequest request){
		Long userId = WebUitl.getCmsLoginedUserId(request);
		return propertyService.deletePropertyById(propertyId,userId);
	}
}

