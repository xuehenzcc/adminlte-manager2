package com.zcc.controller.gm;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
import com.zcc.entity.Roles;
import com.zcc.entity.RolesPermissions;
import com.zcc.entity.SysUser;
import com.zcc.entity.SysUserRole;
import com.zcc.entity.Urls;
import com.zcc.entity.UserRoles;
import com.zcc.entity.Users;
import com.zcc.entity.vo.TreeMenuAllowAccess;
import com.zcc.entity.vo.TreeUrlAllowAccess;
import com.zcc.service.IRolesPermissionsService;
import com.zcc.service.IRolesService;
import com.zcc.service.ISysMenuService;
import com.zcc.service.IUrlsService;
import com.zcc.service.IUserRolesService;
import com.zcc.service.IUsersService;
/**
 * 角色控制器
 * @author Gaojun.Zhou
 * @date 2016年12月13日 上午10:23:41
 */
@Controller
@RequestMapping("/gm/roles")
public class RolesController extends SuperController{  

	/**
	 * 角色服务
	 */
	@Autowired private IRolesService RolesService;
	/**
	 * 角色用户服务
	 */
	@Autowired private IUserRolesService sysUserRoleService;
	/**
	 * 用户服务
	 */
	@Autowired private IUsersService sysUserService;
	/**
	 * 菜单服务
	 */
	@Autowired private IUrlsService sysMenuService;
	/**
	 * 角色权限服务
	 */
	@Autowired private IRolesPermissionsService RolesMenuService;
	
	/**
	 * 分页查询角色
	 */
	@RequiresPermissions("listRoles")
    @RequestMapping("/list/{pageNumber}")  
    public  String list(@PathVariable Integer pageNumber,@RequestParam(defaultValue="15") Integer pageSize, String search,Model model){
    	
		Page<Roles> page = getPage(pageNumber,pageSize);
		page.setOrderByField("create_time");
		page.setAsc(false);
		model.addAttribute("pageSize",pageSize);
		// 查询分页
		EntityWrapper<Roles> ew = new EntityWrapper<Roles>();
		if(StringUtils.isNotBlank(search)){
			ew.like("role_name",search);
			model.addAttribute("search",search);
		}
		Page<Roles> pageData = RolesService.selectPage(page, ew);
		model.addAttribute("pageData", pageData);
		return "gm/roles/list";
    } 
    
    /**
     * 新增角色
     */
	@RequiresPermissions("addRole")
    @RequestMapping("/add")  
    public  String add(Model model){
		return "gm/roles/add";
    } 
    
    /**
     * 执行新增角色
     */
	@RequiresPermissions("addRole")
    @Log("创建角色")
    @RequestMapping("/doAdd")  
	@ResponseBody
    public  Rest doAdd(Roles role){
    	role.setCreate_time(new Date());
    	role.setGuid(UUID.randomUUID().toString());
    	RolesService.insert(role);
		return Rest.ok();

    }  
    
    /**
     * 删除角色
     */
	@RequiresPermissions("deleteRole")
    @Log("删除角色")
    @RequestMapping("/delete")  
    @ResponseBody
    public  Rest delete(String id){
    	RolesService.deleteById(id);
    	return Rest.ok();
    }  

    /**
     * 批量删除角色
     */
	@RequiresPermissions("deleteBatchRole")
    @Log("批量删除角色")
    @RequestMapping("/deleteBatch")  
    @ResponseBody
    public Rest deleteBatch(@RequestParam("id[]") List<String> ids){
    	RolesService.deleteBatchIds(ids);
    	return Rest.ok();
    }  
    
    /**
     * 编辑角色
     */
	@RequiresPermissions("editRole")
    @RequestMapping("/edit/{id}")  
    public  String edit(@PathVariable String id,Model model){
    	Roles Roles = RolesService.selectById(id);
    	model.addAttribute("sysRole",Roles);
//    	model.addAttribute("sysRoles",sysRoles);
    	return "gm/roles/edit";
    } 
    
    /**
     * 执行编辑角色
     */
	@RequiresPermissions("editRole")
    @Log("编辑角色")
    @RequestMapping("/doEdit")  
	@ResponseBody
    public  Rest doEdit(Roles Roles,Model model){
    	RolesService.updateById(Roles);
    	return Rest.ok();
    } 
    
    /**
     * 权限
     */
	@RequiresPermissions("authRole")
    @RequestMapping("/auth/{id}")  
    public  String auth(@PathVariable String id,Model model){
    	
    	Roles Roles = RolesService.selectById(id);
    	
    	if(Roles == null){
    		throw new RuntimeException("该角色不存在");
    	}
    	
    	List<RolesPermissions> RolesMenus = RolesMenuService.selectList(new EntityWrapper<RolesPermissions>().eq("roles_id", id));
    	List<String> menuIds = Lists.transform(RolesMenus,input -> input.getPermission());
//    	EntityWrapper<Urls> ew = new EntityWrapper<Urls>();
//    	List<Urls> trees = sysMenuService.selectList(ew);
    	List<Urls> trees = sysMenuService.selectUrls();
    	List<TreeUrlAllowAccess> treeUrls=new ArrayList<TreeUrlAllowAccess>();
    	for (int i = 0; i < trees.size(); i++) {
    		TreeUrlAllowAccess tree=new TreeUrlAllowAccess();
			if(menuIds.size()>0){
	    		for(int j = 0; j < menuIds.size(); j++) {
	    			if(menuIds.get(j).equals(trees.get(i).getId())){
	    				tree.setAllowAccess(true);
	    				break;
	    			}
	    		}
	    	}
			tree.setUrl(trees.get(i).getUrl());
			tree.setId(trees.get(i).getId());
			tree.setNumber(trees.get(i).getNumber());
			treeUrls.add(tree);
		}
    	model.addAttribute("sysRole", Roles);
    	model.addAttribute("treeMenuAllowAccesses", treeUrls);
    	
    	return "gm/roles/auth";
    } 
    
    /**
     * 权限
     */
	@RequiresPermissions("authRole")
    @Log("角色分配权限")
    @RequestMapping("/doAuth")  
	@ResponseBody
    public  Rest doAuth(String roleId,@RequestParam(value="mid[]",required=false) String[] mid){
    	RolesMenuService.addAuth(roleId,mid);
    	return Rest.ok("OK,授权成功,1分钟后生效  ~");
    } 
	
	/**
	 * 获取角色下的所有用户
	 */
	@RequestMapping("/getUsers")  
	public String getUsers(String roleId,Model model){
		
		List<UserRoles> sysUserRoles = sysUserRoleService.selectList(new EntityWrapper<UserRoles>().eq("roles_id", roleId));
		
		List<String> userIds = Lists.transform(sysUserRoles,input -> input.getUsers_id());
		
		List<Users> users  = new ArrayList<Users>();
		
		if(userIds.size() > 0){
			EntityWrapper<Users> ew = new EntityWrapper<Users>();
			ew.in("id", userIds);
			users= sysUserService.selectList(ew);
		}
		
		model.addAttribute("users",users);
		return "gm/roles/users";
	}
	
	/**
	 * 获取指定角色的用户数量
	 */
	@RequestMapping("/getCount")  
	@ResponseBody
	public String getCount(String roleId){
		
		int count =  sysUserRoleService.selectCount(new EntityWrapper<UserRoles>().addFilter("roles_id = {0}", roleId));
		return String.valueOf(count);
	}
	
}
