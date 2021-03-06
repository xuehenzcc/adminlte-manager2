package com.zcc.mapper;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zcc.entity.SysRoleMenu;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


/**
 *
 * SysRoleMenu 表数据库控制层接口
 *
 */
@Repository
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {

	/**
	 * 根据用户Id获取用户所在角色的权限
	 */
	public List<String> selectRoleMenuIdsByUserId(String uid);
	
}