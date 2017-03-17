package com.fang.plan.mapper;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fang.plan.entity.Role;


/**
 *
 * Role 表数据库控制层接口
 *
 */
public interface RoleMapper extends BaseMapper<Role> {
  
  public Role getByRoleName(String roleName);
  
  public List<Role> getAllRoleName();
  
}