package com.abc.myproj.service;

import java.util.List;

import com.abc.myproj.entity.BannerAdplaceRelation;
import com.baomidou.mybatisplus.service.IService;
/**
*
* BannerAdplace 表数据服务层接口
*
*/
public interface IBannerAdplaceRelationService  extends IService<BannerAdplaceRelation>{
  
  public List<BannerAdplaceRelation> getBannerAdplaceID(String orderId,String AdpId,String date,String allowance);
  
  public List<BannerAdplaceRelation> getBannerId(String orderId,String AdpId,String date,String allowance);
  
}