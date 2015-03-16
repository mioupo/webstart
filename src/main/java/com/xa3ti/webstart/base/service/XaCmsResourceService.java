package com.xa3ti.webstart.base.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.xa3ti.webstart.base.entity.XaCmsResource;
import com.xa3ti.webstart.base.util.XaResult;

/**
 * @Title: XaCmsResourceService.java
 * @Package com.xa3ti.shengmilu.business.service
 * @Description: 资源(菜单)操作的service
 * @author hchen
 * @date 2014年8月1日 下午5:56:23
 * @version V1.0
 */
public interface XaCmsResourceService {

	
	/**
	 * @Title: saveXaCmsResource
	 * @Description: 增加一条资源
	 * @param xaCmsResource
	 * @return    
	 */
	XaCmsResource saveXaCmsResource(XaCmsResource xaCmsResource);
	

	/**
	 * @Title: delXaCmsResource
	 * @Description: 删除一条资源信息，假删；删除时如果该资源被角色关联，直接删除关联关系
	 * @param rid
	 * @return    1表示删除成功；其它数字表示删除失败
	 */
	int delXaCmsResource(String rids);
	
	

	/**
	 * @Title: updateXaCmsResource
	 * @Description: 修改一条资源信息
	 * @param xaCmsResource
	 * @return  返回修改后的数据
	 */
	XaCmsResource updateXaCmsResource(XaCmsResource xaCmsResource);
	

	/**
	 * @Title: findXaCmsResourceByConditon
	 * @Description: 根据条件分页查询资源数据
	 * @param filterParams 查询条件
	 * @param pageable	分页条件
	 * @return  
	 */
	XaResult<Page<XaCmsResource>> findXaCmsResourceByConditon(String resourceName,Integer nextPage,Integer pageSize);
	
	/**
	 * @Title: getParentResouces
	 * @Description: 获取一级菜单的资源,parent为空的数据
	 * @return    
	 */
	List<XaCmsResource> getParentResouces(int status,int showType);
	
	
	/**
	 * @Title: getPageLevelAndMenuLevelResource
	 * @Description: 获取菜单级资源
	 * @return    
	 */
	List<XaCmsResource> getMenuLevelResource();
	
	
	/**
	 * @Title: findXaCmsResourceById
	 * @Description: 根据资源ID查询一条记录
	 * @param rid 资源ID
	 * @return    
	 */
	XaCmsResource  findXaCmsResourceById(long rid);
}

