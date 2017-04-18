package com.test.mysys.system.model;

import java.sql.Timestamp;
import java.util.List;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Many;
import org.nutz.dao.entity.annotation.Table;

import com.test.mysys.common.utils.reflection.ShowLog;
import com.test.mysys.common.utils.reflection.ShowLogTablename;

@ShowLogTablename("用户")
@Table("t_system_user")
public class User {

	@Id
	private Long id = null;// id

	@Comment("登录名")
	@ShowLog
	@Column
	private String loginname = null;// 登录名
	@Comment("姓名")
	@Column
	private String realname = null;// 姓名
	@Comment("密码")
	@Column
	private String password = null;// 密码
	@Column
	private Timestamp cjsj = null;// 创建时间
	@Column
	private Timestamp xgsj = null;// 修改时间
	@Column
	private String cjr = null;// 创建人
	@Column
	private String xgr = null;// 修改人
	@Column
	private String email = null;

	@Many(target = UserRole.class, field = "userid")
	private List<UserRole> userRoles;

	private String yzcode; // 验证码

	public String getYzcode() {
		return yzcode;
	}

	public void setYzcode(String yzcode) {
		this.yzcode = yzcode;
	}

	private int checked;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Timestamp getCjsj() {
		return cjsj;
	}

	public void setCjsj(Timestamp cjsj) {
		this.cjsj = cjsj;
	}

	public Timestamp getXgsj() {
		return xgsj;
	}

	public void setXgsj(Timestamp xgsj) {
		this.xgsj = xgsj;
	}

	public String getCjr() {
		return cjr;
	}

	public void setCjr(String cjr) {
		this.cjr = cjr;
	}

	public String getXgr() {
		return xgr;
	}

	public void setXgr(String xgr) {
		this.xgr = xgr;
	}

	public List<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public int getChecked() {
		return checked;
	}

	public void setChecked(int checked) {
		this.checked = checked;
	}

}