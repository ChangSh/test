package com.fang.plan.service;

import com.baomidou.mybatisplus.service.IService;
import com.fang.plan.entity.AdPlace;
import java.util.List;
import com.fang.plan.entity.*;
/**
*
* AdPlace 表数据服务层接口
*
*/
public interface IAdPlaceService  extends IService<AdPlace>{
  
  List<Website> GetWebsiteList();
  
  Website GetWebsiteInfo(String id);
  
  List<Channelgroup> GetChannel(int id);
  
  boolean AddAdplace(AdPlace a);
  
  List<City> GetCitys();
  
  boolean AddChuanYangAdPlaceId(int adplaceid,int ChuanYangAdPlaceId);
}

