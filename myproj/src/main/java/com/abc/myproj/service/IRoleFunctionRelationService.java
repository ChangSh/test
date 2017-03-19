package com.abc.myproj.service;

import com.abc.myproj.entity.RoleFunctionRelation;
import com.baomidou.mybatisplus.service.IService;
/**
* @author ：CHANG
*  表数据服务层接口
*
*/
public interface IRoleFunctionRelationService  extends IService<RoleFunctionRelation>{
  
  
  Boolean updateFunc(String roleid,String limit);
}