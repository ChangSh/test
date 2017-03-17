package com.fang.plan.service;

import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fang.core.Common.tools.DateUtil;
import com.fang.plan.entity.AdPlace;
import com.fang.plan.entity.AdPlacePrice;
import com.fang.plan.mapper.AdPlacePriceMapper;

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
  public List<AdPlace> GetAdPlaceByWeb(int id,int channelid) {
    // TODO Auto-generated method stub
    return AdPlacePriceMapper.GetAdPlaceByWeb(id);
  }
  
  @Override
  public List<AdPlacePrice> GetAdPlacePriceByPage(Pagination pag, AdPlacePrice a,String where) {
    // TODO Auto-generated method stub
    return AdPlacePriceMapper.GetAdPlacePriceByPage(pag, a,where);
  }

  @Override
  public List<AdPlace> selectPlacePriceListById(AdPlacePrice entity) {
    DateTime start=new DateTime (entity.getStarttime().replaceAll("/", "-"));
    DateTime end=new DateTime (entity.getEndtime().replaceAll("/", "-"));
    int re= ((end.getYear()-start.getYear())*12)+(end.getMonthOfYear()-start.getMonthOfYear());
    StringBuffer sb=new StringBuffer();
    StringBuffer sbw=new StringBuffer();
    int y=start.getYear();
    int m=start.getMonthOfYear();
    for(int i=0;i<=re;i++){
      if(i>0){
        sb.append(" union all ");
        sbw.append(" union all ");
      }
      if(m>12){
        m=1;
        y++;
      }
      sb.append(" SELECT   State,ReserveHour,[Allowance],KJ_ADP_ID,year(ReserveDate) AS years,month(ReserveDate) AS months,day(ReserveDate) AS d")
          .append(" FROM Plan_Detail_"+y+"_"+m+" WHERE OrderId='"+entity.getOrderid()+"'  ");
      sbw.append("  SELECT distinct KJ_ADP_ID FROM Plan_Detail_"+y+"_"+m+" a1 WHERE OrderId='"+entity.getOrderid()+"'  ");

      m++;
    }
    return AdPlacePriceMapper.selectPlacePriceListById(entity,sb.toString(),sbw.toString());
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
      sb.append("     SELECT  distinct  (SELECT distinct CAST (State AS VARCHAR)+','   FROM Plan_Detail_"+y+"_"+m+" d where  r.ReserveDate=d.ReserveDate AND  r.ReserveHour=d.ReserveHour")
      .append("       AND KJ_ADP_ID=r.KJ_ADP_ID and OrderId!='"+entity.getOrderid()+"' FOR XML PATH('')) State,")
      .append("       (SELECT distinct State  FROM Plan_Detail_"+y+"_"+m+" d where  r.ReserveDate=d.ReserveDate AND  r.ReserveHour=d.ReserveHour")
      .append("       AND KJ_ADP_ID=r.KJ_ADP_ID and OrderId='"+entity.getOrderid()+"' ) mState,")
      .append("          ReserveHour,[Allowance],KJ_ADP_ID,year(ReserveDate) AS years,month(ReserveDate) AS months,day(ReserveDate) AS d")
      .append("     FROM")
      .append("    (SELECT distinct State, ReserveHour,[Allowance],KJ_ADP_ID,ReserveDate")
      .append("      FROM Plan_Detail_"+y+"_"+m+" r")
      .append("    ) r  ");
      m++;
    }
    
    return AdPlacePriceMapper.selectPlacePriceList(entity,sb.toString());
  }
}
