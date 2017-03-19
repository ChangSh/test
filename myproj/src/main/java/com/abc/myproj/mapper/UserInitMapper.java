package com.abc.myproj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.abc.myproj.entity.City;
import com.abc.myproj.entity.Resource;
import com.abc.myproj.entity.UserInit;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;


/**
 *
 * UserInit 表数据库控制层接口
 *
 */
public interface UserInitMapper extends BaseMapper<UserInit> {
  
  /**
   * @return 权限列表
   */
  List<Resource> myPermission(@Param("username") String username);
  
  /**
   * @return 资源列表
   */
  List<Resource> mySource(@Param("email") String email);
  
  /**
   * 自定义分页
   * @return
   */
  List<UserInit> userlist(Pagination pag,@Param("user") UserInit user);
  
  
  /**
   * @return 组织架构
   */
  List<City> myCity(@Param("email") String email);
  
  /**
   * 是否存在user
   */
  int ifExistUser(String email);

  /**
   * @return 组织架构
   */
  List<City> allCity();
  

}