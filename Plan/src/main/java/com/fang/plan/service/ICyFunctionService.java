package com.fang.plan.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.fang.plan.entity.CyFunction;
/**
* @author ：CHANG
* CyFunction 表数据服务层接口
*
*/
public interface ICyFunctionService  extends IService<CyFunction>{
  
  
  List<CyFunction> getCyFunctionList(int roleId);
  

}