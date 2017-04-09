package com.test.mysys.goods.model;

import org.nutz.dao.entity.annotation.*;


@Table("t_buycart")
public class BuyCart {
	@Name
	private String id;
	@Column
	private String gid; //商品id
	@Column
	private int issettle;//是否结算
	@Column
	private String loginname;//总价.
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
	public int getIssettle() {
		return issettle;
	}
	public void setIssettle(int issettle) {
		this.issettle = issettle;
	}
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	
	
}
