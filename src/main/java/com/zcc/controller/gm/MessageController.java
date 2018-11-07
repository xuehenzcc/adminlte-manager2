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
import com.zcc.entity.Message;
import com.zcc.service.IMemberService;
import com.zcc.service.IMessageService;
/**
 * 用户控制器
 * @author Gaojun.Zhou
 * @date 2016年12月13日 上午10:22:41
 */
@Controller
@RequestMapping("/gm/message")
public class MessageController extends SuperController{  

	@Autowired private IMessageService messageService;
	@Autowired private IMemberService memberService;
	
	/**
	 * 分页查询用户
	 */
	@RequiresPermissions("listMessage")
    @RequestMapping("/list/{pageNumber}")  
    public  String list(@PathVariable Integer pageNumber,@RequestParam(defaultValue="15") Integer pageSize,String search,Model model){
//		// 查询分页
		EntityWrapper<Message> ew = new EntityWrapper<Message>();
		if(StringUtils.isNotBlank(search)){
			ew.like("content",search);
			model.addAttribute("search",search);
		}
    	Page<Message> page = getPage(pageNumber,pageSize);
    	model.addAttribute("pageSize", pageSize);
    	page.setOrderByField("id");
    	page.setAsc(false);
    	Page<Message> pageData = messageService.selectPage(page, ew);
    	model.addAttribute("pageData", pageData);
    	return "gm/message/list";
    } 
    /**
     * 新增用户
     */
	@RequiresPermissions("addMessage")
    @RequestMapping("/add")  
    public  String add(Model model){
		return "gm/message/add";
    } 
    
    /**
     * 执行新增
     */
    @Log("创建用户")
    @RequiresPermissions("addMessage")
    @RequestMapping("/doAdd")  
    @ResponseBody
    public  Rest doAdd(Message message){
    	message.setCreatedate(new Date());
    	String account = message.getAccount();
    	
    	EntityWrapper<Member> ew = new EntityWrapper<Member>();
		if(StringUtils.isNotBlank(account)){
			ew.eq("account",account);
		}
    	List<Member> messages = memberService.selectList(ew);
    	if(messages.size()>0){
    		message.setUserid(messages.get(0).getId());
        	message.setTelephone(messages.get(0).getTelephone());
    	}
    	messageService.insert(message);
    	return Rest.ok();
    }  
    /**
     * 删除用户
     */
    @Log("删除用户")
    @RequiresPermissions("deleteMessage")
    @RequestMapping("/delete")  
    @ResponseBody
    public  Rest delete(String id){
    	messageService.deleteById(id);
    	return Rest.ok();
    }  
    
	/**
	 * 编辑用户
	 */
    @RequestMapping("/edit/{id}")  
    @RequiresPermissions("editMessage")
    public  String edit(@PathVariable String id,Model model){
    	Message sysUser = messageService.selectById(id);
    	
//    	List<SysRole> sysRoles = sysRoleService.selectList(null);
//    	EntityWrapper<SysUserRole> ew = new EntityWrapper<SysUserRole>();
//    	ew.eq("userId ", id);
//    	List<SysUserRole> mySysUserRoles = sysUserRoleService.selectList(ew);
//    	List<String> myRolds = Lists.transform(mySysUserRoles, input -> input.getRoleId());
    	
    	model.addAttribute("sysUser",sysUser);
//    	model.addAttribute("sysRoles",sysRoles);
//    	model.addAttribute("myRolds",myRolds);
//    	model.addAttribute("deptList", sysDeptService.selectList(null));
    	return "gm/message/edit";
    } 
    /**
     * 执行编辑
     */
    @Log("编辑用户")
    @RequiresPermissions("editMember")
    @RequestMapping("/doEdit")  
    @ResponseBody
    public  Rest doEdit(Message sysUser,Model model){
    	messageService.updateById(sysUser);
    	return Rest.ok();
    } 
    
    
}
