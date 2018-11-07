package com.zcc.service;

import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.zcc.entity.Users;
import com.zcc.entity.Users;


/**
 *
 * Users 表数据服务层接口
 *
 */
public interface IUsersService extends IService<Users> {
	
	/**
	 * 分页查询用户
	 */
//	Page<Map<Object, Object>> selectUserPage(Page<Map<Object, Object>> page, String search);
	
	/**
	 * 保存用户
	 */
	void insertUser(Users user, String[] roleId);
	/**
	 * 更新用户
	 */
	void updateUser(Users Users, String[] roleId);
	/**
	 * 删除用户
	 */
	void delete(String id);

}