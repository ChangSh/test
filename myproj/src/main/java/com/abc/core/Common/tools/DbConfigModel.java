package com.abc.core.Common.tools;

import java.io.Serializable;

/**
 * @author wangyw
 * 数据库配置模型
 */
public class DbConfigModel implements Serializable {
  
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  /**
   * 数据库供应类型(oracle,sqlServer......)
   */
  private  String supply;
  
  /**
   * 驱动名称
   */
  private  String driver;
  
  /**
   * 连接
   */
  private  String url;
  
  /**
   * 用户名
   */
  private  String username;
  
  /**
   * 密码
   */
  private  String password;
  
  /**
   * 链接数据库
   */
  private String dbName;
  
  /**
   * sql初始化（true）进行系统初始化
   */
  private String sqlInit;
  
  /**
   * 是否更新
   */
  private String isUpdate;
  
  /**
   * 是否显示sql
   */
  private String isShowSql;
  
  /**
   * @return supply
   */
  public String getSupply() {
    return supply;
  }
  
  /**
   * @param supply set supply
   */
  public void setSupply(String supply) {
    this.supply = supply;
  }
  
  /**
   * @return driver
   */
  public String getDriver() {
    return driver;
  }
  
  /**
   * @param driver set driver
   */
  public void setDriver(String driver) {
    this.driver = driver;
  }
  
  /**
   * @return url
   */
  public String getUrl() {
    return url;
  }
  
  /**
   * @param url set url
   */
  public void setUrl(String url) {
    this.url = url;
  }
  
  /**
   * @return username
   */
  public String getUsername() {
    return username;
  }
  
  /**
   * @param username set username
   */
  public void setUsername(String username) {
    this.username = username;
  }
  
  /**
   * @return password
   */
  public String getPassword() {
    return password;
  }
  
  /**
   * @param password set password
   */
  public void setPassword(String password) {
    this.password = password;
  }
  
  /**
   * @return dbName
   */
  public String getDbName() {
    return dbName;
  }
  
  /**
   * @param dbName set dbName
   */
  public void setDbName(String dbName) {
    this.dbName = dbName;
  }
  
  /**
   * @return sqlInit
   */
  public String getSqlInit() {
    return sqlInit;
  }
  
  /**
   * @param sqlInit set sqlInit
   */
  public void setSqlInit(String sqlInit) {
    this.sqlInit = sqlInit;
  }
  
  /**
   * @return isUpdate
   */
  public String getIsUpdate() {
    return isUpdate;
  }
  
  /**
   * @param isUpdate set isUpdate
   */
  public void setIsUpdate(String isUpdate) {
    this.isUpdate = isUpdate;
  }
  
  /**
   * @return isShowSql
   */
  public String getIsShowSql() {
    return isShowSql;
  }
  
  /**
   * @param isShowSql set isShowSql
   */
  public void setIsShowSql(String isShowSql) {
    this.isShowSql = isShowSql;
  }
  
  
}
