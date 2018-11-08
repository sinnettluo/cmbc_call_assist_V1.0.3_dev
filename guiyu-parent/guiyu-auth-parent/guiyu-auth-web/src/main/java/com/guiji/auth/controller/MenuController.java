package com.guiji.auth.controller;

import java.awt.Menu;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guiji.auth.service.MenuService;
import com.guiji.user.dao.entity.SysMenu;

@RestController
@RequestMapping("menu")
public class MenuController {

	@Autowired
	private MenuService service;

	@RequestMapping("insert")
	public void insert(SysMenu menu){
		service.insert(menu);
		
	}

	@RequestMapping("delete")
	public void delete(Long id){
		service.delete(id);
	}

	@RequestMapping("update")
	public void update(SysMenu menu){
		service.update(menu);
	}

	@RequestMapping("getMenuById")
	public SysMenu getMenuById(Long id){
		return service.getMenuById(id);
	}
	
	@RequestMapping("getMenus")
	public List<SysMenu> getMenus(Long userId){
		return service.getMenus(userId);
	}
	
	@RequestMapping("getAllMenus")
	public Map<String,Object> getAllMenus(Long roleId){
		return service.getAllMenus(roleId);
	} 
}
