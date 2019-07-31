package com.test.edusys.college.model;

import org.nutz.dao.entity.annotation.*;

@Table("t_college")
public class College {
	@Name
	private String id;
	@Column
	private String college;

	private String major;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

}
