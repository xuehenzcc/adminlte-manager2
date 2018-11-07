package com.zcc.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zcc.entity.Urls;


/**
 *
 * SysRoleMenu 表数据库控制层接口
 *
 */
@Repository
public interface UrlsMapper extends BaseMapper<Urls> {

	List<Urls> selectUrls();
}