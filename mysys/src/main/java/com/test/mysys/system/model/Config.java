package com.test.mysys.system.model;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

import com.test.mysys.common.utils.reflection.ShowLog;
import com.test.mysys.common.utils.reflection.ShowLogTablename;

@ShowLogTablename("配置信息")
@Table("t_system_config") 
public class Config {
	
	@Id
	private Long id = null;
	
	@Comment("名称")
	@ShowLog
	@Column
	private String name = null;//名称
	
	@Comment("值")
	@ShowLog
	@Column
	private String val = null;// 值
	
	@Comment("说明")
	@ShowLog
	@Column
	private String comment = null;// 说明

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
	
	
	
	
}
