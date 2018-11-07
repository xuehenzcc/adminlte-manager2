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
import com.zcc.entity.Member;
import com.zcc.service.IMemberService;
/**
 * 用户控制器
 * @author Gaojun.Zhou
 * @date 2016年12月13日 上午10:22:41
 */
@Controller
@RequestMapping("/gm/member")
public class MemberController extends SuperController{  

	@Autowired private IMemberService memberService;
//	@Autowired private ISysUserService sysUserService;
//	@Autowired private ISysRoleService sysRoleService;
//	@Autowired private ISysUserRoleService sysUserRoleService;
//	@Autowired private ISysDeptService sysDeptService;
	
	/**
	 * 分页查询用户
	 */
	@RequiresPermissions("listMember")
    @RequestMapping("/list/{pageNumber}")  
    public  String list(@PathVariable Integer pageNumber,@RequestParam(defaultValue="15") Integer pageSize,String search,Model model){
//		Page<SysRole> page = getPage(pageNumber,pageSize);
//		page.setOrderByField("createTime");
//		page.setAsc(false);
//		model.addAttribute("pageSize",pageSize);
//		// 查询分页
		EntityWrapper<Member> ew = new EntityWrapper<Member>();
		if(StringUtils.isNotBlank(search)){
			ew.like("account",search);
			model.addAttribute("search",search);
		}
//		Page<SysRole> pageData = sysRoleService.selectPage(page, ew);
//		model.addAttribute("pageData", pageData);
//		return "system/role/list";
		
//		if(StringUtils.isNotBlank(search)){
//			model.addAttribute("search", search);
//		}
    	Page<Member> page = getPage(pageNumber,pageSize);
    	model.addAttribute("pageSize", pageSize);
    	page.setOrderByField("account");
    	page.setAsc(true);
    	Page<Member> pageData = memberService.selectPage(page, ew);
    	model.addAttribute("pageData", pageData);
    	return "gm/member/list";
    } 
    /**
     * 新增用户
     */
	@RequiresPermissions("addMember")
    @RequestMapping("/add")  
    public  String add(Model model){
//    	model.addAttribute("roleList", sysRoleService.selectList(null));
//    	model.addAttribute("deptList", sysDeptService.selectList(null));
		return "gm/member/add";
    } 
    
    /**
     * 执行新增
     */
    @Log("创建用户")
    @RequiresPermissions("addMember")
    @RequestMapping("/doAdd")  
    @ResponseBody
    public  Rest doAdd(Member user){
    	user.setCreatedate(new Date());
    	user.setAge(10);
    	memberService.insert(user);
    	return Rest.ok();
    }  
    /**
     * 删除用户
     */
    @Log("删除用户")
    @RequiresPermissions("deleteMember")
    @RequestMapping("/delete")  
    @ResponseBody
    public  Rest delete(String id){
    	memberService.deleteById(id);
    	return Rest.ok();
    }  
    
	/**
	 * 编辑用户
	 */
    @RequestMapping("/edit/{id}")  
    @RequiresPermissions("editMember")
    public  String edit(@PathVariable String id,Model model){
    	Member sysUser = memberService.selectById(id);
    	
//    	List<SysRole> sysRoles = sysRoleService.selectList(null);
//    	EntityWrapper<SysUserRole> ew = new EntityWrapper<SysUserRole>();
//    	ew.eq("userId ", id);
//    	List<SysUserRole> mySysUserRoles = sysUserRoleService.selectList(ew);
//    	List<String> myRolds = Lists.transform(mySysUserRoles, input -> input.getRoleId());
    	
    	model.addAttribute("sysUser",sysUser);
//    	model.addAttribute("sysRoles",sysRoles);
//    	model.addAttribute("myRolds",myRolds);
//    	model.addAttribute("deptList", sysDeptService.selectList(null));
    	return "gm/member/edit";
    } 
    /**
     * 执行编辑
     */
    @Log("编辑用户")
    @RequiresPermissions("editMember")
    @RequestMapping("/doEdit")  
    @ResponseBody
    public  Rest doEdit(Member sysUser,Model model){
    	memberService.updateById(sysUser);
    	return Rest.ok();
    } 
    
    /**
     * 验证用户名是否已存在
     */
    @RequestMapping("/checkName")  
    @ResponseBody
    public Rest checkName(String account){
    	List<Member> list = memberService.selectList(new EntityWrapper<Member>().eq("account", account));
    	if(list.size() > 0){
    		return Rest.failure("用户名已存在");
    	}
    	return Rest.ok();
    }
    
}
