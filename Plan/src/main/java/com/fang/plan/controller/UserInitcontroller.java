package com.fang.plan.controller;
import java.util.List;
import java.util.UUID;

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

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.fang.common.controller.BaseController;
import com.fang.core.Common.annotation.SystemControllerLog;
import com.fang.core.Common.tools.EhcacheUtil;
import com.fang.core.Common.tools.MultipleDataSource;
import com.fang.core.Common.tools.PagedResult;
import com.fang.core.Common.tools.SoapHelper;
import com.fang.plan.entity.City;
import com.fang.plan.entity.UserInit;
import com.fang.plan.service.IUserInitService;

/**
 * UserInit模块
 * 
 * @author ：陈军
 * 
 * */

@Controller
@RequestMapping("/UserInit")
public class UserInitcontroller  extends BaseController {
  

  private final IUserInitService service;

  @Autowired
  public UserInitcontroller(IUserInitService userService) {
      this.service = userService;
  }
  @RequestMapping(value = "/main", method = RequestMethod.GET)  
  public ModelAndView main() {
    ModelAndView mav = new ModelAndView("bdp/pages/depUser/main_depUser");
    return mav;
  }
    
  @ResponseBody
  @RequestMapping(value = "/queryDepUserByPage", method = RequestMethod.GET)  
  public  PagedResult<UserInit> queryDepUserByPage(UserInit entity,String sort,int limit,int offset) { 
    //自定义分页查询
    Pagination pag= new Pagination(offset,limit);
    if(sort.equals("")||sort==""){
      pag.setOrderByField("1"); 
    }
    else{
      pag.setOrderByField("uuid"); 
    }
    //单表分页查询
    PagedResult<UserInit> pagedResult = new PagedResult<UserInit>();

    pagedResult.setRows(service.userlist(pag,entity)); 
    pagedResult.setTotal(pag.getTotal());
   
    return pagedResult;
  }
  

  @ResponseBody
  @RequestMapping(value = "/addDepUser", method = {RequestMethod.GET, RequestMethod.POST})
  @SystemControllerLog(description="添加用户")
  public Object AddDepUser(UserInit entity) {
    entity.setUuid(UUID.randomUUID().toString());
    return service.insertUserAndRole(entity)? renderSuccess("添加成功") : renderError("添加失败");
  }
  
   
  @ResponseBody
  @RequestMapping(value = "/deleteDepUser", method = {RequestMethod.GET, RequestMethod.POST})
  @SystemControllerLog(description="删除用户")
  public Object DeleteUser(@RequestBody String[] userid) {  
    return service.deleteUserAndRole(userid) ? renderSuccess("删除成功") : renderError("删除失败");
  }
  
  @ResponseBody
  @RequestMapping(value = "/modifyDepUser", method = {RequestMethod.GET, RequestMethod.POST})
  @SystemControllerLog(description="修改用户")
  public Object modifyDepUser(UserInit entity) {
    return service.updateUserAndRole(entity)? renderSuccess("修改成功") : renderError("修改失败");
  }
  
  @ResponseBody
  @RequestMapping(value = "/queryAllCity", method = RequestMethod.POST)
  public Object queryAllCity(UserInit userInit) {
    // 缓存 plancache 在cache.xml 配置中
    EhcacheUtil cache = EhcacheUtil.getInstance();
    List<City> cityList = (List<City>) cache.get("plancache", "AllCity");//获取缓存中数据
    if (cityList == null || cityList.equals(null)) {
      cityList = service.allCity();
      cache.put("plancache", "AllCity", cityList);
    }
    return cityList;
  }
  @RequestMapping(value="/upload",method = RequestMethod.POST)  
  @ResponseBody
  @SystemControllerLog(DataSource=MultipleDataSource.PLAN)//description 日志描述,tableType 日志类别， DataSource 访问的数据源，默认工作台的库
  public Object  upload(@RequestParam(value="file",required = false)MultipartFile file,HttpServletRequest request, HttpServletResponse response){  
    return service.loadExcelFile(file)? renderSuccess("添加成功") : renderError("添加失败");  
  } 
  
  @ResponseBody
  @RequestMapping(value = "/addForm", method = {RequestMethod.GET, RequestMethod.POST})
  @SystemControllerLog(DataSource=MultipleDataSource.PLAN)
  public Object addForm(String email) {
    try {
      if(SoapHelper.getUserInfo(email)!=null){
        return SoapHelper.getUserInfo(email);
      }
    }
    catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return renderError("找不到该用户");
   
  }
}
