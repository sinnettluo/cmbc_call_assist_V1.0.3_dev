package com.guiji.auth.api;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.guiji.component.result.Result.ReturnData;
import com.guiji.user.dao.entity.SysUser;

@FeignClient("guiyu-auth-web")
public interface IAuth {
	
	@RequestMapping(value = "/user/getUserById")
	public ReturnData<SysUser> getUserById(@RequestParam("userId") Long userId);
	
}