package com.zcc.controller.gm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.collect.Lists;
import com.zcc.common.anno.Log;
import com.zcc.common.bean.Rest;
import com.zcc.common.controller.SuperController;
import com.zcc.entity.Member;
import com.zcc.entity.Roles;
import com.zcc.entity.SysRole;
import com.zcc.entity.SysUser;
import com.zcc.entity.SysUserRole;
import com.zcc.entity.UserRoles;
import com.zcc.entity.Users;
import com.zcc.service.IRolesService;
import com.zcc.service.ISysDeptService;
import com.zcc.service.ISysRoleService;
import com.zcc.service.ISysUserRoleService;
import com.zcc.service.ISysUserService;
import com.zcc.service.IUserRolesService;
import com.zcc.service.IUsersService;
/**
 * 用户控制器
 * @author Gaojun.Zhou
 * @date 2016年12月13日 上午10:22:41
 */
@Controller
@RequestMapping("/gm/users")
public class UsersController extends SuperController{  

	@Autowired private IUsersService sysUserService;
	@Autowired private IRolesService sysRoleService;
	@Autowired private IUserRolesService sysUserRoleService;
//	@Autowired private ISysDeptService sysDeptService;
	
	/**
	 * 分页查询用户
	 */
	@RequiresPermissions("listUser")
    @RequestMapping("/list/{pageNumber}")  
    public  String list(@PathVariable Integer pageNumber,@RequestParam(defaultValue="15") Integer pageSize,String search,Model model){
//		if(StringUtils.isNotBlank(search)){
//			model.addAttribute("search", search);
//		}
		EntityWrapper<Users> ew = new EntityWrapper<Users>();
		if(StringUtils.isNotBlank(search)){
			ew.like("username",search);
			model.addAttribute("username",search);
		}
		
    	Page<Users> page = getPage(pageNumber,pageSize);
    	model.addAttribute("pageSize", pageSize);
    	Page<Users> pageData = sysUserService.selectPage(page, ew);
    	model.addAttribute("pageData", pageData);
    	return "gm/users/list";
    } 
    /**
     * 新增用户
     */
	@RequiresPermissions("addUser")
    @RequestMapping("/add")  
    public  String add(Model model){
    	model.addAttribute("roleList", sysRoleService.selectList(null));
//    	model.addAttribute("deptList", sysDeptService.selectList(null));
		return "gm/users/add";
    } 
    
    /**
     * 执行新增
     */
    @Log("创建用户")
    @RequiresPermissions("addUser")
    @RequestMapping("/doAdd")  
    @ResponseBody
    public  Rest doAdd(Users user,@RequestParam(value="roleId[]",required=false) String[] roleId){
    	
    	sysUserService.insertUser(user,roleId);
    	return Rest.ok();
    }  
    /**
     * 删除用户
     */
    @Log("删除用户")
    @RequiresPermissions("deleteUser")
    @RequestMapping("/delete")  
    @ResponseBody
    public  Rest delete(String id){
    	sysUserService.delete(id);
    	return Rest.ok();
    }  
    
	/**
	 * 编辑用户
	 */
    @RequestMapping("/edit/{id}")  
    @RequiresPermissions("editUser")
    public  String edit(@PathVariable String id,Model model){
    	Users sysUser = sysUserService.selectById(id);
    	
    	List<Roles> sysRoles = sysRoleService.selectList(null);
    	EntityWrapper<UserRoles> ew = new EntityWrapper<UserRoles>();
    	ew.eq("users_id ", id);
    	List<UserRoles> mySysUserRoles = sysUserRoleService.selectList(ew);
//    	List<String> myRolds = Lists.transform(mySysUserRoles, input -> input.getRoleId());
    	List<String> myRolds =new ArrayList<String>();
    	for (int i = 0; i < mySysUserRoles.size(); i++) {
    		String roleId=mySysUserRoles.get(i).getRoles_id();
    		myRolds.add(roleId);
		}
    	model.addAttribute("sysUser",sysUser);
    	model.addAttribute("sysRoles",sysRoles);
    	model.addAttribute("myRolds",myRolds);
//    	model.addAttribute("deptList", sysDeptService.selectList(null));
    	return "gm/users/edit";
    } 
    /**
     * 执行编辑
     */
    @Log("编辑用户")
    @RequiresPermissions("editUser")
    @RequestMapping("/doEdit")  
    @ResponseBody
    public  Rest doEdit(Users sysUser,@RequestParam(value="roleId[]",required=false) String[] roleId,Model model){
    	sysUserService.updateUser(sysUser,roleId);
    	return Rest.ok();
    } 
    
    /**
     * 验证用户名是否已存在
     */
    @RequestMapping("/checkName")  
    @ResponseBody
    public Rest checkName(String userName){
    	List<Users> list = sysUserService.selectList(new EntityWrapper<Users>().eq("username", userName));
    	if(list.size() > 0){
    		return Rest.failure("用户名已存在");
    	}
    	return Rest.ok();
    }
    
}
