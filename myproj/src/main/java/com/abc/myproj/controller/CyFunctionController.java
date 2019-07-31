package com.abc.myproj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.abc.common.controller.BaseController;
import com.abc.core.Common.annotation.SystemControllerLog;
import com.abc.core.Common.tools.MultipleDataSource;
import com.abc.myproj.service.ICyFunctionService;
import com.abc.myproj.service.IRoleFunctionRelationService;
import com.abc.myproj.service.IRoleService;

/**
 * CyFunction模块
 * 
 * @author ：CHANG
 * 
 * */

@Controller
@RequestMapping("/CyFunction")
public class CyFunctionController extends BaseController {
  
  private final ICyFunctionService service;
  
  
  private final IRoleFunctionRelationService roleFunc_service;
  
  @Autowired
  public CyFunctionController(ICyFunctionService service,IRoleService role_service,IRoleFunctionRelationService roleFunc_service) {
    this.service = service;
    this.roleFunc_service = roleFunc_service;
  }
  
  
  
  @RequestMapping(value = "/main", method = RequestMethod.GET)
  public ModelAndView main() {
    ModelAndView mav = new ModelAndView("bdp/pages/CyFunction/main_CyFunction");
    return mav;
  }
  

  
  @ResponseBody
  @RequestMapping(value = "/modifyCyFunction", method = {RequestMethod.GET, RequestMethod.POST})
  @SystemControllerLog(DataSource = MultipleDataSource.PLAN)
  // description 日志描述,tableType 日志类别， DataSource 访问的数据源，默认工作台的库
  public Object modifyCyFunction(String roleid, String funcid) {
    
   return roleFunc_service.updateFunc(roleid, funcid) ? renderSuccess("修改成功") : renderError("修改失败");
    
  }
  
  
  @ResponseBody
  @RequestMapping(value = "/initData", method = {RequestMethod.GET, RequestMethod.POST})
  @SystemControllerLog(DataSource = MultipleDataSource.PLAN)
  // description 日志描述,tableType 日志类别， DataSource 访问的数据源，默认工作台的库
  public Object initData(int roleid) {
    return service.getCyFunctionList(roleid);
  }
  

  
}
