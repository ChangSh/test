/**
 * File：PageLoadingTimeInterceptor.java
 * Package：com.fang.bdp.core.web.spring.interceptor
 * Author：wangjiashuai
 * Date：2015-7-6 上午9:44:45
 * Copyright (C) 2003-2015 搜房资讯有限公司-版权所有
 */
package com.abc.core.web.spring.interceptor;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.abc.core.Common.annotation.Token;

/**
 * 拦截器：加载每个模块的首页时，服务器端加载时间拦截
 * 
 */
public class PageLoadingTimeInterceptor extends HandlerInterceptorAdapter {
  
  /**
   * key
   */
  private static final String PAGE_LOADING_URL = "pageLoadingUrl";
  
  /**
   * key
   */
  private static final String PAGE_LOADING_TIME = "pageLoadingTime";
  
  /**
   * 进入页面时的时间
   */
  private Long preTime;
  
  
  /**
   * 页面信息放到List中
   */
  private List<Map<String, String>> loadingInfoList = new ArrayList<Map<String, String>>();
  
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
/*    String isMenu = request.getParameter("isMenu");
    if (StringUtils.isNotEmpty(isMenu)) {
      this.loadingInfoList.clear();
    }*/
    //重复表单提交
    if (handler instanceof HandlerMethod) {
      HandlerMethod handlerMethod = (HandlerMethod) handler;
      Method method = handlerMethod.getMethod();
      Token annotation = method.getAnnotation(Token. class );
      if (annotation != null ) {
          boolean needSaveSession = annotation.save();
          if (needSaveSession) {
              request.getSession( false ).setAttribute( "token" , UUID.randomUUID().toString());
          }
          boolean needRemoveSession = annotation.remove();
          if (needRemoveSession) {
              if (isRepeatSubmit(request)) {
                return false ;
              }
              request.getSession( false ).removeAttribute( "token" );
          }
      }
      return true;
  } else {
      return super .preHandle(request, response, handler);
  }
  }
  
  private boolean isRepeatSubmit(HttpServletRequest request) {
    String serverToken = (String) request.getSession( false ).getAttribute( "token" );
    if (serverToken == null ) {
        return true ;
    }
    String clinetToken = request.getParameter( "token" );
    if (clinetToken == null ) {
        return true ;
    }
    if (!serverToken.equals(clinetToken)) {
        return true ;
    }
    return false ;
}
  
  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                         ModelAndView modelAndView) throws Exception {
   /* long postTime = System.currentTimeMillis();
    Map<String, String> map = new HashMap<String, String>();
    map.put(PAGE_LOADING_URL, request.getRequestURI());
    // 时间为毫秒
    map.put(PAGE_LOADING_TIME, postTime - preTime + "ms");
    
    loadingInfoList.add(map);
    RequestContextUtils.getWebApplicationContext(request).getBean(PageLoadingController.class)
        .setLoadingInfoList(loadingInfoList);*/
    super.postHandle(request, response, handler, modelAndView);
  }
}
