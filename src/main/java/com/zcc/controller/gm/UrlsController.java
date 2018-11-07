package com.zcc.controller.gm;

import java.util.Date;
import java.util.List;

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
import com.zcc.common.anno.Log;
import com.zcc.common.bean.Rest;
import com.zcc.common.controller.SuperController;
import com.zcc.entity.Urls;
import com.zcc.service.IUrlsService;
/**
 * 用户控制器
 * @author Gaojun.Zhou
 * @date 2016年12月13日 上午10:22:41
 */
@Controller
@RequestMapping("/gm/urls")
public class UrlsController extends SuperController{  

	@Autowired private IUrlsService sysUrlservice;
	
	/**
	 * 分页查询用户
	 */
	@RequiresPermissions("listUser")
    @RequestMapping("/list/{pageNumber}")  
    public  String list(@PathVariable Integer pageNumber,@RequestParam(defaultValue="15") Integer pageSize,String search,Model model){
		EntityWrapper<Urls> ew = new EntityWrapper<Urls>();
		if(StringUtils.isNotBlank(search)){
			ew.like("url",search);
			model.addAttribute("url",search);
		}
		
    	Page<Urls> page = getPage(pageNumber,pageSize);
    	page.setOrderByField("number");
		page.setAsc(true);
		
    	model.addAttribute("pageSize", pageSize);
    	Page<Urls> pageData = sysUrlservice.selectPage(page, ew);
    	model.addAttribute("pageData", pageData);
    	return "gm/urls/list";
    } 
    /**
     * 新增用户
     */
	@RequiresPermissions("addUser")
    @RequestMapping("/add")  
    public  String add(Model model){
		return "gm/urls/add";
    } 
    
    /**
     * 执行新增
     */
    @Log("创建用户")
    @RequiresPermissions("addUser")
    @RequestMapping("/doAdd")  
    @ResponseBody
    public  Rest doAdd(Urls user){
    	user.setCreate_time(new Date());
    	sysUrlservice.insert(user);
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
    	sysUrlservice.deleteById(id);
    	return Rest.ok();
    }  
    
	/**
	 * 编辑用户
	 */
    @RequestMapping("/edit/{id}")  
    @RequiresPermissions("editUser")
    public  String edit(@PathVariable String id,Model model){
    	Urls sysUser = sysUrlservice.selectById(id);
    	
//    	List<Roles> sysRoles = sysRoleService.selectList(null);
//    	EntityWrapper<UserRoles> ew = new EntityWrapper<UserRoles>();
//    	ew.eq("Urls_id ", id);
//    	List<UserRoles> mySysUserRoles = sysUserRoleService.selectList(ew);
////    	List<String> myRolds = Lists.transform(mySysUserRoles, input -> input.getRoleId());
//    	List<String> myRolds =new ArrayList<String>();
//    	for (int i = 0; i < mySysUserRoles.size(); i++) {
//    		String roleId=mySysUserRoles.get(i).getRoles_id();
//    		myRolds.add(roleId);
//		}
    	model.addAttribute("sysUser",sysUser);
//    	model.addAttribute("sysRoles",sysRoles);
//    	model.addAttribute("myRolds",myRolds);
//    	model.addAttribute("deptList", sysDeptService.selectList(null));
    	return "gm/urls/edit";
    } 
    /**
     * 执行编辑
     */
    @Log("编辑用户")
    @RequiresPermissions("editUser")
    @RequestMapping("/doEdit")  
    @ResponseBody
    public  Rest doEdit(Urls sysUser,Model model){
    	sysUrlservice.updateById(sysUser);
    	return Rest.ok();
    } 
    
    /**
     * 验证用户名是否已存在
     */
    @RequestMapping("/checkName")  
    @ResponseBody
    public Rest checkName(String userName){
    	List<Urls> list = sysUrlservice.selectList(new EntityWrapper<Urls>().eq("username", userName));
    	if(list.size() > 0){
    		return Rest.failure("用户名已存在");
    	}
    	return Rest.ok();
    }
    
}
