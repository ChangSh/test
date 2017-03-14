package com.abc.myproj.entity;

import java.io.Serializable;


public class OrderInfo implements Serializable{
  private static final long serialVersionUID = 1L;
  private String OrderCode;
  private String ProjectName;
  private String CityName;
  private String ServiceDateStart;
  private String ServiceDateEnd;
  
  public String getOrderCode() {
    return OrderCode;
  }
  
  public void setOrderCode(String orderCode) {
    OrderCode = orderCode;
  }
  
  public String getProjectName() {
    return ProjectName;
  }
  
  public void setProjectName(String projectName) {
    ProjectName = projectName;
  }
  
  public String getCityName() {
    return CityName;
  }
  
  public void setCityName(String cityName) {
    CityName = cityName;
  }
  
  public String getServiceDateStart() {
    return ServiceDateStart;
  }
  
  public void setServiceDateStart(String serviceDateStart) {
    ServiceDateStart = serviceDateStart;
  }
  
  public String getServiceDateEnd() {
    return ServiceDateEnd;
  }
  
  public void setServiceDateEnd(String serviceDateEnd) {
    ServiceDateEnd = serviceDateEnd;
  }
 
}
