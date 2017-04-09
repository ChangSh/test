package com.test.mysys.input.model;

import org.nutz.dao.entity.annotation.*;


@Table("t_cargo_c")
public class CargoChild {
	@Name
	private String id;
	@Column
	private String pid;//主表ID
	
	@Column
	private String gid;//进货总额
	
	@Column
	private double cargounitprice;//进货单价
	
	@Column
	private int cargoamount; //进货数量

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	public double getCargounitprice() {
		return cargounitprice;
	}

	public void setCargounitprice(double cargounitprice) {
		this.cargounitprice = cargounitprice;
	}

	public int getCargoamount() {
		return cargoamount;
	}

	public void setCargoamount(int cargoamount) {
		this.cargoamount = cargoamount;
	}
	
	
	
}
