package com.fang.plan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fang.plan.entity.AdPlace;
import com.fang.plan.entity.AdPlacePrice;
import com.fang.plan.entity.Banner;
import com.fang.plan.entity.BannerAdplaceRelation;
import com.fang.plan.entity.Plan_Detail;


/**
 *
 * BannerAdplace 表数据库控制层接口
 *
 */
public interface BannerAdplaceRelationMapper extends BaseMapper<BannerAdplaceRelation> {
  
  public List<AdPlace> selectPlacePriceList(@Param("entity") AdPlacePrice entity,@Param("sql") String sql);
  
  public List<Plan_Detail> getBannerAdplaceID(@Param("sql") String querySql);
  
  public List<Banner> getBannerInfo(@Param("sql") String querySql);
  
  public int deleteByAdplaceid(int id);
  
}