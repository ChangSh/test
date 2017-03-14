package com.abc.myproj.entity;

import java.io.Serializable;


public class City implements Serializable{
  
 private static final long serialVersionUID = 1L;
 
 public int CityId;
 public String CityName;
 public String ShortName;

public int getCityId() {
  return CityId;
}

public void setCityId(int cityId) {
  CityId = cityId;
}

public String getCityName() {
  return CityName;
}

public void setCityName(String cityName) {
  CityName = cityName;
}

public String getShortName() {
  return ShortName;
}

public void setShortName(String shortName) {
  ShortName = shortName;
}
 
 
}
