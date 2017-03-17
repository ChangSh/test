package com.fang.plan.service;

import com.baomidou.mybatisplus.service.IService;
import com.fang.plan.entity.RoleFunctionRelation;
/**
* @author ：CHANG
*  表数据服务层接口
*
*/
public interface IRoleFunctionRelationService  extends IService<RoleFunctionRelation>{
  
  
  Boolean updateFunc(String roleid,String limit);
}