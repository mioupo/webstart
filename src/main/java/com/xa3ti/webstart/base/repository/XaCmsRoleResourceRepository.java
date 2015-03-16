package com.xa3ti.webstart.base.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.xa3ti.webstart.base.entity.XaCmsRoleResource;



/**
 * @Title: XaCmsRoleResource.java
 * @Package com.xa3ti.shengmilu.base.repository
 * @Description: 角色和资源之间的关系处理接口
 * @author hchen
 * @date 2014年8月5日 下午2:04:00
 * @version V1.0
 */
public interface XaCmsRoleResourceRepository extends PagingAndSortingRepository<XaCmsRoleResource, Long>,JpaSpecificationExecutor<XaCmsRoleResource> {

	/**
	 * @Title: findRoleResourceByRoleId
	 * @Description: 根据角色ID获取RoleResource记录
	 * @param rid 角色ID
	 * @return    
	 */
	@Query("from XaCmsRoleResource xcr where xcr.roleId=?1")
	List<XaCmsRoleResource>  findRoleResourceByRoleId(long rid);
	
	/**
	 * @Title: findRoleResourceByResourceId
	 * @Description: 根据资源ID获取RoleResource记录
	 * @param rid 资源ID
	 * @return    
	 */
	@Query("from XaCmsRoleResource xcr where xcr.roleResourceId=?1")
	List<XaCmsRoleResource> findRoleResourceByResourceId(long rid);
}

