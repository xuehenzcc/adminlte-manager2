package com.zcc.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;


/**
 * <p>
 * 角色菜单关联表
 * </p>
 *
 * @author GaoJun.Zhou
 * @since 2017-03-06
 */
@TableName("roles_permissions")
public class RolesPermissions extends Model<RolesPermissions> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type=IdType.UUID)
	private String id;
    /**
     * 角色主键
     */
	private String roles_id;
    /**
     * 菜单主键
     */
	private String permission;

	private Date create_time;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	

	public String getRoles_id() {
		return roles_id;
	}

	public void setRoles_id(String roles_id) {
		this.roles_id = roles_id;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
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
