package com.fang.plan.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fang.plan.entity.AdPlace;
import com.fang.plan.entity.Channelgroup;
import com.fang.plan.entity.City;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fang.plan.entity.*;


/**
 *
 * AdPlace 表数据库控制层接口
 *
 */
public interface AdPlaceMapper extends BaseMapper<AdPlace> {
  
  public List<Website> GetWebSiteList();
  
  
  public Website GetWebSiteInfo(String id);
  
  public List<Channelgroup> GetChannel(int id);
  
  public boolean AddAdPlace(@Param("a")AdPlace a);
  
  public List<City> GetCitys();
  
  public boolean AddChuanYangAdPlaceId(@Param("adplaceid")int adplaceid, @Param("ChuanYangAdPlaceId")int ChuanYangAdPlaceId);
}