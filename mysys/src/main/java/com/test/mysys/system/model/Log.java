package com.test.mysys.system.model;

import java.sql.Timestamp;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("t_system_log")
public class Log implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6451042982273924036L;
	@Id
	private Integer id = null;
	@Column
	private String description = null; //
	// @Column
	// private String exceptionCode = null; //
	@Column
	private String type = null; //
	// @Column
	// private String exceptionDetail = null; //
	@Column
	private String method = null; // 方法
	// @Column
	// private String params = null; // 请求参数
	@Column
	private String createBy = null; // 请求人
	@Column
	private String createDate = null; //
	@Column
	private Timestamp cjsj = null;// 创建时间
	@Column
	private String requestIp = null; // 请求IP

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getRequestIp() {
		return requestIp;
	}

	public void setRequestIp(String requestIp) {
		this.requestIp = requestIp;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

}
