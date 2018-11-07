package com.zcc.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.zcc.entity.Urls;


/**
 *
 * SysRoleMenu 表数据服务层接口
 *
 */
public interface IUrlsService extends IService<Urls> {
	
	/**
	 * 查询Url
	 */
	List<Urls> selectUrls();

}