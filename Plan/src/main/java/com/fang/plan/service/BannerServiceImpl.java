package com.fang.plan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fang.plan.entity.Banner;
import com.fang.plan.entity.SizeInfo;
import com.fang.plan.mapper.BannerMapper;
import com.fang.plan.mapper.SizeInfoMapper;

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
  public List<Banner> getBannerbyOrderId(String orderid){
    return mapper.getBannerbyOrderId(orderid);
  }

 
  
}
