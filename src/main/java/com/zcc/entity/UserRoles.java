package com.zcc.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;


/**
 * <p>
 * 用户角色关联表
 * </p>
 *
 * @author GaoJun.Zhou
 * @since 2017-03-06
 */
@TableName("user_roles")
public class UserRoles extends Model<UserRoles> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type=IdType.UUID)
	private String id;
    /**
     * 用户主键
     */
	private String users_id;
    /**
     * 角色主键
     */
	private String roles_id;

	private Date create_time;

	public String getUsers_id() {
		return users_id;
	}

	public void setUsers_id(String users_id) {
		this.users_id = users_id;
	}

	public String getRoles_id() {
		return roles_id;
	}

	public void setRoles_id(String roles_id) {
		this.roles_id = roles_id;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	protected Serializable pkVal() {
		// TODO Auto-generated method stub
		return this.id;
	}

	
}
