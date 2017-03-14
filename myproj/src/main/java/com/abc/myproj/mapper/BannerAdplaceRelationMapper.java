package com.abc.myproj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.abc.myproj.entity.BannerAdplaceRelation;
import com.baomidou.mybatisplus.mapper.BaseMapper;


/**
 *
 * BannerAdplace 表数据库控制层接口
 *
 */
public interface BannerAdplaceRelationMapper extends BaseMapper<BannerAdplaceRelation> {
  
  public List<BannerAdplaceRelation> getBannerAdplaceID(@Param("sql") String querySql);
  
  public List<BannerAdplaceRelation> getBannerId(@Param("sql") String querySql);
  
}