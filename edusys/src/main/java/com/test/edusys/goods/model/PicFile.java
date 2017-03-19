package com.test.edusys.goods.model;

import org.nutz.dao.entity.annotation.*;


@Table("t_file")
public class PicFile {
	@Name
	private String id;
	@Column
	private String fileid;
	@Column
	private String filepath;//单价
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFileid() {
		return fileid;
	}
	public void setFileid(String fileid) {
		this.fileid = fileid;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	
	
}
