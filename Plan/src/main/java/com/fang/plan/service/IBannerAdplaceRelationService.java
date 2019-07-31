package com.fang.plan.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.service.IService;
import com.fang.plan.entity.AdPlace;
import com.fang.plan.entity.AdPlacePrice;
import com.fang.plan.entity.Banner;
import com.fang.plan.entity.BannerAdplaceRelation;
import com.fang.plan.entity.Plan_Detail;
/**
*
* BannerAdplace 表数据服务层接口
*
*/
public interface IBannerAdplaceRelationService  extends IService<BannerAdplaceRelation>{
  
  public List<Plan_Detail> getBannerAdplaceID(String orderId,String AdpId,String date,String reserveHour);
  
  public List<Banner> getBannerInfo(String orderId,String AdpId,String date,String reserveHour);
  
  public int deleteByAdplaceid(List<BannerAdplaceRelation> entityList);
  
  public int bandBannerAdplace(List<BannerAdplaceRelation> entityList,String userName);
  
  public List<AdPlace> selectPlacePriceList(AdPlacePrice entity);
}