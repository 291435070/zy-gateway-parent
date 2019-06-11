package org.zy.gateway.admin.client.model;

import java.io.Serializable;

public class News implements Serializable {

	private static final long serialVersionUID = 55416350236195977L;

	private Long id;
	private String title;
	private String content;
	private String time;
	private String createDate;

	public News() {
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public String getTime() {
		return time;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

}
