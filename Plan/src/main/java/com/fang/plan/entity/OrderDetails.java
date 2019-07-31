package com.fang.plan.entity;

import java.util.List;


public class OrderDetails {
  
   
  public AdPlacePrice getDetails() {
    return Details;
  }

  
  public void setDetails(AdPlacePrice details) {
    Details = details;
  }

  
  public List<String> getTimes() {
    return Times;
  }

  
  public void setTimes(List<String> times) {
    Times = times;
  }

  private AdPlacePrice Details;
   
   private List<String> Times;
  
}
