package com.fang.plan.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fang.plan.entity.AdPlace;
import com.fang.plan.entity.AdPlacePrice;
import com.fang.plan.entity.Banner;
import com.fang.plan.entity.BannerAdplaceRelation;
import com.fang.plan.entity.Plan_Detail;
import com.fang.plan.mapper.AdPlacePriceMapper;
import com.fang.plan.mapper.BannerAdplaceRelationMapper;
import com.fang.plan.mapper.Plan_DetailMapper;

@Service
public class BannerAdplaceRelationServiceImpl extends ServiceImpl<BannerAdplaceRelationMapper, BannerAdplaceRelation> implements IBannerAdplaceRelationService{

  
  @Autowired
  private BannerAdplaceRelationMapper mapper;
  
  @Autowired
  private  Plan_DetailMapper planmapper;
  
  @Autowired
  private AdPlacePriceMapper AdPlacePriceMapper;
  /**
   * 获取每个格子的id
   */
  @Override
  public List<Plan_Detail> getBannerAdplaceID(String orderId, String AdpId, String date,String reserveHour) {
    // TODO Auto-generated method stub
    date=date.replaceAll("-", "/");
    Date d=new Date(date);
    int year=d.getYear()+1900;
    int month=d.getMonth()+1;
    String sql="select id,allowance from dbo.Plan_Detail_"+year+"_"+month+" where orderId='"+orderId+"' and ADPId='"+AdpId+"' and ReserveDate='"+date+"' and ReserveHour='"+reserveHour+"'";
    return mapper.getBannerAdplaceID(sql);
  }
/**
 * 获取可以绑定的创意
 */
  @Override
  public List<Banner> getBannerInfo(String orderId, String AdpId, String date, String reserveHour) {
    date=date.replaceAll("-", "/");
    Date d=new Date(date);
    int year=d.getYear()+1900;
    int month=d.getMonth()+1;
    String sql="select c.* from dbo.Plan_Detail_"+year+"_"+month+" a inner join [dbo].[BannerAdplaceRelation] b on a.id=b.AdplaceId inner join dbo.banner c on b.bannerid=c.id "
    +"where a.orderId='"+orderId+"' and ADPId='"+AdpId+"' and a.ReserveDate='"+date+"' and ReserveHour='"+reserveHour+"'";
    
    return mapper.getBannerInfo(sql);
  }
@Override
public int deleteByAdplaceid(List<BannerAdplaceRelation> entityList) {
  try{
    for(int i=0;i<entityList.size();i++){
      BannerAdplaceRelation bar=entityList.get(i);
      if(bar.getState().equals("0")){//取消上传素材
        int adplaceid=bar.getAdplaceid();
        mapper.deleteByAdplaceid(adplaceid);
        
        String date=bar.getReservedate();
        date=date.replaceAll("-", "/");
        Date d=new Date(date);
        int year=d.getYear()+1900;
        int month=d.getMonth()+1;
        String sql="update dbo.Plan_Detail_"+year+"_"+month+" set State=2 where id='"+bar.getAdplaceid()+"'"; 
        planmapper.updateState(sql);
      }else if(bar.getState().equals("2")){//取消确认素材
        String date=bar.getReservedate();
        date=date.replaceAll("-", "/");
        Date d=new Date(date);
        int year=d.getYear()+1900;
        int month=d.getMonth()+1;
        String sql="update dbo.Plan_Detail_"+year+"_"+month+" set State=3 where id='"+bar.getAdplaceid()+"'"; 
        planmapper.updateState(sql);
      }
      
    }
    return 1;
  }catch(Exception e){
    return 0;
  }
}
@Override
public int bandBannerAdplace(List<BannerAdplaceRelation> entityList,String userName) {
  try{
    for(int i=0;i<entityList.size();i++){
      BannerAdplaceRelation bar=entityList.get(i);
      if(bar.getState().equals("1")){//上传素材
        mapper.insert(entityList.get(i));//往关系表中插入数据
        String date=bar.getReservedate();
        date=date.replaceAll("-", "/");
        Date d=new Date(date);
        int year=d.getYear()+1900;
        int month=d.getMonth()+1;
        String sql="update dbo.Plan_Detail_"+year+"_"+month+" set State=3 where id='"+bar.getAdplaceid()+"'"; 
        planmapper.updateState(sql);//改变detail表中的状态
      }else if(bar.getState().equals("3")){//确认创意
        Date now=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        entityList.get(i).setCheckoperator(userName);
        entityList.get(i).setChecktime(sdf.format(now));
        mapper.updateById(entityList.get(i));
        
        String date=bar.getReservedate();
        date=date.replaceAll("-", "/");
        Date d=new Date(date);
        int year=d.getYear()+1900;
        int month=d.getMonth()+1;
        String sql="update dbo.Plan_Detail_"+year+"_"+month+" set State=4 where id='"+bar.getAdplaceid()+"'"; 
        planmapper.updateState(sql);//改变detail表中的状态
      }
    }
    return 1;
  }catch(Exception e){
    return 0;
  }
  
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
  return mapper.selectPlacePriceList(entity, sb.toString());
}

 


  
 
  
}
