package com.abc.myproj.service;

import com.abc.myproj.entity.*;
import com.baomidou.mybatisplus.service.IService;
import java.util.List;
/**
*
* AdPlace 表数据服务层接口
*
*/
public interface IAdPlaceService  extends IService<AdPlace>{
  
  List<Website> GetWebsiteList();
  
  Website GetWebsiteInfo(String id);
  
  List<Channelgroup> GetChannel();
  
  boolean AddAdplace(AdPlace a);
  
  List<City> GetCitys();
}

