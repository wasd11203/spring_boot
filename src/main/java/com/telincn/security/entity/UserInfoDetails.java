package com.telincn.security.entity;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import com.telincn.entity.User;

/**
 * extends org.springframework.security.core.userdetails.User:
 *  将User的实体的实现交由Spring Security 默认实现
 * extends SystemUser implements Userdetails:
 * 	可以实现自定义User实体 
 * 
 * @author 99
 *
 */
public class UserInfoDetails extends org.springframework.security.core.userdetails.User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 577083631841600639L;

	private User user;
	
	public UserInfoDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

}
