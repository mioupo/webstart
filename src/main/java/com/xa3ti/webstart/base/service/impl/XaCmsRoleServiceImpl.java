package com.xa3ti.webstart.base.service.impl;

import java.util.ArrayList;
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
import com.xa3ti.webstart.base.entity.XaCmsUserRole;
import com.xa3ti.webstart.base.repository.XaCmsResourceRepository;
import com.xa3ti.webstart.base.repository.XaCmsRoleRepository;
import com.xa3ti.webstart.base.repository.XaCmsRoleResourceRepository;
import com.xa3ti.webstart.base.repository.XaCmsUserRoleRepository;
import com.xa3ti.webstart.base.service.XaCmsRoleService;
import com.xa3ti.webstart.base.util.DynamicSpecifications;
import com.xa3ti.webstart.base.util.SearchFilter;
import com.xa3ti.webstart.base.util.XaUtil;
import com.xa3ti.webstart.base.vo.NodeStatus;
import com.xa3ti.webstart.base.vo.TreeNode;

/**
 * @Title: XaCmsRoleServiceImpl.java
 * @Package com.xa3ti.shengmilu.business.service.impl
 * @Description: 角色服务类
 * @author hchen
 * @date 2014年8月2日 上午10:25:17
 * @version V1.0
 */
@Service("xaCmsRoleService")
public class XaCmsRoleServiceImpl implements XaCmsRoleService {

	private static final Logger log=Logger.getLogger(XaCmsRoleServiceImpl.class);
	@Autowired
	private XaCmsRoleRepository xaCmsRoleRepository;
	
	@Autowired
	private XaCmsRoleResourceRepository xaCmsRoleResourceRepository;
	
	@Autowired
	private XaCmsResourceRepository xaCmsResourceRepository;
	
	@Autowired
	private XaCmsUserRoleRepository xaCmsUserRoleRepository;
	
	
	@Transactional(readOnly=false)
	public XaCmsRole saveXaCmsRole(XaCmsRole xaCmsRole,String resourceIds) {
		xaCmsRole.setStatus(XaConstant.RoleStatus.status_normal);
		xaCmsRole =xaCmsRoleRepository.save(xaCmsRole);
		createRoleResource(xaCmsRole.getRoleId(), resourceIds);
		return xaCmsRole;
	}

	/**
	 * @Title: createRoleResource
	 * @Description: 创建角色和资源关系
	 * @param roleId
	 * @param resourceIds    
	 */
	@Transactional(readOnly=false)
	private void createRoleResource(long roleId, String resourceIds) {
		String[] ids=resourceIds.split(",");
		List<Long> parentIdList=new ArrayList<Long>();
		for (int i = 0; i < ids.length; i++) {
			if("0".equals(ids[i])) continue;	//去掉虚拟的根节点
			XaCmsRoleResource xaCmsRoleResource=new XaCmsRoleResource();
			xaCmsRoleResource.setRoleId(roleId);
			Long resourceId = Long.parseLong(ids[i]);
			XaCmsResource _resource = xaCmsResourceRepository.findOne(resourceId);
			//把父资源也加入到该角色的资源中
			if(XaUtil.isNotEmpty(_resource.getParentId())){
				if(!parentIdList.contains(_resource.getParentId())){
					XaCmsRoleResource parentRoleResource=new XaCmsRoleResource();
					parentRoleResource.setRoleId(roleId);
					parentRoleResource.setResourceId(_resource.getParentId());
					xaCmsRoleResourceRepository.save(parentRoleResource);
					parentIdList.add(_resource.getParentId());
				}
			}
			
			xaCmsRoleResource.setResourceId(resourceId);
			xaCmsRoleResourceRepository.save(xaCmsRoleResource);
		}
	}

