package com.fang.core.Common.tools;

/**
 * service层操作结果
 * 
 * @author wangyouwei
 */
public class ServiceResult<T> {
  
  /**
   * 描述
   * 
   */
  private String desc;
  
  /**
   * 错误信息
   * 
   */
  private String errorMessage;
  
  /**
   * 
   * 类型，不同类型执行不同的切面操作 ,默认基础操作
   */
  private OpLogEnum type=OpLogEnum.simpleOptLog;
  
  /**
   *日志对象 
   */
  private T obj;
  
  
  
  
  public T getObj() {
    return obj;
  }


  
  public void setObj(T obj) {
    this.obj = obj;
  }


  /**
   * 构造方法
   * @param desc 
   */
  public ServiceResult(String desc) {
    this.desc = desc;
  }


  /**
   * @return desc
   */
  public String getDesc() {
    return desc;
  }

  
  /**
   * @param desc set desc
   */
  public void setDesc(String desc) {
    this.desc = desc;
  }

  
  /**
   * @return errorMessage
   */
  public String getErrorMessage() {
    return errorMessage;
  }

  
  /**
   * @param errorMessage set errorMessage
   */
  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }


  
  public OpLogEnum getType() {
    return type;
  }


  
  public void setType(OpLogEnum type) {
    this.type = type;
  }


  
  
}
