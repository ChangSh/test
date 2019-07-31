package com.test.mysys.goods.model;

import org.nutz.dao.entity.annotation.*;

@View("v_goods_files")
public class GoodsDetailVo {
	@Name
	private String id;
	@Column
	private String gname;
	@Column
	private String gunitprice;
	@Column
	private String gbriefintro;
	@Column
	private String gdetailintro;// 详细信息
	@Column
	private String gsize;
	@Column
	private String gtype;// 分类
	@Column
	private Integer discount;// 折扣
	@Column
	private Integer gisfocus;// 折是否推荐
	@Column
	private Integer gclick;// 折是否推荐
	@Column
	private String filepath;
	@Column
	private String cartId;
	@Column
	private String gaddr;
	@Column
	private String status;
	@Column
	private String codename;
	@Column
	private String garound;
	@Column
	private String gtraffic;

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

	public String getGunitprice() {
		return gunitprice;
	}

	public void setGunitprice(String gunitprice) {
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

	public String getGtype() {
		return gtype;
	}

	public void setGtype(String gtype) {
		this.gtype = gtype;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	public Integer getGisfocus() {
		return gisfocus;
	}

	public void setGisfocus(Integer gisfocus) {
		this.gisfocus = gisfocus;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public Integer getGclick() {
		return gclick;
	}

	public void setGclick(Integer gclick) {
		this.gclick = gclick;
	}

	public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public String getGaddr() {
		return gaddr;
	}

	public void setGaddr(String gaddr) {
		this.gaddr = gaddr;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCodename() {
		return codename;
	}

	public void setCodename(String codename) {
		this.codename = codename;
	}

	public String getGaround() {
		return garound;
	}

	public void setGaround(String garound) {
		this.garound = garound;
	}

	public String getGtraffic() {
		return gtraffic;
	}

	public void setGtraffic(String gtraffic) {
		this.gtraffic = gtraffic;
	}

}
