package com.xa3ti.webstart.base.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xa3ti.webstart.base.constant.XaConstant;
import com.xa3ti.webstart.base.entity.XaCmsResource;
import com.xa3ti.webstart.base.entity.XaCmsRoleResource;
import com.xa3ti.webstart.base.repository.XaCmsResourceRepository;
import com.xa3ti.webstart.base.repository.XaCmsRoleResourceRepository;
import com.xa3ti.webstart.base.service.XaCmsResourceService;
import com.xa3ti.webstart.base.util.MyPage;
import com.xa3ti.webstart.base.util.SqlResultUtil;
import com.xa3ti.webstart.base.util.XaResult;
import com.xa3ti.webstart.base.util.XaUtil;

/**
 * @Title: XaCmsResourceServiceImple.java
 * @Package com.xa3ti.shengmilu.business.service.impl
 * @Description: 资源服务实现类
 * @author hchen
 * @date 2014年8月2日 上午11:08:55
 * @version V1.0
 */
@Service("xaCmsResourceService")
public class XaCmsResourceServiceImple implements XaCmsResourceService {

	private static final Logger log=Logger.getLogger(XaCmsResourceServiceImple.class);
	@Autowired
	private XaCmsResourceRepository xaCmsResourceRepository;
	
	@Autowired
	private XaCmsRoleResourceRepository xaCmsRoleResourceRepository;
	
	public XaCmsResource saveXaCmsResource(XaCmsResource xaCmsResource) {
		return xaCmsResourceRepository.save(xaCmsResource);
	}

	@Transactional(readOnly=false)
	public int delXaCmsResource(String rids) {
		String[] resourceIds=rids.split(",");
		//检查要删除的ID里面是否有未删除子节点的父资源，如果有，就不能直接删除
		boolean hasChild=false;
		for (int i = 0; i < resourceIds.length; i++) {
			Long rid=Long.valueOf(resourceIds[i]);
			//判断要删除的资源是否有一级资源
			XaCmsResource xaCmsResource=xaCmsResourceRepository.findOne(rid);
			//检查当前资源是否有子资源
			List<XaCmsResource> _resourceList = xaCmsResourceRepository.findResourceByParentIdAndStatus(xaCmsResource.getResourceId(), XaConstant.ResourcesStatus.status_normal);
			if(XaUtil.isNotEmpty(_resourceList)){
				hasChild=true;
				break;
			}
		}
		if(hasChild){
			log.info("假删资源失败，还有子资源未被删除");
			return -1;
		}
		
		for (int i = 0; i < resourceIds.length; i++) {
			Long rid=Long.valueOf(resourceIds[i]);
			XaCmsResource xaCmsResource=xaCmsResourceRepository.findOne(rid);
			if(xaCmsResource!=null){
				xaCmsResource.setStatus(XaConstant.ResourcesStatus.status_delete);
				xaCmsResourceRepository.save(xaCmsResource);
				//删资源时，需要将关联该资源的角色的关系删除
				List<XaCmsRoleResource> xcrrList=xaCmsRoleResourceRepository.findRoleResourceByResourceId(xaCmsResource.getResourceId());
				if(XaUtil.isNotEmpty(xcrrList))	xaCmsRoleResourceRepository.delete(xcrrList);
				log.info("假删资源 "+xaCmsResource.getResourceName()+" 信息成功");
			}
		}
		return 1;
	}

	@Transactional(readOnly=false)
	public XaCmsResource updateXaCmsResource(XaCmsResource xaCmsResource) {
		XaCmsResource oldResource=xaCmsResourceRepository.findOne(xaCmsResource.getResourceId());
		if(oldResource!=null){
			oldResource.setParentId(xaCmsResource.getParentId());
			oldResource.setResourceName(xaCmsResource.getResourceName());
			oldResource.setResourceUrl(xaCmsResource.getResourceUrl());
			oldResource.setShowType(xaCmsResource.getShowType());
			xaCmsResourceRepository.save(oldResource);
		}
		return oldResource;
	}

	public XaResult<Page<XaCmsResource>> findXaCmsResourceByConditon(String resourceName,Integer nextPage,Integer pageSize) {
		XaResult<Page<XaCmsResource>> xr1=new XaResult<Page<XaCmsResource>>();
		int fromIndex = (nextPage * pageSize);
		int endIndex = pageSize;
		List<Object[]> resourceList=xaCmsResourceRepository.findResourceByResourceName(resourceName, XaConstant.Status.valid,fromIndex,endIndex);
		Object resourceCount=xaCmsResourceRepository.findResourceByResourceNameCount(resourceName, XaConstant.Status.valid);
		List<XaCmsResource> bbslogList=new ArrayList<XaCmsResource>();
		for (Object[] obj : resourceList) {
			XaCmsResource xaCmsResource=new XaCmsResource();
			xaCmsResource.setResourceId(SqlResultUtil.getSqlResultLong(obj[0]));
			xaCmsResource.setParentResourceName(SqlResultUtil.getSqlResultString(obj[1]));
			xaCmsResource.setResourceName(SqlResultUtil.getSqlResultString(obj[2]));
			xaCmsResource.setResourceUrl(SqlResultUtil.getSqlResultString(obj[3]));
			xaCmsResource.setShowType(SqlResultUtil.getSqlResultInteger(obj[4]));
			bbslogList.add(xaCmsResource);
		}
		final int rowCount = XaUtil.isEmpty(resourceCount)?0:SqlResultUtil.getSqlResultInteger(resourceCount);
		Page<XaCmsResource> mypage=new MyPage<XaCmsResource>(nextPage, pageSize, bbslogList, rowCount);
		xr1.setObject(mypage);
		
		return xr1;
	}

	public List<XaCmsResource> getParentResouces(int status,int showType) {
		return xaCmsResourceRepository.findParentResourceByStatus(status,showType);
	}

	public XaCmsResource findXaCmsResourceById(long rid) {
		return xaCmsResourceRepository.findOne(rid);
	}

	public List<XaCmsResource> getMenuLevelResource() {
		return xaCmsResourceRepository.findResourceByShowTypeAndStatus(XaConstant.ResourceShowType.menu_level, XaConstant.ResourcesStatus.status_normal);
	}
	
	

}

