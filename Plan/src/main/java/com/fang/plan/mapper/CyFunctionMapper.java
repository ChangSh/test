package com.fang.plan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fang.plan.entity.CyFunction;


/**
 * @author ：CHANG
 * CyFunction 表数据库控制层接口
 *
 */
public interface CyFunctionMapper extends BaseMapper<CyFunction> {
  
  List<CyFunction> getCyFunctionList(@Param("roleId") int roleId);  
  
}