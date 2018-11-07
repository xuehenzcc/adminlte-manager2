package com.zcc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zcc.entity.Urls;
import com.zcc.mapper.UrlsMapper;
import com.zcc.service.IUrlsService;



/**
 *
 * SysRoleMenu 表数据服务层接口实现类
 *
 */
@Service
public class UrlsServiceImpl extends ServiceImpl<UrlsMapper, Urls> implements IUrlsService {

	@Autowired private UrlsMapper urlsMapper;
	
	@Override
	public List<Urls> selectUrls() {
		// TODO Auto-generated method stub
		return urlsMapper.selectUrls();
	}
	


}