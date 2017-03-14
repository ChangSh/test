package com.abc.myproj.service;

import java.util.List;

import com.abc.myproj.entity.Role;
import com.baomidou.mybatisplus.service.IService;
/**
*
* Role 表数据服务层接口
*
*/
public interface IRoleService  extends IService<Role>{ 
  
  List<Role> getAllRoleName();
  
  boolean DeleteUserAndRole(String[] idList);
}