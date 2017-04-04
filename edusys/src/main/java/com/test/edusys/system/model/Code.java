package com.test.edusys.system.model;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

import com.test.edusys.common.utils.reflection.ShowLog;
import com.test.edusys.common.utils.reflection.ShowLogTablename;

@ShowLogTablename("代码")
@Table("t_system_codedic")
public class Code implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6451042982273924032L;
	@Id
	private Integer id = null;
	@Comment("组代码")
	@Column
	private String sectionname = null; // 全局类型名称
	@Comment("代码")
	@Column
	private String code = null;// 局部类型代码
	@ShowLog
	@Comment("代码名称")
	@Column
	private String codename = null;// 局部类型名称
	@Comment("说明")
	@Column
	private String memo = null;// 说明
	@Comment("顺序")
	@Column
	private String sortorder = null;// 说明

	public String getSortorder() {
		return sortorder;
	}

	public void setSortorder(String sortorder) {
		this.sortorder = sortorder;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSectionname() {
		return sectionname;
	}

	public void setSectionname(String sectionname) {
		this.sectionname = sectionname;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCodename() {
		return codename;
	}

	public void setCodename(String codename) {
		this.codename = codename;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}
