package com.fang.plan.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.baomidou.mybatisplus.service.IService;
import com.fang.plan.entity.City;
import com.fang.plan.entity.Resource;
import com.fang.plan.entity.UserInit;
/**
*
* User 表数据服务层接口
*
*/
public interface IUserInitService  extends IService<UserInit>{
  
  /**
   * @return 我的权限列表
   */
  public List<Resource> myPermission(String username);
  
  /**
   * @return 资源列表
   */
  List<Resource> mySource(String username);
  
  /**
   * 自定义分页
   * @return
   */
  List<UserInit> userlist(Pagination pag,UserInit user);
  
  /**
   * @return 组织架构
   */
  List<City> myCity(@Param("email") String email);
  
  /**
   * @return 组织架构
   */
  List<City> allCity();
  /**
   * 
   */
  boolean insertUserAndRole(UserInit user);
  
  boolean updateUserAndRole(UserInit user);
  
  boolean deleteUserAndRole(String[] idList);
  
  boolean loadExcelFile( MultipartFile file); 
}
