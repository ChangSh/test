package com.abc.myproj.service;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.myproj.entity.AdPlace;
import com.abc.myproj.entity.AdPlacePrice;
import com.abc.myproj.mapper.AdPlacePriceMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
*
* AdPlacePrice 表数据服务层接口实现类
*
*/
@Service
public class AdPlacePriceServiceImpl extends ServiceImpl<AdPlacePriceMapper, AdPlacePrice> implements IAdPlacePriceService {
  @Autowired
  private AdPlacePriceMapper AdPlacePriceMapper;
  @Override
  public List<AdPlace> GetAdPlaceByWeb(int id) {
    // TODO Auto-generated method stub
    return AdPlacePriceMapper.GetAdPlaceByWeb(id);
  }
  
  @Override
  public List<AdPlacePrice> GetAdPlacePriceByPage(Pagination pag, AdPlacePrice a,String where) {
    // TODO Auto-generated method stub
    return AdPlacePriceMapper.GetAdPlacePriceByPage(pag, a,where);
  }

  @Override
  public List<AdPlace> selectPlacePriceList(AdPlacePrice entity) {
    DateTime start=new DateTime (entity.getStarttime().replaceAll("/", "-"));
    DateTime end=new DateTime (entity.getEndtime().replaceAll("/", "-"));
    int re= ((end.getYear()-start.getYear())*12)+(end.getMonthOfYear()-start.getMonthOfYear());
    StringBuffer sb=new StringBuffer();
    int y=start.getYear();
    int m=start.getMonthOfYear();
    for(int i=0;i<=re;i++){
      if(i>0){
        sb.append(" union all ");
      }
      if(m>12){
        m=1;
        y++;
      }
      sb.append(" select cast ([State] as int) [State],ReserveHour,[Allowance],[ADPId],year(ReserveDate) AS years,month(ReserveDate) AS months,day(ReserveDate) AS d")
      .append(" FROM Plan_Detail_"+y+"_"+m+" where OrderId='BJ2015010155445' ");
      m++;
    }
    return AdPlacePriceMapper.selectPlacePriceList(entity,sb.toString());
  }
  
}
