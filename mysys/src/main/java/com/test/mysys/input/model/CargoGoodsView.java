package com.test.mysys.input.model;


import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.View;

@View("v_cargo_goods")
public class CargoGoodsView {
	@Name
	private String id;
	@Column
	private String gid;
	@Column
	private String pid;
	@Column
	private String gname;
	@Column
	private String gsize;
	@Column
	private int cargoamount;
	@Column
	private double cargounitprice;
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
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getGname() {
		return gname;
	}
	public void setGname(String gname) {
		this.gname = gname;
	}
	public String getGsize() {
		return gsize;
	}
	public void setGsize(String gsize) {
		this.gsize = gsize;
	}
	public int getCargoamount() {
		return cargoamount;
	}
	public void setCargoamount(int cargoamount) {
		this.cargoamount = cargoamount;
	}
	public double getCargounitprice() {
		return cargounitprice;
	}
	public void setCargounitprice(double cargounitprice) {
		this.cargounitprice = cargounitprice;
	}
	
	
	

}
