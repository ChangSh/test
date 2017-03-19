package com.fang.core.web.listener;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContextEvent;
import org.springframework.web.context.ContextLoaderListener;

import com.fang.core.springcontext.util.SpringContextUtils;
import com.fang.core.web.security.ShiroFilerChainManager;
import com.fang.plan.service.IUserInitService;
import com.fang.plan.service.UserInitServiceImpl;



/**
 * 重写spring的监听器, 项目启动时加载
 * 
 * @author wangyw
 */
public class MySpringContextLoaderListener extends ContextLoaderListener {
  
  @Override
  public void contextInitialized(ServletContextEvent event) {
    super.contextInitialized(event);
     initFilterChain();
  }
  

  
  private void initFilterChain() {
    IUserInitService resource = (IUserInitService) SpringContextUtils.getBean("userInitServiceImpl");
    ShiroFilerChainManager shiroFilerChainManager = (ShiroFilerChainManager) SpringContextUtils
        .getBean("shiroFilerChainManager");
    shiroFilerChainManager.init();
    shiroFilerChainManager.initFilterChains(resource.myPermission(""));
  }
}
