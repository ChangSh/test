package com.test.mysys.customer.model;

import org.nutz.dao.entity.annotation.*;


@Table("t_customer")
public class Customer {
	@Name
	private String id;
	@Column
	private String code;//客户编号
	@Column
	private String name;//客户名称
	@Column
	private String phone;//客户电话
	@Column
	private String registration_date;//注册时间
	@Column
	private String loginname;//密码
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getRegistration_date() {
		return registration_date;
	}
	public void setRegistration_date(String registration_date) {
		this.registration_date = registration_date;
	}
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	
	
}
