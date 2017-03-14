package com.abc.myproj.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.myproj.entity.BannerAdplaceRelation;
import com.abc.myproj.mapper.BannerAdplaceRelationMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

@Service
public class BannerAdplaceRelationServiceImpl extends ServiceImpl<BannerAdplaceRelationMapper, BannerAdplaceRelation> implements IBannerAdplaceRelationService{

  
  @Autowired
  private BannerAdplaceRelationMapper mapper;
  /**
   * 获取每个格子的id
   */
  @Override
  public List<BannerAdplaceRelation> getBannerAdplaceID(String orderId, String AdpId, String date,String allowance) {
    // TODO Auto-generated method stub
    date=date.replaceAll("-", "/");
    Date d=new Date(date);
    int year=d.getYear()+1900;
    int month=d.getMonth()+1;
    String sql="select id from dbo.Plan_Detail_"+year+"_"+month+" where orderId='"+orderId+"' and ADPId='"+AdpId+"' and ReserveDate='"+date+"' and Allowance='"+allowance+"'";
    return mapper.getBannerAdplaceID(sql);
  }
/**
 * 获取可以绑定的创意
 */
  @Override
  public List<BannerAdplaceRelation> getBannerId(String orderId, String AdpId, String date, String allowance) {
    date=date.replaceAll("-", "/");
    Date d=new Date(date);
    int year=d.getYear()+1900;
    int month=d.getMonth()+1;
    String sql="select a.id,BannerId from dbo.Plan_Detail_"+year+"_"+month+" a inner join [dbo].[BannerAdplaceRelation] b on a.id=b.AdplaceId "
    +"where orderId='"+orderId+"' and ADPId='"+AdpId+"' and a.ReserveDate='"+date+"' and Allowance='"+allowance+"'";
    
    return mapper.getBannerId(sql);
  }


 


  
 
  
}
