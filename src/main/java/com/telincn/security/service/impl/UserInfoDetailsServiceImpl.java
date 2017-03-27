package com.telincn.security.service.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.telincn.entity.Role;
import com.telincn.entity.User;
import com.telincn.mapper.RoleMapper;
import com.telincn.mapper.UserMapper;
import com.telincn.security.entity.UserInfoDetails;

@Service("userDetailsService")
public class UserInfoDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private RoleMapper roleMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user;
		List<Role> roleList ;
		Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		
		try {
			user = userMapper.selectUserByName(username);
		} catch (Exception e) {
			throw new RuntimeException("user select fail");
		}
		
		if(null == user){
			throw new RuntimeException("no user");
		}else{
			roleList = roleMapper.selectRoleByUser(user.getUserId());
		}
		
		user.setRoleList(roleList);
		//将权限设置到author中去，交给spring security 再遇到@PreAuthorize注解时用来表示当前用户所用有的权限
        for (Role role : roleList) {
        	authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        
		UserInfoDetails uid = new UserInfoDetails(user.getUsername(), user.getPassword(), authorities);
		uid.setUser(user);
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(uid, null, uid.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return uid;
	}

}
