package org.zy.gateway.admin.client.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "用户实体类", description = "这是用户的对象")
public class UserInfo implements Serializable {

	private static final long serialVersionUID = 7450631467493946075L;

	@ApiModelProperty(hidden = true)
	private long id;
	@ApiModelProperty(value = "用户名", example = "admin", required = true)
	private String username;
	@ApiModelProperty(value = "密码", example = "123456", required = false)
	private String password;
	@ApiModelProperty(value = "邮箱", example = "123456@qq.com")
	private String email;
	private String phone;
	private Long date;

	public long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	public Long getDate() {
		return date;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setDate(Long date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", phone=" + phone + ", date=" + date + "]";
	}

}