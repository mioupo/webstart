package com.xa3ti.webstart.business.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xa3ti.webstart.base.constant.XaConstant;
import com.xa3ti.webstart.base.util.DynamicSpecifications;
import com.xa3ti.webstart.base.util.SearchFilter;
import com.xa3ti.webstart.base.util.SearchFilter.Operator;
import com.xa3ti.webstart.base.util.XaDbStatus;
import com.xa3ti.webstart.base.util.XaResult;
import com.xa3ti.webstart.base.util.XaUtil;
import com.xa3ti.webstart.business.entity.Model;
import com.xa3ti.webstart.business.repository.ModelRepository;
import com.xa3ti.webstart.business.service.ModelService;

@Service("ModelService")
@Transactional(readOnly = false)
public class ModelServiceImpl implements ModelService {

	@Autowired
	private ModelRepository modelRepository;

	@Transactional(readOnly = true)
	public XaResult<Page<Model>> findModelByFilter(
			Map<String, Object> filterParams, Pageable pageable) {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		filters.put("status", new SearchFilter("status", Operator.NE,
				XaDbStatus.DB_STATUS_DELETE));// 不显示状态为删除的项
		Page<Model> page = modelRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), Model.class), pageable);
		XaResult<Page<Model>> xr = new XaResult<Page<Model>>();
		xr.success(page);
		return xr;
	}

	public XaResult<Model> createModel(Model model) {

		XaResult<Model> xr = new XaResult<Model>();
		model.setStatus(XaDbStatus.DB_STATUS_NOMAL);
		String createTime= XaUtil.getToDayStr();
		model.setCreateTime(createTime);
		model.setModifyTime(createTime);
		model.setModifyDescription("初始创建");
		Model obj = modelRepository.save(model);
		xr.success(obj);
		return xr;
	}

	public XaResult<Model> updateModel(Model model) {
		Model obj = modelRepository.findOne(model.getId());
		XaResult<Model> xr = new XaResult<Model>();
		if (XaUtil.isNotEmpty(obj)) {
			obj.setName(model.getName());
			obj.setIdentify(model.getIdentify());
			obj.setDescription(model.getDescription());
			obj.setModifyTime(XaUtil.getToDayStr());
			obj.setModifyDescription(model.getDescription());
			obj = modelRepository.save(obj);
			xr.success(obj);
		} else {
			xr.error(XaConstant.Message.object_not_find);
		}

		return xr;
	}

	public XaResult<Model> deleteModel(Long modelId,Long modifyUser) {
		Model obj = modelRepository.findByIdAndStatusNot(modelId,XaDbStatus.DB_STATUS_DELETE);
		XaResult<Model> xr = new XaResult<Model>();
		if (XaUtil.isNotEmpty(obj)) {
			obj.setStatus(XaDbStatus.DB_STATUS_DELETE);
			obj.setModifyTime(XaUtil.getToDayStr());
			obj.setModifyUser(modifyUser);
			obj.setModifyDescription("删除模型");
			obj = modelRepository.save(obj);
			xr.success(obj);
		} else {
			xr.error(XaConstant.Message.object_not_find);
		}
		return xr;
	}

	
	public XaResult<Model> findModel(Long modelId) {
		Model obj = modelRepository.findByIdAndStatusNot(modelId,XaDbStatus.DB_STATUS_DELETE);
		XaResult<Model> xr = new XaResult<Model>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.success(obj);
		} else {
			xr.error(XaConstant.Message.object_not_find);
		}
		return xr;
	}

	
	public XaResult<Model> publishModel(Long modelId,Long modifyUser) {
		Model obj = modelRepository.findOne(modelId);
		XaResult<Model> xr = new XaResult<Model>();
		if (XaUtil.isNotEmpty(obj)) {
			obj.setStatus(XaDbStatus.DB_STATUS_PUBLISH);
			obj.setModifyUser(modifyUser);
			obj.setModifyTime(XaUtil.getToDayStr());
			obj.setModifyDescription("发布模型");
			obj = modelRepository.save(obj);
			xr.success(obj);
		} else {
			xr.error(XaConstant.Message.object_not_find);
		}

		return xr;
	}
	
	
}
