package com.zcc.service.impl;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zcc.common.util.ShiroUtil;
import com.zcc.entity.UserRoles;
import com.zcc.entity.Users;
import com.zcc.entity.UserRoles;
import com.zcc.entity.Users;
import com.zcc.mapper.UserRolesMapper;
import com.zcc.mapper.UsersMapper;
import com.zcc.mapper.UserRolesMapper;
import com.zcc.mapper.UsersMapper;
import com.zcc.service.IUsersService;
import com.zcc.service.IUsersService;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *
 * Users 表数据服务层接口实现类
 *
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {

	@Autowired private UsersMapper userMapper;
	
	@Autowired private UserRolesMapper userRoleMapper;
	
	@Override
	public void insertUser(Users user, String[] roleIds) {
		// TODO Auto-generated method stub
		user.setCreate_time(new Date());
    	user.setPassword(ShiroUtil.md51024Pwd(user.getPassword(), user.getUsername()));
    	user.setGuid(UUID.randomUUID().toString());
		//保存用户
    	userMapper.insert(user);
		//绑定角色
		if(ArrayUtils.isNotEmpty(roleIds)){
			for(String rid : roleIds){
				UserRoles UserRoles = new UserRoles();
				UserRoles.setUsers_id(user.getId()+"");
				UserRoles.setRoles_id(rid);
				userRoleMapper.insert(UserRoles);
			}
		}
		
	}

	@Override
	public void updateUser(Users Users, String[] roleIds) {
		// TODO Auto-generated method stub
		Users.setPassword(null);
		//更新用户
		userMapper.updateById(Users);
		//删除已有权限
		userRoleMapper.delete(new EntityWrapper<UserRoles>().eq("user_id",Users.getId()));
		//重新绑定角色
		if(ArrayUtils.isNotEmpty(roleIds)){
			for(String rid : roleIds){
				UserRoles UserRoles = new UserRoles();
				UserRoles.setUsers_id(Users.getId()+"");
				UserRoles.setRoles_id(rid);
				userRoleMapper.insert(UserRoles);
			}
		}
	}

//	@Override
//	public Page<Map<Object, Object>> selectUserPage(Page<Map<Object, Object>> page, String search) {
//		// TODO Auto-generated method stub
//		page.setRecords(baseMapper.selectUserList(page, search));
//		return page;
//	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		this.deleteById(id);
		userRoleMapper.delete(new EntityWrapper<UserRoles>().addFilter("users_id = {0}", id));
	}


}