package com.xa3ti.webstart.base.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xa3ti.webstart.base.constant.XaConstant;
import com.xa3ti.webstart.base.entity.XaCmsUser;
import com.xa3ti.webstart.base.security.XaSecurityMetadataSourceService;
import com.xa3ti.webstart.base.security.XaUserDetails;
import com.xa3ti.webstart.base.service.XaCmsUserService;
import com.xa3ti.webstart.base.util.WebUitl;
import com.xa3ti.webstart.base.vo.MenuData;


/**
 * @Title: XaCmsUserController.java
 * @Package com.xa3ti.shengmilu.business.controller
 * @Description: 后台用户控制器
 * @author hchen
 * @date 2014年8月7日 上午10:38:14
 * @version V1.0
 */
@Controller
@RequestMapping("xaCmsUser")
public class XaCmsUserController {
	
	@Autowired
	private XaCmsUserService xaCmsUserService;

	/**
	 * @Title: showCmsUser
	 * @Description: 分页查询用户数据
	 * @param nextPage
	 * @param sortDate
	 * @param jsonFilter
	 * @return    
	 */
	@ResponseBody
	@RequestMapping(value ="showCmsUser",method=RequestMethod.GET)
	public Page<XaCmsUser> showCmsUser(@RequestParam(defaultValue="0") Integer nextPage,@RequestParam(defaultValue="10") Integer pageSize,
			@RequestParam String sortDate,
			@RequestParam String jsonFilter){
		
		Pageable pageable = WebUitl.buildPageRequest(nextPage, pageSize, sortDate);
		Map<String,Object> filterParams =  WebUitl.getParametersStartingWith(jsonFilter, "search_");
		filterParams.put("EQ_status", XaConstant.UserStatus.status_normal);
		Page<XaCmsUser> data=xaCmsUserService.getCmsUserByCondition(filterParams, pageable);
		return data;
	}
	
	/**
	 * @Title: addUser
	 * @Description: 新增用户信息，同时增加用户和角色关系
	 * @param xaCmsUser
	 * @return    
	 */
	@ResponseBody
	@RequestMapping(value="addUser/{roleIds}",method=RequestMethod.POST)
	public String addUser(@RequestBody XaCmsUser xaCmsUser,@PathVariable String roleIds){
		xaCmsUserService.saveCmsUser(xaCmsUser,roleIds);
		XaSecurityMetadataSourceService.reset();	//刷新security中的资源列表
		return "{\"status\":1,\"result\":\"新建用户成功\"}";
	}
	
	/**
	 * @Title: updateUserNotModifyPassword
	 * @Description: 修改除密码以外的属性
	 * @param xaCmsUser
	 * @param roleIds  新的角色ID
	 * @return    
	 */
	@ResponseBody
	@RequestMapping(value="update/{roleIds}",method=RequestMethod.POST)
	public String updateUserNotModifyPassword(@RequestBody XaCmsUser xaCmsUser,@PathVariable String roleIds){
		xaCmsUserService.updateCmsUserNotPassword(xaCmsUser,roleIds);
		XaSecurityMetadataSourceService.reset();	//刷新security中的资源列表
		return "{\"status\":1,\"result\":\"修改用户成功\"}";
	}
	
	/**
	 * @Title: getUserAndUserRole
	 * @Description: 获取用户角色相关属性，方便在新增和修改时显示使用
	 * @param userId
	 * @return    
	 */
	@ResponseBody
	@RequestMapping(value="{userId}",method=RequestMethod.GET)
	public Map<String,Object> getUserAndUserRole(@PathVariable long userId){
		return xaCmsUserService.getUserAndRole(userId);
	}
	
	/**
	 * @Title: deleteUserByIds
	 * @Description: 根据ids批量假删该用户
	 * @param ids
	 * @return    
	 */
	@ResponseBody
	@RequestMapping(value="user/{ids}",method=RequestMethod.DELETE)
	public String deleteUserByIds(@PathVariable String ids){
		xaCmsUserService.delCmsUserByIds(ids);
		XaSecurityMetadataSourceService.reset();	//刷新security中的资源列表
		return "{\"status\":1,\"result\":\"删除用户成功\"}";
	}
	
	/**
	 * @Title: getMenuDate
	 * @Description: 获取菜单并存放到session中。会将用户信息存放到session中,返回菜单树和用户信息
	 * @param request
	 * @return    
	 */
	@ResponseBody
	@RequestMapping(value="showMenu",method=RequestMethod.GET)
	public Map<String, Object> getMenuDate(HttpServletRequest request){
		
		Map<String, Object> map=new HashMap<String, Object>();
		HttpSession session = request.getSession();
		MenuData result=(MenuData) session.getAttribute(XaConstant.SessionKey.currentMenuData);
		if(result==null){		
			Object userDetails = SecurityContextHolder.getContext()
					.getAuthentication().getPrincipal();
			String userName="";
			if( userDetails instanceof String){
				userName = (String)userDetails;
			}else if(userDetails instanceof XaUserDetails){
				userName = ((XaUserDetails)userDetails).getUsername();
			}
			XaCmsUser user=xaCmsUserService.findXaCmsUserByUserName(userName, XaConstant.UserStatus.status_normal);
			if(user!=null){			
				user.setPassword("");		//返回到前台的数据将密码清除掉。
				result =xaCmsUserService.createUserResourceByUserId(user.getUserId());
				session.setAttribute(XaConstant.SessionKey.currentMenuData, result);
				session.setAttribute(XaConstant.SessionKey.currentUser, user);
				map.put(XaConstant.SessionKey.currentMenuData, result);
				map.put(XaConstant.SessionKey.currentUser, user);
			}
		}else{
			map.put(XaConstant.SessionKey.currentMenuData, result);
			XaCmsUser user=(XaCmsUser) session.getAttribute(XaConstant.SessionKey.currentUser);
			map.put(XaConstant.SessionKey.currentUser, user);
		}
		return map;
	}
	
	/**
	 * @Title: checkUserNameExist
	 * @Description: 检查用户名是否存在
	 * @param userName
	 * @return    
	 */
	@ResponseBody
	@RequestMapping(value="checkUserName",method=RequestMethod.GET)
	public String checkUserNameExist(@RequestParam String userName){
		XaCmsUser user = xaCmsUserService.findXaCmsUserByUserName(userName, XaConstant.UserStatus.status_normal);
		
		if(user==null){
			return "{\"ok\":\"可以使用\"}";
		}else{
			return  "{\"error\":\"已存在\"}";
		}
	}
	
	/**
	 * @Title: updatePassword
	 * @Description: 修改用户密码
	 * @param userId
	 * @param newPassword
	 * @param oldPassword
	 * @return    
	 */
	@ResponseBody
	@RequestMapping(value="update/{userId}/{newPassword}/{oldPassword}",method=RequestMethod.POST)
	public String updatePassword(@PathVariable long userId,@PathVariable String newPassword,@PathVariable String oldPassword){
		int result =xaCmsUserService.updateCmsUserPassword(userId, oldPassword, newPassword);
		if(result==-1){
			return "{\"status\":-1,\"result\":\"修改密码失败，该用户不存\"}";
		}else if(result==0){
			return "{\"status\":0,\"result\":\"修改密码失败，旧密码输入错误\"}";
		}else{
			return "{\"status\":1,\"result\":\"修改密码成功\"}";
		}
		//此处目前没有修改session中的用户，暂时不会出现问题
	}
}

