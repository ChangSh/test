package com.abc.myproj.service;

import java.util.List;

import com.abc.myproj.entity.Banner;
import com.baomidou.mybatisplus.service.IService;
/**
*
* Banner 表数据服务层接口
*
*/
public interface IBannerService  extends IService<Banner>{
  
   List<Banner> getAllBanner();
   
   List<String> getAllOrderId();
   
   List<Banner> getBannerbyIdAndType(String type,String userName);
}