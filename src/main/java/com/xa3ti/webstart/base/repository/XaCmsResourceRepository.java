package com.xa3ti.webstart.base.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.xa3ti.webstart.base.entity.XaCmsResource;


public interface XaCmsResourceRepository extends
		PagingAndSortingRepository<XaCmsResource, Long>,
		JpaSpecificationExecutor<XaCmsResource> {

	@Query("select ro.roleName from XaCmsRoleResource rr,XaCmsRole ro where rr.resourceId = ?1 and rr.roleId=ro.roleId")
	public List<String> findRoleNameByResourceId(long resourceId);

	@Query("select ro.roleName from XaCmsUser us,XaCmsUserRole ur,XaCmsRole ro where us.userName=?1 and us.userId = ur.userId and ro.roleId=ur.roleId")
	public List<String> findRoleNameByUserName(String userName);

	@Query("select rr.resourceId from XaCmsUserRole ur,XaCmsRoleResource rr"
			+ " where ur.userId = ?1 and ur.roleId=rr.roleId")
	public List<String> findResourceByUserId(Long userId);

	@Query("select mr.resourceUrl from XaCmsRoleResource rr, XaCmsResource mr"
			+ " where rr.roleId=?1 and rr.resourceId=mr.resourceId")
	public List<String> findResourceByRoleId(Long roleId);

	/**
	 * @Title: findParentResourceByStatus
	 * @Description: 根据状态查询一级资源,页面级资源和菜单级资源(parentId为空)
	 * @param status
	 *            1表示正常，0表示已删除或禁用
	 * @return
	 */
	@Query("from XaCmsResource xcr where xcr.parentId is null and xcr.status=?1 and xcr.showType=?2")
	public List<XaCmsResource> findParentResourceByStatus(int status,
			int showType);

	/**
	 * @Title: findResourceByShowTypeAndStatus
	 * @Description: 根据showType和status查询资源
	 * @param showType
	 *            资源类型
	 * @param status
	 *            资源状态
	 * @return
	 */
	@Query("from XaCmsResource xcr where xcr.showType=?1 and xcr.status=?2")
	public List<XaCmsResource> findResourceByShowTypeAndStatus(int showType,
			int status);

	/**
	 * @Title: findResourceByParentIdAndStatus
	 * @Description: 根据parentId和状态查询2级菜单资源资源
	 * @param parentId
	 * @param status
	 * @return
	 */
	@Query("from XaCmsResource xcr where xcr.parentId =?1 and xcr.status=?2")
	public List<XaCmsResource> findResourceByParentIdAndStatus(long parentId,
			int status);

	@Query(value = "select t from XaCmsResource t where t.parentId is null and t.status=?1", countQuery = "select count(distinct t ) from XaCmsResource t where t.parentId is null and t.status=?1")
	public Page<XaCmsResource> myFind(int status, Pageable p);


	/**
	 * @Title: findResourceByResourceName
	 * @Description: 查询资源数据
	 * @param name
	 * @param status
	 * @return    
	 */
	@Query(value = "select t.resource_id,t1.resource_name as parentResource,t.resource_name,t.resource_url,t.show_type "
			+ "from tb_cms_resource t "
			+ "left join tb_cms_resource t1 on t.parent_id=t1.resource_id "
			+ "where t.status=?2 and (?1 is null or t.resource_name like %?1%) limit ?3,?4 ", nativeQuery = true)
	List<Object[]> findResourceByResourceName(String name, int status,int fromIndex,int endIndex);
	
	
	@Query(value = "select count(t.resource_id) "
			+ "from tb_cms_resource t "
			+ "left join tb_cms_resource t1 on t.parent_id=t1.resource_id "
			+ "where t.status=?2 and (?1 is null or t.resource_name like %?1%) ", nativeQuery = true)
	Object findResourceByResourceNameCount(String name, int status);
}
