package com.zcc.entity.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.zcc.entity.SysMenu;


/**
 * 菜单树+是否有权限表示
 * @author Gaojun.Zhou
 * @date 2016年12月26日 上午10:34:02
 */
public class TreeUrlAllowAccess implements Serializable{

	/**
	* @Fields serialVersionUID : TODO()
	*/
	
	private static final long serialVersionUID = 1L;
	private String id;
	/**
	 * 菜单
	 */
//	private SysMenu sysMenu;
	private String url;
	private String number;
	/**
	 * 是否允许访问
	 */
	private boolean allowAccess = false;
	/**
	 * 子菜单
	 */
//	private List<TreeUrlAllowAccess> children = new ArrayList<TreeUrlAllowAccess>();
	
	
	public String getUrl() {
		return url;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public boolean isAllowAccess() {
		return allowAccess;
	}
	public void setAllowAccess(boolean allowAccess) {
		this.allowAccess = allowAccess;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
	
}
