package com.fang.plan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fang.plan.entity.AdPlacePrice;
import com.fang.plan.entity.Plan_Detail;


/**
 *
 * PlanTb 表数据库控制层接口
 *
 */
public interface Plan_DetailMapper extends BaseMapper<Plan_Detail> {
  public void deletes(@Param("sql") String sql);
  public boolean insertPlan_Detail(@Param("sql") String insertSql);
  
  public List<AdPlacePrice> selectPrice(@Param("sql") String insertSql);
  
  public List<Plan_Detail> selectCheck(@Param("sql") String insertSql);
  
  public boolean updateState(@Param("sql") String insertSql);
}