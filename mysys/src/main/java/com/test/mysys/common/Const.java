package com.test.mysys.common;

public class Const {

	public static final boolean DEBUG = false;
	// 定单处理
	public static final String ORDER_COMMITED = "01";
	public static final String ORDER_FINISHED = "99";
	public static final String ORDER_PUBLISHED = "03";// 已发布
	public static final String ORDER_OPERATING = "02";// 处理中
	public static final String ORDER_RECEIVED = "04";// 已接单
	public static final String ORDER_CANCELED = "-1";
	public static final String ORDER_GCB = "工程部";// 工程部

	public static final String DEFAULT_PASSWORD = "111111";// 系统缺省密码

	public static final String PUBLISHTYPE_POST = "1";// 派
	public static final String PUBLISHTYPE_ROB = "2";// 抢

}