	@Transactional(readOnly=false)
	public void delXaCmsRole(String rids) {
		String[] ids=rids.split(",");
		for (int i = 0; i < ids.length; i++) {	
			long roleId=Long.parseLong(ids[i]);
			XaCmsRole xaCmsRole=xaCmsRoleRepository.findOne(roleId);
			if(xaCmsRole!=null){
				xaCmsRole.setStatus(XaConstant.RoleStatus.status_delete);
				xaCmsRoleRepository.save(xaCmsRole);
				List<XaCmsUserRole> urList = xaCmsUserRoleRepository.findXacmsUserRoleByRoleId(roleId);
				List<XaCmsRoleResource> rrList=xaCmsRoleResourceRepository.findRoleResourceByRoleId(roleId);
				if(urList!=null && urList.size()>0) xaCmsUserRoleRepository.delete(urList);
				if(XaUtil.isNotEmpty(rrList)) xaCmsRoleResourceRepository.delete(rrList);
				log.info("删除角色："+xaCmsRole.getRoleName()+" 成功");
			}
		}
	}

	@Transactional(readOnly=false)
	public XaCmsRole updateXaCmsRole(XaCmsRole xaCmsRole,String resourceIds) {
		XaCmsRole oldXaCmsRole=xaCmsRoleRepository.findOne(xaCmsRole.getRoleId());
		if(oldXaCmsRole!=null){
			oldXaCmsRole.setRoleName(xaCmsRole.getRoleName());
			oldXaCmsRole.setRoleDesc(xaCmsRole.getRoleDesc());
			xaCmsRole =xaCmsRoleRepository.save(oldXaCmsRole);
		}
		if(!XaUtil.isEmpty(resourceIds)){
			List<XaCmsRoleResource> rrList = xaCmsRoleResourceRepository.findRoleResourceByRoleId(xaCmsRole.getRoleId());
			xaCmsRoleResourceRepository.delete(rrList);
			createRoleResource(xaCmsRole.getRoleId(),resourceIds);
		}
		return xaCmsRole;
	}

	public Page<XaCmsRole> findXaCmsRoleByConditon(
			Map<String, Object> filterParams, Pageable pageable) {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		return xaCmsRoleRepository.findAll(DynamicSpecifications.bySearchFilter(filters.values(), XaCmsRole.class),pageable);
	}

	public int saveRoleResourceList(long rid, Integer[] resourceIds) {
		return 0;
	}

	public List<XaCmsResource> getMyResourceByRoldId(long rid) {
		return null;
	}

