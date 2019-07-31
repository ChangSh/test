package com.abc.myproj.entity;

import java.util.List;


public class OrderBody {
  
  private List<OrderDetails> list;

  
  public List<OrderDetails> getList() {
    return list;
  }

  
  public void setList(List<OrderDetails> list) {
    this.list = list;
  }
}
