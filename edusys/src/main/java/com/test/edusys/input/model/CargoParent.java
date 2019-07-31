package com.test.edusys.input.model;

import org.nutz.dao.entity.annotation.*;

@Table("t_cargo_p")
public class CargoParent {
	@Name
	private String id;
	@Column
	private String cargotime;// 进货时间
	@Column
	private double cargosum;// 进货总额

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCargotime() {
		return cargotime;
	}

	public void setCargotime(String cargotime) {
		this.cargotime = cargotime;
	}

	public double getCargosum() {
		return cargosum;
	}

	public void setCargosum(double cargosum) {
		this.cargosum = cargosum;
	}

}
