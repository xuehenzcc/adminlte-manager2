package com.zcc.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zcc.entity.Roles;
import com.zcc.mapper.RolesMapper;
import com.zcc.service.IRolesService;


/**
 *
 * SysRole 表数据服务层接口实现类
 *
 */
@Service
public class RolesServiceImpl extends ServiceImpl<RolesMapper, Roles> implements IRolesService {


}