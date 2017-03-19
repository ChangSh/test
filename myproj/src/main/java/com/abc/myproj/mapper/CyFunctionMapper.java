package com.abc.myproj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.abc.myproj.entity.CyFunction;
import com.baomidou.mybatisplus.mapper.BaseMapper;


/**
 * @author ：CHANG
 * CyFunction 表数据库控制层接口
 *
 */
public interface CyFunctionMapper extends BaseMapper<CyFunction> {
  
  List<CyFunction> getCyFunctionList(@Param("roleId") int roleId);  
  
}