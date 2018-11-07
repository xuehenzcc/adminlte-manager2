package com.zcc.service;

import com.baomidou.mybatisplus.service.IService;
import com.zcc.entity.UserRoles;


/**
 *
 * SysUserRole 表数据服务层接口
 *
 */
public interface IUserRolesService extends IService<UserRoles> {
	
	/**
	 * 获取用户的角色
	 * @param uid
	 * @return
	 */
//	Set<String> findRolesByUid(String uid);
}