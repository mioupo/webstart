package com.xa3ti.webstart.business.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.xa3ti.webstart.business.entity.Property;

/**
 * @Title: PropertyRepository.java
 * @Package com.xa3ti.webstart.business.repository
 * @Description: 属性接口
 * @author hchen
 * @date 2014年10月15日 上午11:51:31
 * @version V1.0
 */
public interface PropertyRepository extends JpaSpecificationExecutor<Property>,
		PagingAndSortingRepository<Property, Long> {

}