	public TreeNode getResourceTreeNode(long roleId) {
		//当roleId为0时，表示新增，新增时，所有的资源都不会被选中。
		List<XaCmsRoleResource>  roleResourceList=xaCmsRoleResourceRepository.findRoleResourceByRoleId(roleId);
		List<Long>  myResourceIdList=new ArrayList<Long>();	//存放当前角色的资源ID，如1，2，3，4
		for (XaCmsRoleResource xaCmsRoleResource : roleResourceList) {
			long resourceId=xaCmsRoleResource.getResourceId();
			if(!myResourceIdList.contains(resourceId)) myResourceIdList.add(resourceId);
		}
		
		
		List<TreeNode> children1=new ArrayList<TreeNode>();	//一级资源
		
		List<XaCmsResource>  pageResourceList=xaCmsResourceRepository.findParentResourceByStatus( XaConstant.ResourcesStatus.status_normal,XaConstant.ResourceShowType.page_level);	//页面级资源
		List<XaCmsResource> rootMenuResourceList=xaCmsResourceRepository.findParentResourceByStatus(XaConstant.ResourcesStatus.status_normal,XaConstant.ResourceShowType.menu_level);	//一级菜单资源
		List<XaCmsResource> firstResourceList=new ArrayList<XaCmsResource>();
		firstResourceList.addAll(pageResourceList);
		firstResourceList.addAll(rootMenuResourceList);
		for (XaCmsResource xaCmsResource : firstResourceList) {
			
			
			boolean firstNsSelected=true;   //如果父资源下所有的子资源都勾选了，则该父资源才能被勾选,一级资源是否被勾选标识位
			List<TreeNode> children2=new ArrayList<TreeNode>();		//菜单级资源
			List<XaCmsResource> secondResourceList=xaCmsResourceRepository.findResourceByParentIdAndStatus(xaCmsResource.getResourceId(), XaConstant.ResourcesStatus.status_normal);	//获取二级菜单
			for (XaCmsResource xaCmsResource2 : secondResourceList) {
				
				List<TreeNode> children3=new ArrayList<TreeNode>();		//action级资源
				List<XaCmsResource> thirdResourceList=xaCmsResourceRepository.findResourceByParentIdAndStatus(xaCmsResource2.getResourceId(), XaConstant.ResourcesStatus.status_normal);	//获取三级资源
				boolean secondNsSelected=true;
				for (XaCmsResource xaCmsResource3 : thirdResourceList) {
					//如果子节点有一个不包含，所父节点不能被选中。
					if(!myResourceIdList.contains(xaCmsResource3.getResourceId())){
						secondNsSelected=false;
					}
					
					NodeStatus thirdNs=new NodeStatus(true,false,false);
					if(myResourceIdList.contains(xaCmsResource3.getResourceId())) thirdNs.setSelected(true);
					String icon3="";
					if(XaConstant.ResourceShowType.page_level==xaCmsResource3.getShowType()){
						icon3=XaConstant.TreeNodeIcon.html_24;
					}else if(XaConstant.ResourceShowType.menu_level==xaCmsResource3.getShowType()){
						icon3=XaConstant.TreeNodeIcon.menu_24;
					}else if(XaConstant.ResourceShowType.button_level==xaCmsResource3.getShowType()){
						icon3=XaConstant.TreeNodeIcon.action_24;
					}
					TreeNode thirdTn=new TreeNode(xaCmsResource3.getResourceId()+"",xaCmsResource3.getResourceName(),thirdNs,icon3,null);
					children3.add(thirdTn);
				}
				if(!myResourceIdList.contains(xaCmsResource2.getResourceId())){
					firstNsSelected=false;
				}
				
				
				//构造二级
				NodeStatus secondNs=new NodeStatus(true,false,false);
				
				
				if(myResourceIdList.contains(xaCmsResource2.getResourceId()) && secondNsSelected) secondNs.setSelected(true);
				String icon2="";
				if(XaConstant.ResourceShowType.page_level==xaCmsResource2.getShowType()){
					icon2=XaConstant.TreeNodeIcon.html_24;
				}else if(XaConstant.ResourceShowType.menu_level==xaCmsResource2.getShowType()){
					icon2=XaConstant.TreeNodeIcon.menu_24;
				}else if(XaConstant.ResourceShowType.button_level==xaCmsResource2.getShowType()){
					icon2=XaConstant.TreeNodeIcon.action_24;
				}
				TreeNode secondTn=new TreeNode(xaCmsResource2.getResourceId()+"",xaCmsResource2.getResourceName(),secondNs,icon2,children3);
				children2.add(secondTn);
			}
			
			
			
			
			NodeStatus firstNs=new NodeStatus(true,false,false);
			if(myResourceIdList.contains(xaCmsResource.getResourceId()) && firstNsSelected ) firstNs.setSelected(true);
			
			String icon1="";
			if(XaConstant.ResourceShowType.page_level==xaCmsResource.getShowType()){
				icon1=XaConstant.TreeNodeIcon.html_24;
			}else if(XaConstant.ResourceShowType.menu_level==xaCmsResource.getShowType()){
				icon1=XaConstant.TreeNodeIcon.menu_24;
			}else if(XaConstant.ResourceShowType.button_level==xaCmsResource.getShowType()){
				icon1=XaConstant.TreeNodeIcon.action_24;
			}
			TreeNode firstTn=new TreeNode(xaCmsResource.getResourceId()+"", xaCmsResource.getResourceName(), firstNs, icon1, children2);
			children1.add(firstTn);
		}
		
		
		//资源树,根目录，默认不会选中
		NodeStatus rootStatus=new NodeStatus(true,false,false);
		TreeNode root=new TreeNode("0","资源列表",rootStatus,"",children1);
		return root;
	}

	public XaCmsRole findRoleById(long roleId) {
		return xaCmsRoleRepository.findOne(roleId);
	}

	public XaCmsRole getRoleByName(String roleName) {
		
		return xaCmsRoleRepository.findRoleByRoleName(roleName);
	}
	
	

}

