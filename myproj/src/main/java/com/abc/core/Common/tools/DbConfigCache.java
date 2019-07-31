package com.abc.core.Common.tools;

import javax.servlet.ServletContext;

import com.abc.core.Common.tools.BdpLogger;
import com.abc.core.Common.tools.Constants;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;


/**
 * 数据库配置信息缓存
 * 
 * @author wangyw
 */
public final class DbConfigCache {
  /**
   * 私有构造方法
   */
  private DbConfigCache() {
  }
  
  /**
   * 初始化数据库配置信息
   * @param servletContext 
   */
  public static void init(ServletContext servletContext) {
//  String path = servletContext.getRealPath("/WEB-INF/conf/core/jdbc.properties");
	  
	String realPath = DbConfigCache.class.getClassLoader()
			.getResource(Constants.COMM_PATH + "db/jdbc.properties").getPath();
    DbConfigModel dbConfigModel = DbConnectionProConfig.getDbConfig(realPath);
    regiestCache(dbConfigModel);
  }
  
  /**
   * 注册dbConfigCache模型
   * @param model 
   */
  public static void regiestCache(DbConfigModel model) {
    CacheManager manager = CacheManager.getInstance();
    
    manager.addCache(CacheConst.DB_CONFIGMODEL);
    
    Cache cache = manager.getCache(CacheConst.DB_CONFIGMODEL);
    Element element = new Element("model", model);
    // 设置为永久缓存，超时被忽略
    element.setEternal(true);
    
    cache.put(element);
    BdpLogger.info("============加载DbConfig配置缓存【OK】=================");
  }
  
  /**
   * 获取DbConfig缓存
   * 
   * @return model
   */
  public static DbConfigModel getModel() {
    CacheManager manager = CacheManager.getInstance();
    
    Cache cache = manager.getCache(CacheConst.DB_CONFIGMODEL);
    
    Element element = cache.get("model");
    
    DbConfigModel model = (DbConfigModel) element.getObjectValue();
    
    return model;
  }
}
