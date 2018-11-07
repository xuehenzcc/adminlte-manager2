package com.zcc.entity;

import com.baomidou.mybatisplus.activerecord.Model;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;


/**
 * <p>
 * 角色表
 * </p>
 *
 * @author GaoJun.Zhou
 * @since 2017-03-06
 */
@TableName("roles")
public class Roles extends Model<Roles> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type=IdType.UUID)
	private String id;
    /**
     * 角色名称
     */
	private String role_name;
    /**
     * 角色描述
     */
	private String version;
    /**
     * 状态,1-启用,-1禁用
     */
	private Integer archived;
	private String guid;
    /**
     * 创建时间
     */
	private Date create_time;


	

	



	public String getId() {
		return id;
	}




	public void setId(String id) {
		this.id = id;
	}




	public String getRole_name() {
		return role_name;
	}




	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}




	public String getVersion() {
		return version;
	}




	public void setVersion(String version) {
		this.version = version;
	}




	public Integer getArchived() {
		return archived;
	}




	public void setArchived(Integer archived) {
		this.archived = archived;
	}




	public String getGuid() {
		return guid;
	}




	public void setGuid(String guid) {
		this.guid = guid;
	}




	public Date getCreate_time() {
		return create_time;
	}




	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}




	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
