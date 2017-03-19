package com.abc.myproj.mapper;

import java.util.List;

import com.abc.myproj.entity.Role;
import com.baomidou.mybatisplus.mapper.BaseMapper;


/**
 *
 * Role 表数据库控制层接口
 *
 */
public interface RoleMapper extends BaseMapper<Role> {
  
  public Role getByRoleName(String roleName);
  
  public List<Role> getAllRoleName();
  
}