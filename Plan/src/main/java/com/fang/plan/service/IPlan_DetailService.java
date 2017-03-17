package com.fang.plan.service;


import com.baomidou.mybatisplus.service.IService;
import com.fang.plan.entity.BannerAdplaceRelation;
import com.fang.plan.entity.Plan_Detail;
/**
*
* PlanTb 表数据服务层接口
*
*/
public interface IPlan_DetailService  extends IService<Plan_Detail>{
  public boolean insertBatchs(Plan_Detail entityList,String username);
  
  public boolean updateState(BannerAdplaceRelation entityList);
}