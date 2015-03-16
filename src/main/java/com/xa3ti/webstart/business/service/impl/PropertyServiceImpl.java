package com.xa3ti.webstart.business.service.impl;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.xa3ti.webstart.base.constant.XaConstant;
import com.xa3ti.webstart.base.util.DynamicSpecifications;
import com.xa3ti.webstart.base.util.SearchFilter;
import com.xa3ti.webstart.base.util.SearchFilter.Operator;
import com.xa3ti.webstart.base.util.XaDbStatus;
import com.xa3ti.webstart.base.util.XaResult;
import com.xa3ti.webstart.base.util.XaUtil;
import com.xa3ti.webstart.business.entity.Property;
import com.xa3ti.webstart.business.repository.ModelRepository;
import com.xa3ti.webstart.business.repository.PropertyRepository;
import com.xa3ti.webstart.business.service.PropertyService;

/**
 * @Title: PropertyServiceImpl.java
 * @Package com.xa3ti.webstart.business.service.impl
 * @Description: TODO
 * @author hchen
 * @date 2014年10月15日 上午11:55:50
 * @version V1.0
 */
@Service("PropertyService")
public class PropertyServiceImpl implements PropertyService {

	@Autowired
	private PropertyRepository propertyRepository;
	
	@Autowired
	private ModelRepository modelRepository;
	
	public XaResult<Property> createProperty(Property property) {
		XaResult<Property> xr=new XaResult<Property>();
		property.setStatus(XaDbStatus.DB_STATUS_NOMAL);
		String createTime= XaUtil.getToDayStr();
		property.setCreateTime(createTime);
		property.setModifyTime(createTime);
		property.setModifyDescription("初始创建");
		property=propertyRepository.save(property);
		xr.success(property);
		return xr;
	}

	
	public XaResult<Property> updateProperty(Property property) {
		XaResult<Property> xr=new XaResult<Property>();
		Property obj=propertyRepository.findOne(property.getId());
		if(XaUtil.isEmpty(obj)){
			xr.error(XaConstant.Message.object_not_find);
		}else{
			obj.setIdentify(property.getIdentify());
			obj.setLength(property.getLength());
			obj.setName(property.getName());
			obj.setNullAble(property.getNullAble());
			obj.setType(property.getType());
			obj.setModifyTime(XaUtil.getToDayStr());
			obj=propertyRepository.save(obj);
			xr.success(obj);
		}
		return xr;
	}

	
	public XaResult<Property> deletePropertyById(Long id,Long modifyUser) {
		XaResult<Property> xr=new XaResult<Property>();
		Property obj=propertyRepository.findOne(id);
		if(XaUtil.isEmpty(obj)){
			xr.error(XaConstant.Message.object_not_find);
		}else{
			obj.setStatus(XaDbStatus.DB_STATUS_DELETE);
			obj.setModifyTime(XaUtil.getToDayStr());
			obj.setModifyUser(modifyUser);
			obj=propertyRepository.save(obj);
			xr.success(obj);
		}
		return xr;
	}

	
	public XaResult<Page<Property>> findPropertyByfilter(
			Map<String, Object> filterParams, Pageable pageable) {
		XaResult<Page<Property>> xr=new XaResult<Page<Property>>();
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		filters.put("status", new SearchFilter("status", Operator.NE,
				XaDbStatus.DB_STATUS_DELETE));// 不显示状态为删除的项
		Page<Property> page = propertyRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), Property.class), pageable);
		xr.success(page);
		return xr;
	}

	
	public XaResult<Property> findPropertyById(Long id) {
		XaResult<Property> xr=new XaResult<Property>();
		Property obj=propertyRepository.findOne(id);
		if(XaUtil.isEmpty(obj)){
			xr.error(XaConstant.Message.object_not_find);
		}else{
			xr.success(obj);
		}
		return xr;
	}

}

