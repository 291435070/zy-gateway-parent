package org.zy.gateway.admin.client.model;

import java.util.Date;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class FileEntity {

	@Id
	private String id;// 类型为String，MongoDB自动分配id
	private String name;// 文件名
	private Binary content;// 文件内容
	private String type; // 文件类型
	private long size; // 文件大小
	private Date createTime;// 上传时间

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Binary getContent() {
		return content;
	}

	public String getType() {
		return type;
	}

	public long getSize() {
		return size;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setContent(Binary content) {
		this.content = content;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}