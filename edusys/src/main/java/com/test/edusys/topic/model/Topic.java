package com.test.edusys.topic.model;

import org.nutz.dao.entity.annotation.*;

@Table("t_topic")
public class Topic {
	@Name
	private String id;
	@Column
	private String tname;
	@Column
	private String tbackinfo;
	@Column
	private String tdetailinfo;
	@Column
	private String tfunction;
	@Column
	private String stuloginname;
	@Column
	private String sturealname;
	@Column
	private String tealoginname;
	@Column
	private String tearealname;
	@Column
	private String code;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public String getTbackinfo() {
		return tbackinfo;
	}

	public void setTbackinfo(String tbackinfo) {
		this.tbackinfo = tbackinfo;
	}

	public String getTdetailinfo() {
		return tdetailinfo;
	}

	public void setTdetailinfo(String tdetailinfo) {
		this.tdetailinfo = tdetailinfo;
	}

	public String getTfunction() {
		return tfunction;
	}

	public void setTfunction(String tfunction) {
		this.tfunction = tfunction;
	}

	public String getStuloginname() {
		return stuloginname;
	}

	public void setStuloginname(String stuloginname) {
		this.stuloginname = stuloginname;
	}

	public String getSturealname() {
		return sturealname;
	}

	public void setSturealname(String sturealname) {
		this.sturealname = sturealname;
	}

	public String getTealoginname() {
		return tealoginname;
	}

	public void setTealoginname(String tealoginname) {
		this.tealoginname = tealoginname;
	}

	public String getTearealname() {
		return tearealname;
	}

	public void setTearealname(String tearealname) {
		this.tearealname = tearealname;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
