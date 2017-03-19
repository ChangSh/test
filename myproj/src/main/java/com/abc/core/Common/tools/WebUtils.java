/**
 * File：WebUtils.java
 * Package：com.fang.bdp.core.util
 * Author：wangjiashuai
 * Date：2015-7-9 上午10:25:23
 * Copyright (C) 2003-2015 搜房资讯有限公司-版权所有
 */
package com.abc.core.Common.tools;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;


import com.abc.core.springcontext.util.SpringContextUtils;

/**
 * web utils
 * 主要用来：
 * 1、获取当前登录用户名
 * 2、获取当前用户登录IP
 * 3、获取项目名，如bdp.core
 * 
 * @author wangjiashuai
 */
public final class WebUtils {
  
  /**
   * 项目名
   */
  public static final String PROJECT_NAME = SpringContextUtils.getContext().getApplicationName();
  
  /**
   * 获取当前用户名
   * 
   * @return 当前用户名
   */
  public static String getCurrentUserName() {
    Subject currentUser = SecurityUtils.getSubject();
    if (currentUser == null) {
      return "anno";
    }
    Object prin = currentUser.getPrincipal();
    if (null != prin) {
      return prin.toString();
    }
    else {
      return "anno";
    }
  }
  
  /**
   * 获取当前登录的用户登录IP地址。
   * 
   * @return 当前登录的用户登录IP地址
   */
  public static String getCurrentUserIp() {
    Subject currentUser = SecurityUtils.getSubject();
    if (currentUser == null) {
      return "unknown";
    }
    Session session = currentUser.getSession(false);
    if (null != session) {
      return session.getHost();
    }
    else {
      return "unknown";
    }
  }
  
  /**
   * 获取项目名，如果"/bdp.core"
   * 
   * @return 项目Web路径
   */
  public static String getWebPath() {
    return PROJECT_NAME;
  }
  
  /**
   * private con
   */
  private WebUtils() {
  }
}
