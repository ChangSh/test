package com.fang.core.Common.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * jdbc.properties 下的数据库配置文件信息
 * 
 * @author mahw
 */
public final class DbConnectionProConfig {
  
  /**
   * 构造方法私有化，避免被创建
   * 
   * @param path 
   * @return dbConfigModel
   */
  public static DbConfigModel getDbConfig(String path) {
    Properties prop = new Properties();
    DbConfigModel model = null;
    InputStream in = null;
    try {
      in = new FileInputStream(new File(path));
      if (in != null) {
        prop.load(in);
        
        model = new DbConfigModel();
        
        model.setSupply(prop.getProperty("jdbc.supply"));
        model.setDriver(prop.getProperty("jdbc.driver"));
        model.setUrl(prop.getProperty("jdbc.url"));
        model.setUsername(prop.getProperty("jdbc.username"));
        model.setPassword(prop.getProperty("jdbc.password"));
        model.setDbName(prop.getProperty("jdbc.dbName"));
        model.setSqlInit(prop.getProperty("sqlInit"));
        model.setIsUpdate(prop.getProperty("isUpdate"));
        model.setIsShowSql(prop.getProperty("isShowSql"));
      }
    }
    catch (Exception e) {
      e.printStackTrace(System.err);
    }
    finally {
      try {
        in.close();
      }
      catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    return model;
  }
  
  /**
   * 构造方法私有化，避免被创建
   * 
   * @author wangyw
   */
  private DbConnectionProConfig() {
  }
}
