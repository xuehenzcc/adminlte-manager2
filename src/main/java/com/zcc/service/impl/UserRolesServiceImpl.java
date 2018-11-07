package com.zcc.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zcc.entity.UserRoles;
import com.zcc.mapper.UserRolesMapper;
import com.zcc.service.IUserRolesService;



/**
 *
 * SysUserRole 表数据服务层接口实现类
 *
 */
@Service
public class UserRolesServiceImpl extends ServiceImpl<UserRolesMapper, UserRoles> implements IUserRolesService {

//	@Override
//	public Set<String> findRolesByUid(String uid) {
//		List<SysUserRole> list = this.selectList(new EntityWrapper<SysUserRole>().eq("userId", uid));
//
//		Set<String> set = new HashSet<String>();
//		for (SysUserRole ur : list) {
//			set.add(ur.getRoleId());
//		}
//		return set;
//	}
}