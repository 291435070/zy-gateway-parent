package org.zy.gateway.admin.client.model;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class Feedback implements Serializable {

	private static final long serialVersionUID = 4595459293699491670L;

	private long id;
	@Length(max = 50, message = "内容长度不能超过50")
	@NotBlank(message = "内容不能为空")
	private String content;
	@Email(message = "邮箱格式不正确")
	@NotBlank(message = "邮箱不能为空")
	private String email;
	private String phone;
	@Min(value = 0, message = "请选择正确的类型")
	@NotNull(message = "类型不能为空")
	private Integer type;
	private Long date;

	public long getId() {
		return id;
	}

	public String getContent() {
		return content;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	public Integer getType() {
		return type;
	}

	public Long getDate() {
		return date;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public void setDate(Long date) {
		this.date = date;
	}

}