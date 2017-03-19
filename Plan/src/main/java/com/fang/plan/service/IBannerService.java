package com.fang.plan.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.fang.plan.entity.Banner;


/**
*
* Banner 表数据服务层接口
*
*/
public interface IBannerService  extends IService<Banner>{
  
   List<Banner> getAllBanner();
   
   List<String> getAllOrderId();
   
   List<Banner> getBannerbyOrderId(String OrderId);
}