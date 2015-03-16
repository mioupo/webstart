package com.xa3ti.webstart.base.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.xa3ti.webstart.base.constant.XaConstant;
import com.xa3ti.webstart.base.entity.XaCmsResource;
import com.xa3ti.webstart.base.security.XaSecurityMetadataSourceService;
import com.xa3ti.webstart.base.service.XaCmsResourceService;
import com.xa3ti.webstart.base.util.XaResult;
import com.xa3ti.webstart.base.util.XaUtil;
import com.xa3ti.webstart.base.vo.SelectOptionVo;

/**
 * @Title: XaCmsResourceController.java
 * @Package com.xa3ti.shengmilu.business.controller
 * @Description: 资源控制器
 * @author hchen
 * @date 2014年8月2日 上午11:42:16
 * @version V1.0
 */
@Controller
@RequestMapping("xaCmsResource")
public class XaCmsResourceController {
	
	@Autowired
	private XaCmsResourceService xaCmsResourceService;

	/**
	 * @Title: getAllResource
	 * @Description: 列表查询所有资源
	 * @param request	
	 * @param response
	 * @param nextPage	下一次，页数从0开始
	 * @param sortDate	排序规则
	 * @param jsonFilter	查询过滤条件
	 * @return    
	 */
	@ResponseBody
	@RequestMapping(value="showResource")
	public XaResult<Page<XaCmsResource>> getAllResource(@RequestParam Integer nextPage,
			@RequestParam Integer pageSize,
			@RequestParam String resourceName){
		if(XaUtil.isEmpty(resourceName)){
			resourceName=null;
		}
		XaResult<Page<XaCmsResource>> data=xaCmsResourceService.findXaCmsResourceByConditon(resourceName,nextPage,pageSize);
		return data;
	}
	
	/**
	 * @Title: saveResource
	 * @Description: 保存资源
	 * @param request
	 * @param response
	 * @param xaCmsResource
	 * @return    
	 */
	@ResponseBody
	@RequestMapping(method=RequestMethod.POST)
	public String saveResource(@RequestBody XaCmsResource xaCmsResource){
		xaCmsResource.setOrderNum(100);			//设置默认排序
		xaCmsResource.setStatus(XaConstant.ResourcesStatus.status_normal);
		xaCmsResource= xaCmsResourceService.saveXaCmsResource(xaCmsResource);
		XaSecurityMetadataSourceService.reset();	//刷新security中的资源列表
		return "{\"status\":1,\"result\":\"新建资源成功\"}";
	}
	
	/**
	 * @Title: getParentResource
	 * @Description: 返回一级资源
	 * @return    
	 */
	@ResponseBody
	@RequestMapping(value="showParent/{rid}",method=RequestMethod.GET)
	public SelectOptionVo getParentResource(@PathVariable Long rid){
		List<XaCmsResource> result=xaCmsResourceService.getMenuLevelResource();
		SelectOptionVo sov=new SelectOptionVo();
		sov.setOptionItem(result);
		if(rid!=null){
			XaCmsResource resource=xaCmsResourceService.findXaCmsResourceById(rid);
			sov.setSelectedId(resource);
		}else{
			sov.setSelectedId("");
		}
		return sov;
	}
	
	/**
	 * @Title: batchDelResource
	 * @Description: 批量删除资源信息，假删资源。注意，删除资源时，需要将角色和资源之间的关系同时删掉
	 * @param ids
	 * @param request
	 * @param response
	 * @return    
	 */
	@ResponseBody
	@RequestMapping(value="resource/{ids}",method=RequestMethod.DELETE)
	public String batchDelResource(@PathVariable String ids,HttpServletRequest request,HttpServletResponse response){
		Map<String, String> map = JSON.parseObject(ids,HashMap.class);
		String ids1= map.get("ids");
		int code = xaCmsResourceService.delXaCmsResource(ids1);
		return "{\"status\":"+code+",\"result\":\"删除资源成功\"}";
	}
	
	/**
	 * @Title: updateResource
	 * @Description: 更新一条资源记录
	 * @param id
	 * @param request
	 * @param response
	 * @return    
	 */
	@ResponseBody
	@RequestMapping(value="update",method=RequestMethod.PUT)
	public String updateResource(@RequestBody XaCmsResource xaCmsResource,HttpServletRequest request,HttpServletResponse response){
		XaCmsResource oldResource=xaCmsResourceService.findXaCmsResourceById(xaCmsResource.getResourceId());
		String result="";
		if(oldResource!=null){
			xaCmsResourceService.updateXaCmsResource(xaCmsResource);
			XaSecurityMetadataSourceService.reset();	//刷新security中的资源列表
			result="{\"status\":1,\"result\":\"修改资源成功\"}";
		}else{
			result="{\"status\":0,\"result\":\"修改资源不成功，"+xaCmsResource.getResourceId()+"对应的资源不存在\"}";
		}
		return result;
	}
}

