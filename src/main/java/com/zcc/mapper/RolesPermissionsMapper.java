package com.zcc.mapper;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zcc.entity.RolesPermissions;


/**
 *
 * SysRoleMenu 表数据库控制层接口
 *
 */
@Repository
public interface RolesPermissionsMapper extends BaseMapper<RolesPermissions> {

//	/**
//	 * 根据用户Id获取用户所在角色的权限
//	 */
//	public List<String> selectRoleMenuIdsByUserId(String uid);
	
}