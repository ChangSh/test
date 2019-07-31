package com.abc.myproj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.abc.myproj.entity.AdPlace;
import com.abc.myproj.entity.AdPlacePrice;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;


/**
 *
 * AdPlacePrice 表数据库控制层接口
 *
 */
public interface AdPlacePriceMapper extends BaseMapper<AdPlacePrice> {
  
  public List<AdPlace> selectPlacePriceList(@Param("entity") AdPlacePrice entity,@Param("sql") String sql);
  
  public List<AdPlace> GetAdPlaceByWeb(int id);
  
  public List<AdPlacePrice> GetAdPlacePriceByPage(Pagination pag,@Param("a") AdPlacePrice a,@Param("where")String where);
}