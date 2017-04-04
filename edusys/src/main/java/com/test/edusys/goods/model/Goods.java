package com.test.edusys.goods.model;

import org.nutz.dao.entity.annotation.*;

@Table("t_goods")
public class Goods {
	@Name
	private String id;
	@Column
	private String gname;// 商品名
	@Column
	private double gunitprice;// 单价
	@Column
	private String gbriefintro;// 简介
	@Column
	private String gdetailintro;// 详细信息
	@Column
	private String gsize;// 规格
	@Column
	private String gtype;// 分类
	@Column
	private Integer discount;// 折扣
	@Column
	private Integer gisfocus;// 焦点图显示
	@Column
	private Integer gclick;// 折扣

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGname() {
		return gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}

	public double getGunitprice() {
		return gunitprice;
	}

	public void setGunitprice(double gunitprice) {
		this.gunitprice = gunitprice;
	}

	public String getGbriefintro() {
		return gbriefintro;
	}

	public void setGbriefintro(String gbriefintro) {
		this.gbriefintro = gbriefintro;
	}

	public String getGdetailintro() {
		return gdetailintro;
	}

	public void setGdetailintro(String gdetailintro) {
		this.gdetailintro = gdetailintro;
	}

	public String getGsize() {
		return gsize;
	}

	public void setGsize(String gsize) {
		this.gsize = gsize;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	public String getGtype() {
		return gtype;
	}

	public void setGtype(String gtype) {
		this.gtype = gtype;
	}

	public Integer getGisfocus() {
		return gisfocus;
	}

	public void setGisfocus(Integer gisfocus) {
		this.gisfocus = gisfocus;
	}

	public Integer getGclick() {
		return gclick;
	}

	public void setGclick(Integer gclick) {
		this.gclick = gclick;
	}

}
