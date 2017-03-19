package com.abc.myproj.mapper;

import java.util.List;

import com.abc.myproj.entity.UserRoleRelation;
import com.baomidou.mybatisplus.mapper.BaseMapper;


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