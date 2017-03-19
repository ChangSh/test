/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.test.edusys.common.utils;

import org.nutz.dao.pager.Pager;

public class Pagination extends Pager { 

	public static final String ASC = "asc";
	public static final String DESC = "desc";
	
	private String order;//DESC ASC
	private String orderBy;//排序字段
	private String[] orders;//多个排序字段  key为字段 value 为asc  new String[]{"hc", pager.DESC,"filename", pager.ASC}
	
	//---------------------------------
	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String[] getOrders() {
		return orders;
	}

	public void setOrders(String[] orders) {
		this.orders = orders;
	}

	
	
}
