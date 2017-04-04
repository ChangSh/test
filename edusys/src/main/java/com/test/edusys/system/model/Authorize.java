package com.test.edusys.system.model;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

import com.test.edusys.common.utils.reflection.ShowLog;
import com.test.edusys.common.utils.reflection.ShowLogTablename;

@ShowLogTablename("权限")
@Table("t_system_authorize")
public class Authorize {

	@Id
	private Long id = null;//
	@ShowLog
	@Comment("模块")
	@Column // ("module")
	private String module = null;// 模块
	@Comment("描述")
	@Column // ("description")
	private String description = null;// 描述
	@Comment("顺序")
	@Column // ("sx")
	private String sx = null;// 顺序

	// ---------------------------------

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSx() {
		return sx;
	}

	public void setSx(String sx) {
		this.sx = sx;
	}

}