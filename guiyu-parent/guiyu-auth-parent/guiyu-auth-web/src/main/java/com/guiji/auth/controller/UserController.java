package com.guiji.auth.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guiji.auth.api.IAuth;
import com.guiji.auth.exception.CheckConditionException;
import com.guiji.auth.service.UserService;
import com.guiji.auth.util.AuthUtil;
import com.guiji.common.model.Page;
import com.guiji.component.result.Result;
import com.guiji.component.result.Result.ReturnData;
import com.guiji.user.dao.entity.SysUser;
import com.netflix.ribbon.proxy.annotation.Http.Header;

/**
 * Created by ty on 2018/10/22.
 */
@RestController
//@RequestMapping("user")
public class UserController implements IAuth{
	
	@Autowired
	private UserService service;
	
	@RequestMapping("/user/regist")
	public void insert(SysUser user,Long roleId) throws Exception{
		if(service.existUserName(user)){
			throw new CheckConditionException("00010005");
		}
		user.setPassword(AuthUtil.encrypt(user.getPassword()));
		service.insert(user,roleId);
	}
	
	@RequestMapping("/user/update")
	public void update(SysUser user,String[] roleId) throws CheckConditionException{
		if(service.existUserName(user)){
			throw new CheckConditionException("00010005");
		}
		user.setPassword(AuthUtil.encrypt(user.getPassword()));
		service.update(user,roleId);
	}
	
	@RequestMapping("/user/delete")
	public void delete(Long id){
		service.delete(id);
	}

	@RequestMapping("/user/getUserByPage")
	public Page<Map<String,String>> getUserByPage(Page<Map<String,String>> page){
		service.getUserByPage(page);
		return page;
	}
	
	@RequestMapping("/user/getUserById")
	public ReturnData<SysUser> getUserById(Long userId){
		SysUser sysUser=service.getUserById(userId);
		return Result.ok(sysUser);
	}
	
	@RequestMapping("/user/getUserByName")
	public List<Map<String,String>> getUserByName(String username){
		return service.getUserByName(username);
	}
}
