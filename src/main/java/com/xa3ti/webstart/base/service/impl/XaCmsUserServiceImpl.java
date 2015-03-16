package com.xa3ti.webstart.base.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xa3ti.webstart.base.constant.XaConstant;
import com.xa3ti.webstart.base.entity.XaCmsResource;
import com.xa3ti.webstart.base.entity.XaCmsRole;
import com.xa3ti.webstart.base.entity.XaCmsRoleResource;
import com.xa3ti.webstart.base.entity.XaCmsUser;
import com.xa3ti.webstart.base.entity.XaCmsUserRole;
import com.xa3ti.webstart.base.repository.XaCmsResourceRepository;
import com.xa3ti.webstart.base.repository.XaCmsRoleRepository;
import com.xa3ti.webstart.base.repository.XaCmsRoleResourceRepository;
import com.xa3ti.webstart.base.repository.XaCmsUserRepository;
import com.xa3ti.webstart.base.repository.XaCmsUserRoleRepository;
import com.xa3ti.webstart.base.service.XaCmsUserService;
import com.xa3ti.webstart.base.util.DynamicSpecifications;
import com.xa3ti.webstart.base.util.SearchFilter;
import com.xa3ti.webstart.base.vo.FirstLevelMenu;
import com.xa3ti.webstart.base.vo.MenuData;
import com.xa3ti.webstart.base.vo.SecondLevelMenu;


/**
 * @Title: XaCmsUserServiceImpl.java
 * @Package com.xa3ti.shengmilu.business.service.impl
 * @Description: 后台用户服务类
 * @author hchen
 * @date 2014年8月2日 上午9:46:30
 * @version V1.0
 */
@Service("xaCmsUserService")
public class XaCmsUserServiceImpl implements XaCmsUserService {

	private static final Logger log=Logger.getLogger(XaCmsUserServiceImpl.class);
	
	@Autowired
	private XaCmsUserRepository xaCmsUserRepository;
	
	@Autowired
	private XaCmsRoleRepository xaCmsRoleRepository;
	
	@Autowired
	private XaCmsUserRoleRepository xaCmsUserRoleRepository;
	
	@Autowired
	private XaCmsRoleResourceRepository xaCmsRoleResourceRepository;
	
	@Autowired
	private XaCmsResourceRepository xaCmsResourceRepository;
	
	
	@Transactional(readOnly=false)
	public XaCmsUser saveCmsUser(XaCmsUser xaCmsUser,String roleIds) {
		xaCmsUser.setStatus(XaConstant.UserStatus.status_normal);
		xaCmsUser =xaCmsUserRepository.save(xaCmsUser);
		log.info("增加一条用户记录："+xaCmsUser.getUserId());
		createUserRole(xaCmsUser.getUserId(), roleIds);
		
		return xaCmsUser;
	}

	/**
	 * @Title: createUserRole
	 * @Description: 创建用户角色关系
	 * @param userId
	 * @param roleIds    
	 */
	private void createUserRole(long userId, String roleIds) {
		String[] ids=roleIds.split(",");
		for (int i = 0; i < ids.length; i++) {
			long roleId=Long.parseLong(ids[i]);
			XaCmsRole xcr = xaCmsRoleRepository.findOne(roleId);
			if(xcr!=null && XaConstant.RoleStatus.status_normal==xcr.getStatus()){
				XaCmsUserRole xur=new XaCmsUserRole();
				xur.setRoleId(roleId);
				xur.setUserId(userId);
				xaCmsUserRoleRepository.save(xur);
				log.info("增加一条用户角色关系记录："+xur.getId());
			}
		}
	}

	public Page<XaCmsUser> getCmsUserByCondition(
			Map<String, Object> filterParams, Pageable pageable) {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		return xaCmsUserRepository.findAll(DynamicSpecifications.bySearchFilter(filters.values(), XaCmsUser.class), pageable);
	}

	@Transactional(readOnly=false)
	public void delCmsUserByIds(String uids) {
		String[] ids=uids.split(",");
		for (int j = 0; j < ids.length; j++) {			
			XaCmsUser xaCmsUser=xaCmsUserRepository.findOne(Long.parseLong(ids[j]));
			if(xaCmsUser!=null){
				xaCmsUser.setStatus(XaConstant.UserStatus.status_delete);
				xaCmsUserRepository.save(xaCmsUser);
			}
		}
	}

	public int updateCmsUserPassword(long uid, String oldPassword,
			String newPassword) {
		XaCmsUser xaCmsUser=xaCmsUserRepository.findOne(uid);
		if(xaCmsUser==null) return -1;
		if(oldPassword.equals(xaCmsUser.getPassword())){
			xaCmsUser.setPassword(newPassword);
			xaCmsUserRepository.save(xaCmsUser);
			return 1;
		}else{			
			return 0;
		}
	}

