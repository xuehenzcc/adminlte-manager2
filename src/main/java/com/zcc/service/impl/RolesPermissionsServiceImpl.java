package com.zcc.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zcc.entity.RolesPermissions;
import com.zcc.entity.SysRoleMenu;
import com.zcc.mapper.RolesPermissionsMapper;
import com.zcc.mapper.SysMenuMapper;
import com.zcc.mapper.SysRoleMenuMapper;
import com.zcc.service.IRolesPermissionsService;
import com.zcc.service.ISysRoleMenuService;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 *
 * SysRoleMenu 表数据服务层接口实现类
 *
 */
@Service
public class RolesPermissionsServiceImpl extends ServiceImpl<RolesPermissionsMapper, RolesPermissions> implements IRolesPermissionsService {
	
//	@Autowired private SysMenuMapper sysMenuMapper;
//	
	@Override
	public void addAuth(String roleId, String[] menuIds) {
		
		/**
		 * 删除原有权限
		 */
		this.delete(new EntityWrapper<RolesPermissions>().eq("roles_id",roleId));
		/**
		 * 重新授权
		 */
		if(ArrayUtils.isNotEmpty(menuIds)){
			for(String menuId : menuIds){
				RolesPermissions sysRoleMenu2 = new RolesPermissions();
				sysRoleMenu2.setRoles_id(roleId);
				sysRoleMenu2.setPermission(menuId);
				this.insert(sysRoleMenu2);
			}
		}
	}
//
//	@Override
//	public List<SysRoleMenu> selectByRole(String roleId) {
//		EntityWrapper<SysRoleMenu> ew = new EntityWrapper<SysRoleMenu>();
//		ew.addFilter("roleId = {0}", roleId);
//		return this.selectList(ew);
//		
//	}
//
//	@Override
//	public Set<String> findMenusByUid(String id) {
//		List<String> list =  sysMenuMapper.selectResourceByUid(id);
//		return new HashSet<>(list);
//	}


}