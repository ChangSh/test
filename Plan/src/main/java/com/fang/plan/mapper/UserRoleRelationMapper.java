package com.fang.plan.mapper;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fang.plan.entity.UserRoleRelation;


/**
 *
 * UserRoleRelation 表数据库控制层接口
 *
 */
public interface UserRoleRelationMapper extends BaseMapper<UserRoleRelation> {
  
  boolean deleteByRoleId(int roleid) ;
  
  boolean deleteByEmail(String email) ;
  
  List<UserRoleRelation> getURByRoleId(int roleid);
  /**
   * 是否存在userRole
   */
  int ifExistUserRole(UserRoleRelation ur);
}