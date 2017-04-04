package com.test.edusys.system.model;

import java.util.ArrayList;
import java.util.List;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("t_system_area")
public class Area {

	@Id
	private Long id = null;// id
	@Column
	private String name = null;// name
	@Column
	private Long fid = null;// 父级部门ID
	@Column
	private Long ordercol = null;// 排序

	private boolean isParent;// tree 用来显示
	private boolean isUser; // tree里是否显示的是用户
	private List<Area> children = new ArrayList<Area>();
	private String iconSkin;// 树图标
	private String chkStyle;

	// ---------------------------------

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

	public Long getFid() {
		return fid;
	}

	public void setFid(Long fid) {
		this.fid = fid;
	}

	public boolean getIsParent() {
		return isParent;
	}

	public void setIsParent(boolean isParent) {
		this.isParent = isParent;
	}

	public boolean getIsUser() {
		return isUser;
	}

	public void setIsUser(boolean isUser) {
		this.isUser = isUser;
	}

	public List<Area> getChildren() {
		return children;
	}

	public void setChildren(List<Area> children) {
		this.children = children;
	}

	public String getIconSkin() {
		return iconSkin;
	}

	public void setIconSkin(String iconSkin) {
		this.iconSkin = iconSkin;
	}

	public String getChkStyle() {
		return chkStyle;
	}

	public void setChkStyle(String chkStyle) {
		this.chkStyle = chkStyle;
	}

	public Long getOrdercol() {
		return ordercol;
	}

	public void setOrdercol(Long ordercol) {
		this.ordercol = ordercol;
	}

}