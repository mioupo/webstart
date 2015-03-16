package com.xa3ti.webstart.base.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xa3ti.webstart.base.constant.XaConstant;
import com.xa3ti.webstart.base.entity.XaCmsUser;
import com.xa3ti.webstart.base.repository.XaCmsResourceRepository;
import com.xa3ti.webstart.base.repository.XaCmsUserRepository;

@Service("XaUserDetailsService")
@Transactional(readOnly = true)
public class XaUserDetailsService implements UserDetailsService {
	protected static final String ROLE_PREFIX = "ROLE_";
	protected static final GrantedAuthority DEFAULT_USER_ROLE = new SimpleGrantedAuthority(
			ROLE_PREFIX + "USER");

	@Autowired
	private XaCmsUserRepository xaCmsUserRepository;

	@Autowired
	private XaCmsResourceRepository xaCmsResourceRepository;

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		XaUserDetails xaUserDetails = new XaUserDetails();

		try {
			XaCmsUser user = xaCmsUserRepository.findByUserName(username,
					XaConstant.UserStatus.status_normal);
			List<String> rList = xaCmsResourceRepository
					.findRoleNameByUserName(username);
			xaUserDetails.setUsername(user.getUserName());
			xaUserDetails.setPassword(user.getPassword());
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			for (String roleName : rList) {
				GrantedAuthority authority = new SimpleGrantedAuthority(
						roleName);
				authorities.add(authority);
			}
			xaUserDetails.setAuthorities(authorities);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return xaUserDetails;
	}
}
