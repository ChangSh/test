package com.abc.myproj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.myproj.entity.Banner;
import com.abc.myproj.entity.SizeInfo;
import com.abc.myproj.mapper.BannerMapper;
import com.abc.myproj.mapper.SizeInfoMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
*
* Banner 表数据服务层接口实现类
*
*/
@Service
public class BannerServiceImpl extends ServiceImpl<BannerMapper, Banner> implements IBannerService {
  
  @Autowired
  private  BannerMapper mapper;
  
  @Override
  public List<Banner> getAllBanner() {
    // TODO Auto-generated method stub
    return mapper.getAllBanner();
  }

  @Override
  public List<String> getAllOrderId() {
    // TODO Auto-generated method stub
    return mapper.getAllOrderId();
  }

  @Override
  public List<Banner> getBannerbyIdAndType(String type,String userName) {
    // TODO Auto-generated method stub
    Banner entity=new Banner();
    entity.setAddoperator(userName);
    entity.setBannertype(type);
    return mapper.getBannerbyIdAndType(entity);
  }

 
  
}
