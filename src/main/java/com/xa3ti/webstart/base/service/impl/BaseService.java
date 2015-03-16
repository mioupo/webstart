package com.xa3ti.webstart.base.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Title: BaseService.java
 * @Package com.xa3ti.xxx.business.service.impl
 * @Description: TODO
 * @author hchen
 * @date 2014年8月20日 下午6:55:24
 * @version V1.0
 */
public abstract class BaseService {

	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
	@SuppressWarnings("unchecked")
	public List<T> query(String sql,String resultSetMapping){
		EntityManager em = entityManagerFactory.createEntityManager();
		Query query= em.createNativeQuery(sql,resultSetMapping);
		List<T> list = query.getResultList();
		em.close();
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> query(String sql){
		EntityManager em = entityManagerFactory.createEntityManager();
		Query query= em.createNativeQuery(sql);
		List<Object[]> list = query.getResultList();
		em.close();
		return list;
	}
}