	public XaCmsUser updateCmsUserNotPassword(XaCmsUser xaCmsUser,String rids) {
		XaCmsUser oldXaCmsUser=xaCmsUserRepository.findOne(xaCmsUser.getUserId());
		if(oldXaCmsUser!=null){
			oldXaCmsUser.setDescription(xaCmsUser.getDescription());
			oldXaCmsUser.setEmail(xaCmsUser.getEmail());
			oldXaCmsUser.setMobile(xaCmsUser.getMobile());
			oldXaCmsUser.setRealName(xaCmsUser.getRealName());
			xaCmsUserRepository.save(oldXaCmsUser);
			log.info("修改用户："+oldXaCmsUser.getUserId()+"成功");
			//删除原来的角色关系
			List<XaCmsUserRole> xcurList=xaCmsUserRoleRepository.findXacmsUserRoleByUserId(oldXaCmsUser.getUserId());
			if(xcurList.size()>0) xaCmsUserRoleRepository.delete(xcurList);
			log.info("删除用户角色关系："+oldXaCmsUser.getUserId()+"成功");
			createUserRole(xaCmsUser.getUserId(), rids);
			
		}
		return oldXaCmsUser;
	}

	public List<XaCmsRole> getMyXaCmsRoleListByUserId(long uid) {
		return null;
	}

	public List<XaCmsResource> getMyXaCmsResourceListByUserId(long uid) {
		// TODO Auto-generated method stub
		return null;
	}

	public int saveUserRoleList(int uid, Integer[] roleIds) {
		return 0;
	}

	public Map<String, Object> getUserAndRole(long userId) {
		Map<String, Object> map=new HashMap<String, Object>();
		if(userId==0){
			map.put("user", null);
			map.put("userRole", null);
		}else{
			XaCmsUser xcu=xaCmsUserRepository.findOne(userId);
			map.put("user", xcu);
			List<XaCmsUserRole> xcurList=xaCmsUserRoleRepository.findXacmsUserRoleByUserId(userId);
			map.put("userRole", xcurList);
		}
		List<XaCmsRole> xcrList= xaCmsRoleRepository.findAllXaCmsRole(XaConstant.RoleStatus.status_normal);
		map.put("role", xcrList);
		return map;
	}

	public XaCmsUser findXaCmsUserByUserName(String userName, int status) {
		XaCmsUser user=xaCmsUserRepository.findByUserName(userName,status);
		return user;
	}

	public MenuData createUserResourceByUserId(long userId) {
		
		List<XaCmsUserRole> urList=xaCmsUserRoleRepository.findXacmsUserRoleByUserId(userId); //获取用户的角色关系记录，遍历关系，查找对应的资源
		List<Long> resourceIdList=new ArrayList<Long>(); //该用户拥有的资源ID集合
		for (int i = 0; i < urList.size(); i++) {
			XaCmsUserRole xur = urList.get(i);
			//角色对应的资源关系记录
			List<XaCmsRoleResource> xcrrList = xaCmsRoleResourceRepository.findRoleResourceByRoleId(xur.getRoleId());
			for (int j = 0; j < xcrrList.size(); j++) {
				XaCmsRoleResource xcrr =xcrrList.get(j);
				//将该角色对应的资源ID集合去掉重复后存放在资源列表中。
				if(!resourceIdList.contains(xcrr.getResourceId()))resourceIdList.add(xcrr.getResourceId());
			}
		}
		//获取所有的一级菜单资源
		List<XaCmsResource> firstLevelResourceList=xaCmsResourceRepository.findParentResourceByStatus(XaConstant.ResourcesStatus.status_normal,XaConstant.ResourceShowType.menu_level);
		//根据上面计算出的用户拥有的资源ID集合，和系统中所有的一级菜单比较，拿到拥有的一级资源集合
		List<XaCmsResource> returnFistResourceList=new ArrayList<XaCmsResource>();		//该角色对应的一级菜单
		for (XaCmsResource xaCmsResource : firstLevelResourceList) {
			if(resourceIdList.contains(xaCmsResource.getResourceId())){
				returnFistResourceList.add(xaCmsResource);
			}
		}
		List<FirstLevelMenu> fisrstList=new ArrayList<FirstLevelMenu>();
		for (XaCmsResource xaCmsResource : returnFistResourceList) {
			//遍历拥有的一级资源，查询该资源下的所有有用的二级资源
			List<XaCmsResource>  sencondXaCmsResourceList=xaCmsResourceRepository.findResourceByParentIdAndStatus(xaCmsResource.getResourceId(), XaConstant.ResourcesStatus.status_normal);
			List<SecondLevelMenu> slMenuList=new ArrayList<SecondLevelMenu>();
			for (XaCmsResource xaCmsResource2 : sencondXaCmsResourceList) {
				if(resourceIdList.contains(xaCmsResource2.getResourceId())){
					//和拥有的资源ID比较，如果包含，将该资源转换成二级菜单
					String menuUrl="";
//					if(xaCmsResource2.getResourceUrl()!=null && xaCmsResource2.getResourceUrl().length()>1){
//						menuUrl=xaCmsResource2.getResourceUrl().substring(1);	//去掉第一个“/”
//					}
					SecondLevelMenu slmenu=new SecondLevelMenu(xaCmsResource2.getResourceId()+"",xaCmsResource2.getResourceName(),xaCmsResource2.getResourceUrl());
					slMenuList.add(slmenu);
				}
			}
			FirstLevelMenu flmenu=new FirstLevelMenu();
			flmenu.setId(xaCmsResource.getResourceId()+"");
			flmenu.setText(xaCmsResource.getResourceName());
			flmenu.setIcon("");
			flmenu.setList(slMenuList);
			
			fisrstList.add(flmenu);
		}
		
		//要返回的菜单对象
		MenuData menuData=new MenuData();
		menuData.setTitle("导航栏");
		menuData.setItems(fisrstList);
		return menuData;
	}

	
}

