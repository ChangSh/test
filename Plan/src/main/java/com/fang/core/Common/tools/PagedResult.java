package com.fang.core.Common.tools;

import java.util.List;


/**
 * bootstrap-Table 返回页面的对象
 * @author pengmaokui
 * @param <T>
 */
public class PagedResult<T> {

  /**
   * 记录总数
   */
  private long total;
  /**
   * 值
   */
  private List<T> rows;

  /**
   * 空构造函数
   */
  public PagedResult() {
    
  }
  
  private Object Others;
  
  
  
  public Object getOthers() {
    return Others;
  }

  
  public void setOthers(Object others) {
    Others = others;
  }

  /**
   * 构造函数
   * @param total 总数
   * @param rows 数据
   */
  public PagedResult(long total, List<T> rows) {
    this.rows = rows;
    this.total = total;
  }
  
  /**
   * @return total
   */
  public long getTotal() {
    return total;
  }
  
  /**
   * @param total
   *        set total
   */
  public void setTotal(long total) {
    this.total = total;
  }
  
  /**
   * @return rows
   */
  public List<T> getRows() {
    return rows;
  }
  
  /**
   * @param rows
   *        set rows
   */
  public void setRows(List<T> rows) {
    this.rows = rows;
  }
}
