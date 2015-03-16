package com.xa3ti.webstart.base.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.xa3ti.webstart.base.entity.XaCmsRole;



public interface XaCmsRoleRepository extends
		PagingAndSortingRepository<XaCmsRole, Long>,
		JpaSpecificationExecutor<XaCmsRole> {

	/**
	 * @Title: findAllXaCmsRole
	 * @Description: 根据角色状态查询角色集合
	 * @param status 1表示正常，0表示删除
	 * @return    
	 */
	@Query("from XaCmsRole xcr where xcr.status=?1")
	List<XaCmsRole>  findAllXaCmsRole(int status);
	
	
	/**
	 * @Title: findRoleByRoleName
	 * @Description: 根据用户名查询有效状态的角色信息
	 * @param roleName
	 * @return    
	 */
	@Query("from XaCmsRole xcr where xcr.status=1 and xcr.roleName=?1")
	XaCmsRole findRoleByRoleName(String roleName);
}
