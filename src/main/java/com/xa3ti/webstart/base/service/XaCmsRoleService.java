package com.xa3ti.webstart.base.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.xa3ti.webstart.base.entity.XaCmsResource;
import com.xa3ti.webstart.base.entity.XaCmsRole;
import com.xa3ti.webstart.base.vo.TreeNode;

 

/**
 * @Title: XaCmsRoleService.java
 * @Package com.xa3ti.shengmilu.business.service
 * @Description: 用户角色处理的service
 * @author hchen
 * @date 2014年8月1日 下午5:18:40
 * @version V1.0
 */
public interface XaCmsRoleService {

	/**
	 * 增加一条角色信息,增加时会同时增加角色对应资源的关系
	 * @Title: saveXaCmsRole
	 * @param xaCmsRole
	 * @return XaCmsRole
	 */
	XaCmsRole saveXaCmsRole(XaCmsRole xaCmsRole,String resourceIds);
	
	/**
	 * 批量删除角色信息，假删,删除时将角色假删除，同时，删除用户和角色之间的关系
	 * @Title: delXaCmsRole
	 * @param rids 要删除的角色ID，如：1,3,4,7 
	 */
	void delXaCmsRole(String rids);
	
	
	/**
	 * 修改角色信息
	 * @Title: updateXaCmsRole
	 * @param xaCmsRole
	 * @return XaCmsRole 修改后的角色信息
	 */
	XaCmsRole updateXaCmsRole(XaCmsRole xaCmsRole,String resourceIds);
	
	/**
	 * 根据条件分页查询角色信息
	 * @Title: findXaCmsRoleByConditon
	 * @param filterParams 查询条件
	 * @param pageable	分页条件
	 * @return Page<XaCmsRole>
	 */
	Page<XaCmsRole> findXaCmsRoleByConditon(Map<String, Object> filterParams,Pageable pageable);
	
	/**
	 * @Title: saveRoleResourceList
	 * @Description: 增加或修改角色对应的资源列表，操作时先删除原来的资源，再添加新的资源
	 * @param rid 角色ID
	 * @param resourceIds	资源ID数组
	 * @return 1表示修改或增加成功，其它数字表示失败。
	 */
	int saveRoleResourceList(long rid,Integer[] resourceIds);
	
	
	/**
	 * @Title: getMyResourceByRoldId
	 * @Description: 根据角色ID获取该角色对应的资源
	 * @param rid
	 * @return    
	 */
	List<XaCmsResource> getMyResourceByRoldId(long rid);
	
	
	/**
	 * 
	 * @Title: getResourceTreeNode
	 * @Description: 构造树型菜单
	 * @param roleId  roleId==0,表示是新增时的资源树(所有节点都没有被选中)；roleId!=0,表示修改时的资源树(某些节点会被选中)
	 * @return
	 */
	TreeNode  getResourceTreeNode(long roleId);
	
	
	/**
	 * @Title: findRoleById
	 * @Description: 根据角色ID查询一条角色信息
	 * @param roleId
	 * @return    
	 */
	XaCmsRole findRoleById(long roleId);
	
	
	/**
	 * @Title: getRoleByName
	 * @Description: 根据用户名查询用户信息。必须是有效用户
	 * @param roleName
	 * @return    
	 */
	XaCmsRole getRoleByName(String roleName);
}

