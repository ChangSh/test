package com.abc.myproj.service;

import java.util.List;

import com.abc.myproj.entity.AdPlace;
import com.abc.myproj.entity.AdPlacePrice;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.baomidou.mybatisplus.service.IService;
/**
*
* AdPlacePrice 表数据服务层接口
*
*/
public interface IAdPlacePriceService  extends IService<AdPlacePrice>{
  
  List<AdPlace> GetAdPlaceByWeb(int id);
  
  public List<AdPlace> selectPlacePriceList(AdPlacePrice entity);
  
  //自定义分页
  List<AdPlacePrice> GetAdPlacePriceByPage(Pagination pag,AdPlacePrice a,String where);
}