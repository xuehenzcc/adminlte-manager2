package com.zcc.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zcc.entity.SysLog;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
  * 日志表 Mapper 接口
 * </p>
 */
@Repository
public interface SysLogMapper extends BaseMapper<SysLog> {

}