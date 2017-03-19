package com.fang.common.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fang.core.Common.tools.Constants;
import com.fang.core.Common.tools.CtResult;


/**
 * 登出
 * 
 * @author wangjiashuai
 */
@Controller
public class LogoutController {
  
  /**
   * 日志
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(LogoutController.class);
  
  /**
   * 域名
   */
  private static final String DOMAIN_FANG = ".fang.com";
  
  /**
   * 用户退出
   * 
   * @param res
   *        res
   * @return 结果
   */
  @ResponseBody
  @RequestMapping(value = "/logout", method = RequestMethod.POST)
  public CtResult logout(HttpServletRequest req,HttpServletResponse res) {
    try {
      SecurityUtils.getSubject().logout();
      if (Constants.ISSO) {
        clearCookies(req,res);
      }
      req.getSession().removeAttribute("Plan_RealName");
      req.getSession().removeAttribute("Plan_Email");
      req.getSession().removeAttribute("Plan_UserName");
      req.getSession().removeAttribute("Plan_UserID");
      
      return CtResult.success();
    }
    catch (Exception e) {
      LOGGER.error(e.getMessage());
      return CtResult.failure("用户退出失败。");
    }
  }
  
  /**
   * 退出时清除单点Cookie
   * 
   * @param res
   *        res
   */
  public void clearCookies(HttpServletRequest req,HttpServletResponse res) {
    Cookie uuidCookie = new Cookie("isso_uuid", null);
    uuidCookie.setMaxAge(0);
    uuidCookie.setDomain(DOMAIN_FANG);
    uuidCookie.setPath("/");
    
    Cookie loginCookie = new Cookie("isso_login", null);
    loginCookie.setMaxAge(0);
    loginCookie.setDomain(DOMAIN_FANG);
    loginCookie.setPath("/");
    
    Cookie passCookie = new Cookie("isso_passwd", null);
    passCookie.setMaxAge(0);
    passCookie.setDomain(DOMAIN_FANG);
    passCookie.setPath("/");
    
    Cookie ticketCookie = new Cookie("isso_ticket", null);
    ticketCookie.setMaxAge(0);
    ticketCookie.setDomain(DOMAIN_FANG);
    ticketCookie.setPath("/");
  }
}
