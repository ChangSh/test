package com.fang.core.Common.tools;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;

/**
 * logback封装类
 * 
 * @author zhangjy
 */
public final class BdpLogger {
  
  /**
   * logback的logger
   */
  private static Logger logger = null;
  
  /**
   * 私有化构造器
   */
  private BdpLogger() {
  }
  
  /**
   * 
   * @param fqcn
   *        类型名称
   * @return Logger logback的logger
   */
  private static Logger getLogger(String fqcn) {
    return (Logger) LoggerFactory.getLogger(fqcn);
  }
  
  /**
   * 输出trace级别信息
   * 
   * @param msg
   *        日志信息
   */
  public static void trace(String msg) {
    synchronized (BdpLogger.class) {
      BdpLogger.logger = getLogger(getCurrentClass());
      logger.trace(msg);
    }
  }
  
  /**
   * 输出debug级别信息
   * 
   * @param msg
   *        日志信息
   */
  public static void debug(String msg) {
    synchronized (BdpLogger.class) {
      BdpLogger.logger = getLogger(getCurrentClass());
      logger.debug(msg);
    }
  }
  
  /**
   * 输出info级别信息
   * 
   * @param msg
   *        日志信息
   */
  public static void info(String msg) {
    synchronized (BdpLogger.class) {
      BdpLogger.logger = getLogger(getCurrentClass());
      logger.info(msg);
    }
  }
  
  /**
   * 输出warn级别信息
   * 
   * @param msg
   *        日志信息
   */
  public static void warn(String msg) {
    synchronized (BdpLogger.class) {
      BdpLogger.logger = getLogger(getCurrentClass());
      logger.warn(msg);
    }
  }
  
  /**
   * 输出error级别信息
   * 
   * @param msg
   *        日志信息
   */
  public static void error(String msg) {
    synchronized (BdpLogger.class) {
      BdpLogger.logger = getLogger(getCurrentClass());
      logger.error(msg);
    }
  }
  
  /**
   * 输出error级别信息
   * 
   * @param e
   *        异常
   */
  public static void error(Exception e) {
    synchronized (BdpLogger.class) {
      BdpLogger.logger = getLogger(getCurrentClass());
      logger.error(e.toString());
    }
  }
  
  /**
   * 
   * @return String 调用当前类的类名称
   */
  private static String getCurrentClass() {
    String classname = "";
    StackTraceElement[] stacks = Thread.currentThread().getStackTrace();
    for (int i = 0; i < stacks.length; i++) {
      // 循环判断堆栈中哪一个是com.fang.bdp.core.util.BdpLogger
      if (stacks[i].getClassName().equals(BdpLogger.class.getName())) {
        // 判断下一个是不是，如果不是返回，是继续循环
        if (!stacks[i + 1].getClassName().equals(BdpLogger.class.getName())) {
          classname = stacks[i + 1].getClassName();
        }
      }
    }
    return classname;
  }
  
}
