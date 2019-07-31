package com.abc.myproj.service;


import com.abc.myproj.entity.BannerAdplaceRelation;
import com.abc.myproj.entity.Plan_Detail;
import com.baomidou.mybatisplus.service.IService;
/**
*
* PlanTb 表数据服务层接口
*
*/
public interface IPlan_DetailService  extends IService<Plan_Detail>{
  public boolean insertBatchs(Plan_Detail entityList,String username);
  
  public boolean updateState(BannerAdplaceRelation entityList);
}