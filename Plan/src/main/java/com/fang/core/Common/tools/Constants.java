/**
 * File：Constants.java
 * Package：com.fang.bdp.core.commons
 * Author：wangjiashuai
 * Date：2015-6-19 上午10:43:05
 * Copyright (C) 2003-2015 搜房资讯有限公司-版权所有
 */
package com.fang.core.Common.tools;


/**
 * 
 * 通用变量
 * 
 * @author wangjiashuai
 */
public final class Constants {
  
  /**
   * 存放项目properties文件的公共路径
   */
  public static final String COMM_PATH = "com/fang/bdp/core/conf/";
  
  /**
   * 是否使用单点
   */
  public static final boolean ISSO = Boolean.parseBoolean(PropertiesReaderUtils.getProperties(
      COMM_PATH + "constants/constants.properties", "isso"));
  
  /**
   * 如果使用单点登录，服务号（需要联系OA开通申请单点登录接入功能）
   */
  public static final String ISSO_SERVICE = PropertiesReaderUtils.getProperties(COMM_PATH
      + "constants/constants.properties", "isso_service");
  
  /**
   * 国际化资源文件目录
   */
  public static final String LOCALESTRINGS_ZH_CN = PropertiesReaderUtils.getProperties(COMM_PATH
      + "constants/constants.properties", "localestrings_zh_cn");
  
  
  public static final String SMALLFile_LOCAL_PATH = PropertiesReaderUtils.getProperties(COMM_PATH
      + "api/api.properties", "api_uploadtencent");
  
  /**
   * 私有的构造方法
   */
  private Constants() {
  }
  
}
