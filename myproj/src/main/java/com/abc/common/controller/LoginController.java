/**
 * File：HomeController.java
 * Package：com.abc.platform.core.controller
 * Author：wangjiashuai
 * Date：2015-5-8 下午2:09:29
 * Copyright (C) 2003-2015 搜房资讯有限公司-版权所有
 */
package com.abc.common.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.abc.core.Common.tools.Constants;
import com.abc.core.Common.tools.CtResult;
import com.abc.core.Common.tools.PropertiesReaderUtils;
import com.abc.core.web.security.MyShiroRealm;
import com.abc.myproj.entity.Resource;
import com.abc.myproj.entity.UserInit;
import com.abc.myproj.service.IUserInitService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;

/**
 * 登录
 * 
 * @author 陈军 改自 dbp
 */
@Controller
@RequestMapping
public class LoginController {
  
  /**
   * User service
   */
  @Autowired
  private IUserInitService userService;
  
  /**
   * 自定义realm对象
   */
  @Autowired
  @Qualifier("myShiro")
  private MyShiroRealm myShiroRealm;
  
  private ModelAndView t_mav = new ModelAndView("bdp/pages/index");
  
  /**
   * 登录
   * 
   * @param req
   *        request
   * @param res
   *        response
   * @return 跳转页
   * @throws IOException
   *         IOException
   */
  @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
  public ModelAndView login(HttpServletRequest req, HttpServletResponse res,String email) {
    
    // 调用onlogout方法清权限缓存
    myShiroRealm.onLogout(SecurityUtils.getSubject().getPrincipals());
    // 触发权限读取
    myShiroRealm.isPermitted(SecurityUtils.getSubject().getPrincipals(), "强制shiro检查加载用户权限缓存,避免懒加载!"
        + System.currentTimeMillis());   
    List<Resource> re = new ArrayList<Resource>();
    ModelAndView f_mav = new ModelAndView("login");
    ModelAndView d_mav = new ModelAndView("bdp/pages/errors/denied");
    UsernamePasswordToken stoken = new UsernamePasswordToken(email, "");
    Subject subject = SecurityUtils.getSubject();
    try {
          subject.login(stoken);
          if (!subject.isAuthenticated()) {
            return f_mav;
          }
          else{
            UserInit soufunTemp = userService.selectOne(new EntityWrapper<UserInit>().eq("email", email));
            
            // 用户session
            req.getSession().setAttribute("Plan_RealName", soufunTemp.getRealName());
            req.getSession().setAttribute("Plan_Email", soufunTemp.getEmail());
            req.getSession().setAttribute("Plan_UserID", soufunTemp.getOaUserID());
            req.getSession().setAttribute("Plan_UserName", soufunTemp.getUserName());   

            // 资源加载 
            re = userService.mySource(soufunTemp.getEmail());
            String listStr = JSONObject.toJSONString(re);
            JSONArray array = JSONArray.parseArray(listStr);
            t_mav.addObject("treeNodes", array);
          }
          
    }
    catch (Exception e) {
          return d_mav;
    }  
    return  new ModelAndView("redirect:index.do");
  }
  

  
  @RequestMapping(value = "/index", method = {RequestMethod.GET, RequestMethod.POST})
  public ModelAndView index() throws IOException {
    return t_mav;
  }
  
  /**
   * 获取登录超时时间，供页面上倒计时使用
   * 
   * @param req
   *        req
   * @return result
   */
  @RequestMapping("/getTimeOut")
  @ResponseBody
  public CtResult getTimeOut(HttpServletRequest req) {
    final String address = Constants.COMM_PATH + "constants/constants.properties";
    String timeOut = PropertiesReaderUtils.getProperties(address, "timeout");
    if (timeOut.equals("timeout")) {
      timeOut = "864000";
    }
    CtResult ctResult = CtResult.success(timeOut);
    return ctResult;
  }
}
