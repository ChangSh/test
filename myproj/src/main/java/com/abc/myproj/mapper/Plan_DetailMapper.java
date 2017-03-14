package com.abc.myproj.mapper;

import org.apache.ibatis.annotations.Param;

import com.abc.myproj.entity.Plan_Detail;
import com.baomidou.mybatisplus.mapper.BaseMapper;


/**
 *
 * PlanTb 表数据库控制层接口
 *
 */
public interface Plan_DetailMapper extends BaseMapper<Plan_Detail> {
  public void deletes(@Param("sql") String sql);
  public boolean insertPlan_Detail(@Param("sql") String insertSql);
  
  public boolean updateState(@Param("sql") String insertSql);
}