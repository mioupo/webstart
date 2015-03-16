package com.xa3ti.webstart.base.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xa3ti.webstart.base.constant.XaConstant;
import com.xa3ti.webstart.base.entity.XaCmsRole;
import com.xa3ti.webstart.base.repository.XaCmsResourceRepository;
import com.xa3ti.webstart.base.repository.XaCmsRoleRepository;
import com.xa3ti.webstart.base.util.XaUtil;

 
@Service("XaSecurityMetadataSourceService")
@Transactional(readOnly = true)
public class XaSecurityMetadataSourceService implements
		FilterInvocationSecurityMetadataSource {

	@Autowired
	private XaCmsResourceRepository xaCmsResourceRepository;

	@Autowired
	private XaCmsRoleRepository xaCmsRoleRepository;

	private static Map<String, Collection<ConfigAttribute>> roleMap = null;

	public void loadResourceDefine() {

		if (roleMap == null) {
			roleMap = new LinkedHashMap<String, Collection<ConfigAttribute>>();
		}

//		Iterator<XaCmsRole> iterator = msCmsRoleRepository.findAll().iterator();
		Iterator<XaCmsRole> iterator = xaCmsRoleRepository.findAllXaCmsRole(XaConstant.RoleStatus.status_normal).iterator();
		while (iterator.hasNext()) {
			XaCmsRole mcr = iterator.next();
			List<String> urlList = xaCmsResourceRepository
					.findResourceByRoleId(mcr.getRoleId());
			for (String url : urlList) {
				if (XaUtil.isNotEmpty(url)) {
					if (XaUtil.isNotEmpty(mcr.getRoleName())) {
						ConfigAttribute configAttribute = new SecurityConfig(
								mcr.getRoleName());
						Collection<ConfigAttribute> configAttributes = null;
						if (roleMap.containsKey(url)) {
							configAttributes = roleMap.get(url);
						} else {
							configAttributes = new ArrayList<ConfigAttribute>();
						}
						configAttributes.add(configAttribute);
						roleMap.put(url, configAttributes);
					}
				}
			}

		}
	}

	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		String url = ((FilterInvocation) object).getRequestUrl();
		int firstQuestionMarkIndex = url.indexOf("?");
		if (firstQuestionMarkIndex != -1) {
			url = url.substring(0, firstQuestionMarkIndex);
		}
		if (roleMap == null) {
			loadResourceDefine();
		}
		return roleMap.get(url);
	}

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return true;
	}
	
	public static void reset(){
		roleMap = null;
	}

}
