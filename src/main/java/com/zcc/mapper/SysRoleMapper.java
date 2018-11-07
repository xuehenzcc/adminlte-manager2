package com.zcc.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zcc.entity.SysRole;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 *
 * SysRole 表数据库控制层接口
 *
 */
@Repository
public interface SysRoleMapper extends BaseMapper<SysRole> {


}