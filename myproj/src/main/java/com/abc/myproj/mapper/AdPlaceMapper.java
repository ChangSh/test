package com.abc.myproj.mapper;

import com.abc.myproj.entity.*;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;



/**
 *
 * AdPlace 表数据库控制层接口
 *
 */
public interface AdPlaceMapper extends BaseMapper<AdPlace> {
  
  public List<Website> GetWebSiteList();
  
  
  public Website GetWebSiteInfo(String id);
  
  public List<Channelgroup> GetChannel();
  
  public boolean AddAdPlace(@Param("a")AdPlace a);
  
  public List<City> GetCitys();
}