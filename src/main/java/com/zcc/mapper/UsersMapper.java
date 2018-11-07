package com.zcc.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zcc.entity.Users;


/**
 *
 * SysUser 表数据库控制层接口
 *
 */
@Repository
public interface UsersMapper extends BaseMapper<Users> {

//	List<Map<Object, Object>> selectUserList(Page<Map<Object, Object>> page, @Param("search") String search);
	
}