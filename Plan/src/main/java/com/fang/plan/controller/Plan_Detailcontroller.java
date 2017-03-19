package com.fang.plan.controller;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fang.common.controller.BaseController;
import com.fang.core.Common.annotation.SystemControllerLog;
import com.fang.core.Common.tools.MultipleDataSource;
import com.fang.core.Common.tools.PagedResult;
import com.fang.plan.entity.OrderInfo;
import com.fang.plan.entity.Plan_Detail;
import com.fang.plan.service.IPlan_DetailService;

/**
 * PlanTb模块
 * 
 * @auther ：
 * 
 * */

@Controller
@RequestMapping("/PlanTb")
public class Plan_Detailcontroller  extends BaseController {
  

  private final IPlan_DetailService service;
  
  @Autowired
  public Plan_Detailcontroller(IPlan_DetailService service) {
      this.service = service;
  }
  
  @RequestMapping(value = "/main", method = RequestMethod.GET)  
  public ModelAndView main(OrderInfo info) {
    ModelAndView mav = new ModelAndView("bdp/pages/PlanTb/main_PlanTb");
    mav.addObject("info", info);
    return mav;
  }
  @ResponseBody
  @RequestMapping(value = "/create", method = RequestMethod.GET)  
  public ModelAndView create(OrderInfo info) {
    ModelAndView mav = new ModelAndView("bdp/pages/PlanTb/main_PlanCreate");
    mav.addObject("info", info);
    return mav;
  }
  @ResponseBody
  @RequestMapping(value = "/look", method = RequestMethod.GET)  
  public ModelAndView look(OrderInfo info) {
    ModelAndView mav = new ModelAndView("bdp/pages/PlanTb/main_PlanLook");
    mav.addObject("info", info);
    return mav;
  }
    
  @ResponseBody
  @RequestMapping(value = "/queryPlanTbByPage", method = RequestMethod.GET)  
  @SystemControllerLog(DataSource=MultipleDataSource.PLAN)//description 日志描述,tableType 日志类别， DataSource 访问的数据源
  public  PagedResult<Plan_Detail> queryDepUserByPage(Plan_Detail entity,String sort,int limit,int offset) {  
    PagedResult<Plan_Detail> pagedResult = new PagedResult<Plan_Detail>();
    EntityWrapper<Plan_Detail> wrapper=new  EntityWrapper<Plan_Detail>();   
    Page<Plan_Detail> page=null;
    if(sort.equals("")||sort==""){
      page=new Page<Plan_Detail>(offset,limit,"1");
    }
    else{
      page=new Page<Plan_Detail>(offset,limit,sort);
    }
    page.setOptimizeCount(true);
    page=service.selectPage(page,wrapper);    
    pagedResult.setRows(page.getRecords()); 
    pagedResult.setTotal(page.getTotal());
  
    return pagedResult;
  }
  

  @ResponseBody
  @RequestMapping(value = "/addPlanTb", method = {RequestMethod.GET, RequestMethod.POST})
  @SystemControllerLog(DataSource=MultipleDataSource.PLAN)//description 日志描述,tableType 日志类别， DataSource 访问的数据源
  public Object AddPlanTb(Plan_Detail entity,HttpServletRequest request) {
    String userName=(String) request.getSession().getAttribute("Plan_UserName");
    return service.insertBatchs(entity,userName)? renderSuccess("添加成功") : renderError("添加失败");
  }
  
   
  @ResponseBody
  @RequestMapping(value = "/deletePlanTb", method = {RequestMethod.GET, RequestMethod.POST})
  @SystemControllerLog(DataSource=MultipleDataSource.PLAN)//description 日志描述,tableType 日志类别， DataSource 访问的数据源
  public Object DeletePlanTb(@RequestBody String[] userid) {  
    return service.deleteBatchIds(Arrays.asList(userid)) ? renderSuccess("删除成功") : renderError("删除失败");
  }
  
  @ResponseBody
  @RequestMapping(value = "/modifyPlanTb", method = {RequestMethod.GET, RequestMethod.POST})
  @SystemControllerLog(DataSource=MultipleDataSource.PLAN)//description 日志描述,tableType 日志类别， DataSource 访问的数据源
  public Object modifyPlanTb(Plan_Detail entity) {
    return service.updateById(entity)? renderSuccess("修改成功") : renderError("修改失败");
  }

}
