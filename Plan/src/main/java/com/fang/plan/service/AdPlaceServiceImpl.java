package com.fang.plan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fang.plan.entity.*;
import com.fang.plan.mapper.AdPlaceMapper;

/**
*
* AdPlace 表数据服务层接口实现类
*
*/
@Service
public class AdPlaceServiceImpl extends ServiceImpl<AdPlaceMapper, AdPlace> implements IAdPlaceService {
  @Autowired
  private AdPlaceMapper AdPlaceMapper;
  
  @Override
  public List<Website> GetWebsiteList() {

    
    return AdPlaceMapper.GetWebSiteList();
  }

  @Override
  public Website GetWebsiteInfo(String id) {
    // TODO Auto-generated method stub
    return AdPlaceMapper.GetWebSiteInfo(id);
  }

  @Override
  public List<Channelgroup> GetChannel(int id) {
    // TODO Auto-generated method stub
    return AdPlaceMapper.GetChannel(id);
  }

  @Override
  public boolean AddAdplace(AdPlace a) {
    // TODO Auto-generated method stub
    return AdPlaceMapper.AddAdPlace(a);
  }

  @Override
  public List<City> GetCitys() {
    // TODO Auto-generated method stub
    return AdPlaceMapper.GetCitys();
  }

  @Override
  public boolean AddChuanYangAdPlaceId(int adplaceid, int ChuanYangAdPlaceId) {
    // TODO Auto-generated method stub
    return AdPlaceMapper.AddChuanYangAdPlaceId(adplaceid, ChuanYangAdPlaceId);
  }



 
  
}
