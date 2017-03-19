package com.fang.plan.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.baomidou.mybatisplus.service.IService;
import com.fang.plan.entity.AdPlace;
import com.fang.plan.entity.AdPlacePrice;
import com.fang.plan.entity.UserInit;
/**
*
* AdPlacePrice 表数据服务层接口
*
*/
public interface IAdPlacePriceService  extends IService<AdPlacePrice>{
  
  List<AdPlace> GetAdPlaceByWeb(int id,int channelid);
  
  public List<AdPlace> selectPlacePriceList(AdPlacePrice entity);
  
  public List<AdPlace> selectPlacePriceListById(AdPlacePrice entity);
  
  
  //自定义分页
  List<AdPlacePrice> GetAdPlacePriceByPage(Pagination pag,AdPlacePrice a,String where);
}