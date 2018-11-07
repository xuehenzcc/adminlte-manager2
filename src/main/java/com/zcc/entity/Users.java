package com.zcc.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;


/**
 * <p>
 * 用户表
 * </p>
 *
 * @author GaoJun.Zhou
 * @since 2017-03-06
 */
@TableName("users")
public class Users extends Model<Users> {

    private static final long serialVersionUID = 1L;
    
    public static final int _0 = 0;
	public static final int _1 = 1;
    
    /**
     * 主键
     */
    @TableId(type=IdType.UUID)
	private String id;
    /**
     * 用户名
     */
	private String username;
    /**
     * 密码
     */
	private String password;
    /**
     * 用户状态,1-启用,-1禁用
     */
	private Integer archived;
    /**
     * 创建时间
     */
	private Date create_time;
    /**
     * 描述
     */
	private String guid;
    /**
     * 头像
     */
	private String version;
    /**
     * 部门主键
     */
	private String default_user;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getArchived() {
		return archived;
	}
	public void setArchived(Integer archived) {
		this.archived = archived;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getDefault_user() {
		return default_user;
	}
	public void setDefault_user(String default_user) {
		this.default_user = default_user;
	}
	@Override
	protected Serializable pkVal() {
		return this.id;
	}


	

}
