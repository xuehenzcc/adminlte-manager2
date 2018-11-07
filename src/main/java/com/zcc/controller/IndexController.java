package com.zcc.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * 首页控制器
* @ClassName: IndexController
* @author Gaojun.Zhou
* @date 2016年12月8日 下午8:42:40
*
 */
@Controller
@RequestMapping("/")
public class IndexController {  
	
    @RequestMapping({"","/","index"})  
    public  String index(Model model){
    	model.addAttribute("sm",1111.00);
    	model.addAttribute("ct","10");
    	model.addAttribute("totalMoney",10000.00);
    	model.addAttribute("totalOrderCount",100);
    	model.addAttribute("messageCount",100);
    	model.addAttribute("messageFirst","阿萨德发的发阿士大夫撒发生啥打法发顺丰 阿斯顿发的发送到发送到发送到发达大是大非");
		return "index";
    }  
    
    @RequestMapping("/getData")  
    @ResponseBody
    public Map<String, Object> getData(){
    	Map<String, Object> map = new HashMap<String, Object>();
    	Map<String, Object> map2 = new HashMap<String, Object>();
    	Map<String, Object> map3 = new HashMap<String, Object>();
    	String str[]={"一月", "二月", "三月", "四月", "五月", "六月", "七月","八月","九月","十月","十一月","十二月"};
    	Integer dataInt[]={50,35,36,12,50,81,72,56,78,80,82,83};
    	map2.put("success",true);
    	map3.put("label",str);
    	map3.put("list",dataInt);
    	map.put("meta",map2);
    	map.put("data",map3);
		return map;
    }  
    
}
