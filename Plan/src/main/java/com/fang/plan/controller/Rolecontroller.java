package com.fang.plan.controller;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fang.common.controller.BaseController;
import com.fang.core.Common.annotation.SystemControllerLog;
import com.fang.core.Common.tools.MultipleDataSource;
import com.fang.core.Common.tools.PagedResult;
import com.fang.plan.entity.Role;
import com.fang.plan.service.IRoleService;

/**
 * Role模块
 * 
 * @auther ：
 * 
 * */

@Controller
@RequestMapping("/Role")
public class Rolecontroller  extends BaseController {
  

  private final IRoleService service;
  
  @Autowired
  public Rolecontroller(IRoleService service) {
      this.service = service;
  }
  
  @RequestMapping(value = "/main", method = RequestMethod.GET)  
  public ModelAndView main() {
    ModelAndView mav = new ModelAndView("bdp/pages/Role/main_Role");
    return mav;
  }   
  @ResponseBody
  @RequestMapping(value = "/queryRoleByPage", method = RequestMethod.GET)  
  @SystemControllerLog(DataSource=MultipleDataSource.PLAN)//description 日志描述,tableType 日志类别， DataSource 访问的数据源，默认工作台的库
  public  PagedResult<Role> queryDepUserByPage(Role entity,String sort,int limit,int offset) {  
    PagedResult<Role> pagedResult = new PagedResult<Role>();
    EntityWrapper<Role> wrapper=new  EntityWrapper<Role>();   
    wrapper.where("rolename like  {0}", "%"+(entity.getRolename()==null?"":entity.getRolename())+"%");
    Page<Role> page=null;
    if(sort.equals("")||sort==""){
      page=new Page<Role>(offset,limit,"1");
    }
    else{
      page=new Page<Role>(offset,limit,sort);
    }
    page.setOptimizeCount(true);
    page=service.selectPage(page,wrapper);    
    pagedResult.setRows(page.getRecords()); 
    pagedResult.setTotal(page.getTotal());
  
    return pagedResult;
  }
  

  @ResponseBody
  @RequestMapping(value = "/addRole", method = {RequestMethod.GET, RequestMethod.POST})
  @SystemControllerLog(DataSource=MultipleDataSource.PLAN)//description 日志描述,tableType 日志类别， DataSource 访问的数据源，默认工作台的库
  public Object AddDepUser(Role entity) {
    
    return service.insert(entity)? renderSuccess("添加成功") : renderError("添加失败");
  }
  
   
  @ResponseBody
  @RequestMapping(value = "/deleteRole", method = {RequestMethod.GET, RequestMethod.POST})
  @SystemControllerLog(DataSource=MultipleDataSource.PLAN)//description 日志描述,tableType 日志类别， DataSource 访问的数据源，默认工作台的库
  public Object DeleteUser(@RequestBody String[] userid) {  
    return service.DeleteUserAndRole(userid) ? renderSuccess("删除成功") : renderError("删除失败");
  }
  
  @ResponseBody
  @RequestMapping(value = "/modifyRole", method = {RequestMethod.GET, RequestMethod.POST})
  @SystemControllerLog(DataSource=MultipleDataSource.PLAN)//description 日志描述,tableType 日志类别， DataSource 访问的数据源，默认工作台的库
  public Object modifyDepUser(Role entity) {
    return service.updateById(entity)? renderSuccess("修改成功") : renderError("修改失败");
  }
  
  @ResponseBody
  @RequestMapping(value = "/getAllRoleName", method = {RequestMethod.GET, RequestMethod.POST})
  @SystemControllerLog(DataSource=MultipleDataSource.PLAN)//description 日志描述,tableType 日志类别， DataSource 访问的数据源，默认工作台的库
  public List<Role> getAllRoleName() {
    return service.getAllRoleName();
  }
}
