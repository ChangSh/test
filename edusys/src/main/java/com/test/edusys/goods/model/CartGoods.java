package com.test.edusys.goods.model;

import org.nutz.dao.entity.annotation.*;

@Table("t_cart_goods")
public class CartGoods {
	@Name
	private String id;
	@Column
	private String gid;// 数量
	@Column
	private String cid;// 总价

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

}
