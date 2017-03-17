package com.fang.plan.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fang.plan.entity.CyFunction;
import com.fang.plan.entity.RoleFunctionRelation;
import com.fang.plan.mapper.CyFunctionMapper;
import com.fang.plan.mapper.RoleFunctionRelationMapper;

/**
* @author ：CHANG
*  表数据服务层接口实现类
*
*/
@Service
public class RoleFunctionRelationServiceImpl extends ServiceImpl<RoleFunctionRelationMapper, RoleFunctionRelation> implements IRoleFunctionRelationService {
  @Autowired
  private  RoleFunctionRelationMapper mapper; 
  
  @Override
  public Boolean updateFunc(String roleid, String funcid) {
   String[] splitLimit = funcid.split(",");
   List<RoleFunctionRelation> list = new ArrayList<RoleFunctionRelation>();
   if(splitLimit==null){
     return false;
   }
   if(splitLimit.length>0){
     for(int i=0;i<splitLimit.length;i++){
         RoleFunctionRelation rf= new RoleFunctionRelation();
         rf.setRoleid(roleid);
         rf.setFunctionid(splitLimit[i]);
         list.add(rf);      
     }
     
   }
     EntityWrapper<RoleFunctionRelation> wrapper = new EntityWrapper<RoleFunctionRelation>();
     wrapper.where("  roleid =  {0}",roleid);
    try {
      int a = mapper.delete(wrapper);
      boolean b = this.insertBatch(list);
      if(a>=0&&b)
        return true;
      else
        return false;
    }
    catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return false;
    }

  }
  
}
