package com.fang.plan.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fang.core.Common.tools.ReadExcel;
import com.fang.plan.entity.Role;
import com.fang.plan.entity.UserInit;
import com.fang.plan.entity.UserRoleRelation;
import com.fang.plan.mapper.RoleMapper;
import com.fang.plan.mapper.UserInitMapper;
import com.fang.plan.mapper.UserRoleRelationMapper;

/**
*
* Role 表数据服务层接口实现类
*
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

  @Autowired
  private  RoleMapper rolemapper;
  
  @Autowired
  private  UserRoleRelationMapper urmapper;
  
  @Override
  public List<Role> getAllRoleName() {
    return rolemapper.getAllRoleName();
  }

  @Override
  public boolean DeleteUserAndRole(String[] idList) {
    int result=rolemapper.deleteBatchIds(Arrays.asList(idList));
    List urid = Arrays.asList(idList);
    boolean uresult=true;
    for(int i=0;i<urid.size();i++){
      uresult = uresult && urmapper.deleteByRoleId(Integer.parseInt(urid.get(i).toString()));
    }
    return result==1?true && uresult:false;
  }  
  
}
