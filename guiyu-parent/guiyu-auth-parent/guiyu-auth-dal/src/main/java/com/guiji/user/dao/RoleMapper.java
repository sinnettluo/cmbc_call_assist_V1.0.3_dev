package com.guiji.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.guiji.user.dao.entity.Role;

@Mapper
public interface RoleMapper {
	
	public void insert(Role role);
	
	public void delete(String id);
	
	public void update(Role role);
	
	public Role getRoleId(String id);
	
	public List<Role> getRoles();
}
