package com.zcc.service;


import com.baomidou.mybatisplus.service.IService;
import com.zcc.entity.SysSetting;

import java.util.List;

/**
 *
 * SysSetting 表数据服务层接口
 *
 */
public interface ISysSettingService extends IService<SysSetting> {

	List<SysSetting> findAll();


}