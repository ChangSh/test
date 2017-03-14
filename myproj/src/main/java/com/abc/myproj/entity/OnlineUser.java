/**
 * File：OnlineUser.java
 * Package：com.fang.bdp.core.online.service
 * Author：wangjiashuai
 * Date：2015-6-16 上午10:06:49
 * Copyright (C) 2003-2015 搜房资讯有限公司-版权所有
 */
package com.abc.myproj.entity;

import java.io.Serializable;

/**
 * 在线用户
 * 
 * @author wangjiashuai
 */
public class OnlineUser implements Serializable {
  
  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = -997133926697144363L;
  
  /**
   * 用户登录IP
   */
  private String ip;
  
  /**
   * 用户名称
   */
  private String name;
  
  /**
   * 登录时间
   */
  private String loginTime;
  
  /**
   * 最后访问时间
   */
  private String lastAccessTime;
  
  /**
   * con
   */
  public OnlineUser() {
  }
  
  /**
   * @param ip
   *        ip
   * @param name
   *        用户名
   * @param loginTime
   *        登录时间
   * @param lastAccessTime
   *        最后访问时间
   */
  public OnlineUser(String ip, String name, String loginTime, String lastAccessTime) {
    this.ip = ip;
    this.name = name;
    this.loginTime = loginTime;
    this.lastAccessTime = lastAccessTime;
  }
  
  /**
   * @return ip
   */
  public String getIp() {
    return ip;
  }
  
  /**
   * @param ip
   *        set ip
   */
  public void setIp(String ip) {
    this.ip = ip;
  }
  
  /**
   * @return name
   */
  public String getName() {
    return name;
  }
  
  /**
   * @param name
   *        set name
   */
  public void setName(String name) {
    this.name = name;
  }
  
  /**
   * @return loginTime
   */
  public String getLoginTime() {
    return loginTime;
  }
  
  /**
   * @param loginTime
   *        set loginTime
   */
  public void setLoginTime(String loginTime) {
    this.loginTime = loginTime;
  }

  
  /**
   * @return lastAccessTime
   */
  public String getLastAccessTime() {
    return lastAccessTime;
  }

  
  /**
   * @param lastAccessTime set lastAccessTime
   */
  public void setLastAccessTime(String lastAccessTime) {
    this.lastAccessTime = lastAccessTime;
  }
  
}
