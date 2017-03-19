package com.fang.plan.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.service.IService;
import com.fang.plan.entity.Role;
/**
*
* Role 表数据服务层接口
*
*/
public interface IRoleService  extends IService<Role>{ 
  
  List<Role> getAllRoleName();
  
  boolean DeleteUserAndRole(String[] idList);
}