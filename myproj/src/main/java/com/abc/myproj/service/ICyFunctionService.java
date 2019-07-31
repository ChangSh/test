package com.abc.myproj.service;

import java.util.List;

import com.abc.myproj.entity.CyFunction;
import com.baomidou.mybatisplus.service.IService;
/**
* @author ：CHANG
* CyFunction 表数据服务层接口
*
*/
public interface ICyFunctionService  extends IService<CyFunction>{
  
  
  List<CyFunction> getCyFunctionList(int roleId);
  

}