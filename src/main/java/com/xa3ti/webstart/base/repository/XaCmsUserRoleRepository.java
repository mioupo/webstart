package com.xa3ti.webstart.base.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.xa3ti.webstart.base.entity.XaCmsUserRole;
 

/**
 * @Title: XaCmsUserRoleRepository.java
 * @Package com.xa3ti.shengmilu.base.repository
 * @Description: 用户角色关联dao
 * @author hchen
 * @date 2014年8月7日 上午9:52:08
 * @version V1.0
 */
public interface XaCmsUserRoleRepository extends
		PagingAndSortingRepository<XaCmsUserRole, Long>, JpaSpecificationExecutor<XaCmsUserRole> {

	/**
	 * @Title: findXacmsUserRoleByUserId
	 * @Description: 根据用户ID获取用户角色关系的集合
	 * @param userId
	 * @return    
	 */
	@Query("from XaCmsUserRole xcu where xcu.userId=?1")
	List<XaCmsUserRole> findXacmsUserRoleByUserId(long userId);
	
	
	
	/**
	 * @Title: findXacmsUserRoleByRoleId
	 * @Description: 根据角色ID获取用户用户角色关系的集合
	 * @param roleId
	 * @return    
	 */
	@Query("from XaCmsUserRole xcu where xcu.roleId=?1")
	List<XaCmsUserRole> findXacmsUserRoleByRoleId(long roleId);
}

