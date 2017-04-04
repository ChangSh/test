package com.test.edusys.college.model;

import org.nutz.dao.entity.annotation.*;

@Table("t_major")
public class Major {
	@Name
	private String id;
	@Column
	private String major;

	private String college;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

}
