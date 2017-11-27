package com.uk.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uk.dao.UserDao;
import com.uk.model.Users;
import com.uk.model.Role;

@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserDao userDao;

	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Users user = userDao.findByName(username);
		List<GrantedAuthority> authorities = buildUserAuthority(user.getRoles());

		return buildUserForAuthentication(user, authorities);

	}

	private List<GrantedAuthority> buildUserAuthority(Set<Role> Roles) {

		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		// Build user's authorities
		for (Role userRole : Roles) {
			setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
		}

		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

		return Result;
	}

	private User buildUserForAuthentication(Users user, List<GrantedAuthority> authorities) {
		return new User(user.getName(), user.getPassword(), true, true, true, true, authorities);
	}
}