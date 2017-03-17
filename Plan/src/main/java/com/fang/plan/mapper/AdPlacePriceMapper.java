package com.fang.plan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.fang.plan.entity.AdPlace;
import com.fang.plan.entity.AdPlacePrice;


/**
 *
 * AdPlacePrice 表数据库控制层接口
 *
 */
public interface AdPlacePriceMapper extends BaseMapper<AdPlacePrice> {
  
  public List<AdPlace> selectPlacePriceList(@Param("entity") AdPlacePrice entity,@Param("sql") String sql);
  
  public List<AdPlace> selectPlacePriceListById(@Param("entity") AdPlacePrice entity,@Param("sql") String sql,@Param("sqlwhere") String sqlw);
  
  public List<AdPlace> GetAdPlaceByWeb(int id);
  
  public List<AdPlacePrice> GetAdPlacePriceByPage(Pagination pag,@Param("a") AdPlacePrice a,@Param("where")String where);
}