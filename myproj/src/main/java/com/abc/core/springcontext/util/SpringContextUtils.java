package com.abc.core.springcontext.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;


/**
 * 
 * @author user
 */
@Component
public class SpringContextUtils implements ApplicationContextAware {
  /**
   * spring上下文
   */
  private static ApplicationContext context;
  
  /**
   * 获取springContext中的类
   * 
   * @param beanName 类名
   * @return 类
   */
  public static Object getBean(String beanName) {
    return getContext().getBean(beanName);
  }
  
  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    // TODO Auto-generated method stub
    SpringContextUtils.setContext(applicationContext);
  }
  
  /**
   * @return context
   */
  public static ApplicationContext getContext() {
    return context;
  }
  
  /**
   * @param context
   *        set context
   */
  public static void setContext(ApplicationContext context) {
    SpringContextUtils.context = context;
  }
  
}
