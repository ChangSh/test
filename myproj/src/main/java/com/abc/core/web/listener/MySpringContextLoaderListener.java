package com.abc.core.web.listener;

import javax.servlet.ServletContextEvent;

import org.springframework.web.context.ContextLoaderListener;

import com.abc.core.springcontext.util.SpringContextUtils;
import com.abc.core.web.security.ShiroFilerChainManager;
import com.abc.myproj.service.IUserInitService;



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
