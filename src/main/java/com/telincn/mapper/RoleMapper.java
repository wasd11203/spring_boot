package com.telincn.mapper;

import java.util.List;

import com.telincn.entity.Role;

public interface RoleMapper {
	
	public List<Role> selectRoleByUser(int userId);
	
}
