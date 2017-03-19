package com.abc.myproj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.myproj.entity.*;
import com.abc.myproj.mapper.AdPlaceMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

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
  public List<Channelgroup> GetChannel() {
    // TODO Auto-generated method stub
    return AdPlaceMapper.GetChannel();
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



 
  
}
