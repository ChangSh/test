/**
 * File：MyShiroFilter.java
 * Package：com.fang.bdp.core.web.security
 * Author：wangjiashuai
 * Date：2015-6-16 下午3:05:53
 * Copyright (C) 2003-2015 搜房资讯有限公司-版权所有
 */
package com.fang.core.web.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.DelegatingFilterProxy;

/**
 * 自定义ShiroFilter
 * 
 * @author wangjiashuai
 */
public class MyShiroFilter extends DelegatingFilterProxy {
  
  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    if (!(request instanceof HttpServletRequest)) {
      super.doFilter(request, response, filterChain);
      return;
    }
    
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    HttpServletResponse httpResponse = (HttpServletResponse) response;
    String url = httpRequest.getRequestURL().toString();
    /**
     * 由于Resin等服务器原因，如果用户已经登录，这时服务器重启，再次刷新页面时由于之前的SessionID已经不存在于Server上，
     * 这样会重新生成一个新的SessionId，此时新的SessionId也不存在于Shiro中，因此进行了Redirect来规避此问题
     */
    if (url.contains("login.jsp;JSESSIONID")) {
      httpResponse.sendRedirect("login.jsp");
      return;
    }
    super.doFilter(request, response, filterChain);
  }
}
