package com.test.edusys.goods.model;

import org.nutz.dao.entity.annotation.*;

@Table("t_msg")
public class Msg {
	@Name
	private String id;
	@Column
	private String msg;// 留言内容
	@Column
	private String loginname;// 登录名
	@Column
	private String msgTime;// 总价

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getMsgTime() {
		return msgTime;
	}

	public void setMsgTime(String msgTime) {
		this.msgTime = msgTime;
	}

}
