package com.xa3ti.webstart.base.security;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xa3ti.webstart.base.entity.XaCmsResource;
import com.xa3ti.webstart.base.repository.XaCmsResourceRepository;

 
@Service("XaResourceService")
@Transactional(readOnly = true)
public class XaResourceServiceImpl implements XaResourceService {

	@Autowired
	XaCmsResourceRepository xaCmsResourceRepository;

	
	public Map<String, String> findResourceAndRole() {
		Map<String, String> result = new HashMap<String, String>();
		Iterator<XaCmsResource> iterator = xaCmsResourceRepository.findAll()
				.iterator();
		while (iterator.hasNext()) {
			XaCmsResource resource = iterator.next();
			long resourceId = resource.getResourceId();
			List<String> roleNameList = xaCmsResourceRepository
					.findRoleNameByResourceId(resourceId);
			String roles = "";
			for (int i = 0; i < roleNameList.size(); i++) {
				String role = roleNameList.get(i);
				if ("ROLE_ALL".equals(role)) {
					roles = "permitAll";
					break;
				}
				if (i > 0) {
					roles = roles + " and hasRole('" + role + "')";
				} else {
					roles = roles + "hasRole('" + role + "')";
				}
			}
			result.put(resource.getResourceUrl(), roles);
		}
		return result;
	}

}
