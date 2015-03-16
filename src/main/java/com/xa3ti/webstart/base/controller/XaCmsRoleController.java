package com.xa3ti.webstart.base.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

import com.xa3ti.webstart.base.constant.XaConstant;
import com.xa3ti.webstart.base.entity.XaCmsRole;
import com.xa3ti.webstart.base.entity.XaCmsUser;
import com.xa3ti.webstart.base.security.XaSecurityMetadataSourceService;
import com.xa3ti.webstart.base.service.XaCmsRoleService;
import com.xa3ti.webstart.base.service.XaCmsUserService;
import com.xa3ti.webstart.base.util.WebUitl;
import com.xa3ti.webstart.base.vo.MenuData;
import com.xa3ti.webstart.base.vo.TreeNode;


/**
 * @Title: XaCmsRoleController.java
 * @Package com.xa3ti.shengmilu.business.controller
 * @Description: 角色控制器
 * @author hchen
 * @date 2014年8月6日 上午9:40:34
 * @version V1.0
 */
@Controller
@RequestMapping("xaCmsRole")
public class XaCmsRoleController {

	@Autowired
	private XaCmsRoleService xaCmsRoleService;
	
	@Autowired
	private XaCmsUserService xaCmsUserService;
	
	/**
	 * @Title: showAllRole
	 * @Description: 分页显示角色
	 * @param nextPage
	 * @param sortDate
	 * @param jsonFilter
	 * @return    
	 */
	@ResponseBody
	@RequestMapping("showRoles/{nextPage}/{sortDate}/{jsonFilter}")
	public Page<XaCmsRole> showAllRole(@PathVariable Integer nextPage,
			@PathVariable String sortDate,
			@PathVariable String jsonFilter){
		Pageable pageable = WebUitl.buildPageRequest(nextPage, 10, sortDate);
		Map<String,Object> filterParams =  WebUitl.getParametersStartingWith(jsonFilter, "search_");
		filterParams.put("EQ_status", XaConstant.ResourcesStatus.status_normal);
		Page<XaCmsRole> data=xaCmsRoleService.findXaCmsRoleByConditon(filterParams, pageable);
		return data;
	}
	
	/**
	 * @Title: addRole
	 * @Description: 增加一条角色信息，并同时增加角色和资源的关系信息
	 * @param xaCmsRole
	 * @param resourceIds
	 * @return    
	 */
	@ResponseBody
	@RequestMapping(value="{resourceIds}",method=RequestMethod.POST)
	public String addRole(@RequestBody XaCmsRole xaCmsRole,@PathVariable String resourceIds){
		xaCmsRole =xaCmsRoleService.saveXaCmsRole(xaCmsRole, resourceIds);
		XaSecurityMetadataSourceService.reset();	//刷新security中的资源列表
		return "{\"status\":1,\"result\":\"新建角色成功\"}";
	}
	
	/**
	 * @Title: showResource
	 * @Description: 构造资源树
	 * @return    
	 */
	@ResponseBody
	@RequestMapping(value="showResource/{roleId}",method=RequestMethod.GET)
	public TreeNode showResource(@PathVariable long roleId){
		TreeNode tn=xaCmsRoleService.getResourceTreeNode(roleId);
		return tn;
	}
	
	@ResponseBody
	@RequestMapping(value="showRole/{roleId}",method=RequestMethod.GET)
	public XaCmsRole showRole(@PathVariable long roleId){
		XaCmsRole xaCmsRole=xaCmsRoleService.findRoleById(roleId);
		return xaCmsRole;
	}
	
	/**
	 * @Title: addRole
	 * @Description: 增加一条角色信息，并同时增加角色和资源的关系信息
	 * @param xaCmsRole
	 * @param resourceIds
	 * @return    
	 */
	@ResponseBody
	@RequestMapping(value="{resourceIds}/update",method=RequestMethod.POST)
	public String updateRole(@RequestBody XaCmsRole xaCmsRole,@PathVariable String resourceIds,HttpServletRequest request){
		HttpSession session = request.getSession();
		XaCmsUser user = (XaCmsUser) session.getAttribute(XaConstant.SessionKey.currentUser);
		xaCmsRole =xaCmsRoleService.updateXaCmsRole(xaCmsRole, resourceIds);
		MenuData result =xaCmsUserService.createUserResourceByUserId(user.getUserId());		//更新菜单
		session.setAttribute(XaConstant.SessionKey.currentMenuData, result);
		XaSecurityMetadataSourceService.reset();	//刷新security中的资源列表
		return "{\"status\":1,\"result\":\"修改角色成功\"}";
	}
	
	/**
	 * @Title: delRole
	 * @Description: 批量删除角色，删除角色同时删除用户和角色之间的关系。
	 * @param ids
	 * @return    
	 */
	@ResponseBody
	@RequestMapping(value="role/{ids}",method=RequestMethod.DELETE)
	public String delRole(@PathVariable String ids){
		xaCmsRoleService.delXaCmsRole(ids);
		XaSecurityMetadataSourceService.reset();	//刷新security中的资源列表
		return "{\"status\":1,\"result\":\"修改角色成功\"}";
	}
	
	@ResponseBody
	@RequestMapping(value="checkRole",method=RequestMethod.GET)
	public String checkRoleNameExist(@RequestParam(required=true) String roleName,@RequestParam(required=false) Long roleId){
		XaCmsRole role = xaCmsRoleService.getRoleByName(roleName);
		if(role==null || roleId==role.getRoleId()){
			return "{\"ok\":\"可以使用\"}";
		}else{
			return  "{\"error\":\"已存在\"}";
		}
	}
	
}

