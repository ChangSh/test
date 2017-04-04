package com.test.edusys.system.model;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

import com.test.edusys.common.utils.reflection.ShowLog;
import com.test.edusys.common.utils.reflection.ShowLogTablename;

@ShowLogTablename("菜单")
@Table("t_system_menu")
public class Menu {

	public String getImagesrc() {
		return imagesrc;
	}

	public void setImagesrc(String imagesrc) {
		this.imagesrc = imagesrc;
	}

	@Id
	private Long id = null;// id
	@ShowLog
	@Comment("菜单名称")
	@Column
	private String menuname = null;// 菜单名称
	@Comment("菜单url")
	@Column
	private String url = null;// 菜单url
	@Comment("父级菜单ID")
	@Column
	private Long pid = null;// 父级菜单ID
	@Comment("是否显示")
	@Column
	private Integer visible = null;// 是否显示
	@Comment("顺序")
	@Column
	private Integer sx = null;// 顺序
	@Comment("图标路径")
	@Column
	private String imagesrc = null;// 图标路径

	private boolean checked;// 是否选中
	private boolean open;// 是否打开

	// ------------------------------

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMenuname() {
		return menuname;
	}

	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public Integer getSx() {
		return sx;
	}

	public void setSx(Integer sx) {
		this.sx = sx;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public Integer getVisible() {
		return visible;
	}

	public void setVisible(Integer visible) {
		this.visible = visible;
	}

}