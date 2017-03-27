package com.telincn.mapper;

import java.util.List;
import java.util.Map;

import com.telincn.entity.Role;

public interface RoleMapper {
	
	public List<Role> selectRoleByUser(int userId);
	
	/**
	 *  获得 资源url 与 角色的关系
	 * @return
	 */
	public List<Map<String , Object>> loadSourceWithRoleAssociation();
	
	public List<Role> selectRoles();
	
}
