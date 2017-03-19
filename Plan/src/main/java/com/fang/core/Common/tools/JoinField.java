package com.fang.core.Common.tools;


/**
 * 用于多表链接封装FilterBean.value用的
 * 列如：A表与B表需要关联
 * 可以写Filter.addCondition((tableName) "A",(fieldName) "id",(value) new JoinField("B.id"));
 * @author pengmaokui
 */
public class JoinField {
  /**
   * 值
   */
  private String value;
  
  /**
   * 构造函数
   * @param value 值
   */
  public JoinField(String value) {
    this.value = value;
  }

  /**
   * @return value
   */
  public String getValue() {
    return value;
  }
  
  /**
   * @param value
   *        set value
   */
  public void setValue(String value) {
    this.value = value;
  }  
  
  @Override
  public String toString() {
    return value;
  }
}
