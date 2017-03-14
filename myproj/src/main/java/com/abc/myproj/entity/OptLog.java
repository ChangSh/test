package com.abc.myproj.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableName;


/**
 * 日志实体bean
 * @author wangyouwei
 */
@TableName("opt_log")
public class OptLog implements Serializable {
  
  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = -2430159558602671948L;
  
  /**
   * Id
   */
  private Long id;
  
  /**
   * 操作员登录名
   */
  private String operatorName;
  
  /**
   * 操作员登录IP
   */
  private String operatorIp;
  
  /**
   * 操作时间（时间对应的毫秒数）。
   */
  private Long optTime;
  
  /**
   * 操作时间字符串的类型
   */
  private String optTimeDateStr;

  /**
   * 操作描述。
   */
  private String optLogDesc;
  
  /**
   * 操作结果
   */
  private String optResult;
  
  /**
   * 失败原因。对于操作成功的情况，该字段无意义。
   */
  private String failReason;
  
  /**
   * 日志类型，1：成功日志，0：失败日志
   */
  private int type;
  
  /**
   * 操作页面
   */
  private String url;
  
  /**
   * 日志类型名称
   */
  private String typeName;
  
  
  
  
  
  public String getUrl() {
    return url;
  }


  
  public void setUrl(String url) {
    this.url = url;
  }




  
  public String getTypeName() {
    return typeName;
  }



  
  public void setTypeName(String typeName) {
    this.typeName = typeName;
  }



  public int getType() {
    return type;
  }

  
  public void setType(int type) {
    this.type = type;
  }

  /**
   * @return id
   */
  public Long getId() {
    return id;
  }
  
  /**
   * @param id set id
   */
  public void setId(Long id) {
    this.id = id;
  }
  
  /**
   * @return operatorName
   */
  public String getOperatorName() {
    return operatorName;
  }
  
  /**
   * @param operatorName set operatorName
   */
  public void setOperatorName(String operatorName) {
    this.operatorName = operatorName;
  }
  
  /**
   * @return operatorIp
   */
  public String getOperatorIp() {
    return operatorIp;
  }
  
  /**
   * @param operatorIp set operatorIp
   */
  public void setOperatorIp(String operatorIp) {
    this.operatorIp = operatorIp;
  }
  
  /**
   * @return optTime
   */
  public Long getOptTime() {
    return optTime;
  }
  
  /**
   * @param optTime set optTime
   */
  public void setOptTime(Long optTime) {
    this.optTime = optTime;
  }
  
  /**
   * @return optTimeDateStr
   */
  public String getOptTimeDateStr() {
    return optTimeDateStr;
  }

  /**
   * @param optTimeDateStr set optTimeDateStr
   */
  public void setOptTimeDateStr(String optTimeDateStr) {
    this.optTimeDateStr = optTimeDateStr;
  }

  /**
   * @return optLogDesc
   */
  public String getOptLogDesc() {
    return optLogDesc;
  }
  
  /**
   * @param optLogDesc set optLogDesc
   */
  public void setOptLogDesc(String optLogDesc) {
    this.optLogDesc = optLogDesc;
  }
  
  /**
   * @return optResult
   */
  public String getOptResult() {
    return optResult;
  }
  
  /**
   * @param optResult set optResult
   */
  public void setOptResult(String optResult) {
    this.optResult = optResult;
  }
  
  /**
   * @return failReason
   */
  public String getFailReason() {
    return failReason;
  }
  
  /**
   * @param failReason set failReason
   */
  public void setFailReason(String failReason) {
    this.failReason = failReason;
  }
}
