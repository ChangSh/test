package com.fang.plan.controller;
import java.util.Arrays;

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
import com.fang.plan.entity.HolidayDiscount;
import com.fang.plan.entity.OrderBody;
import com.fang.plan.service.IHolidayDiscountService;

/**
 * HolidayDiscount模块
 * 
 * @auther ：
 * 
 * */

@Controller
@RequestMapping("/HolidayDiscount")
public class HolidayDiscountcontroller  extends BaseController {
  

  private final IHolidayDiscountService service;
  
  @Autowired
  public HolidayDiscountcontroller(IHolidayDiscountService service) {
      this.service = service;
  }
  
  @RequestMapping(value = "/main", method = RequestMethod.GET)  
  public ModelAndView main() {
    ModelAndView mav = new ModelAndView("bdp/pages/HolidayDiscount/main_HolidayDiscount");
    return mav;
  }
    
  @ResponseBody
  @RequestMapping(value = "/queryHolidayDiscountByPage", method = RequestMethod.GET)  
  @SystemControllerLog(DataSource=MultipleDataSource.PLAN)//description 日志描述,tableType 日志类别， DataSource 访问的数据源
  public  PagedResult<HolidayDiscount> queryHolidayDiscountByPage(HolidayDiscount entity,String sort,int limit,int offset) {  
    PagedResult<HolidayDiscount> pagedResult = new PagedResult<HolidayDiscount>();
    EntityWrapper<HolidayDiscount> wrapper=new  EntityWrapper<HolidayDiscount>();   
   //wrapper.where("email like  {0}", "%"+(entity.getEmail()==null?"":entity.getEmail())+"%");
    Page<HolidayDiscount> page=null;
    if(sort.equals("")||sort==""){
      page=new Page<HolidayDiscount>(offset,limit,"1");
    }
    else{
      page=new Page<HolidayDiscount>(offset,limit,sort);
    }
    page.setOptimizeCount(true);
    page=service.selectPage(page,wrapper);    
    pagedResult.setRows(page.getRecords()); 
    pagedResult.setTotal(page.getTotal());
  
    return pagedResult;
  }
  

  @ResponseBody
  @RequestMapping(value = "/addHolidayDiscount", method = {RequestMethod.GET, RequestMethod.POST})
  @SystemControllerLog(DataSource=MultipleDataSource.PLAN)//description 日志描述,tableType 日志类别， DataSource 访问的数据源
  public Object AddHolidayDiscount(HolidayDiscount entity) {
    return service.insert(entity)? renderSuccess("添加成功") : renderError("添加失败");
  }
  
   
  @ResponseBody
  @RequestMapping(value = "/deleteHolidayDiscount", method = {RequestMethod.GET, RequestMethod.POST})
  @SystemControllerLog(DataSource=MultipleDataSource.PLAN)//description 日志描述,tableType 日志类别， DataSource 访问的数据源
  public Object DeleteHolidayDiscount(@RequestBody String[] userid) {  
    return service.deleteBatchIds(Arrays.asList(userid)) ? renderSuccess("删除成功") : renderError("删除失败");
  }
  
  @ResponseBody
  @RequestMapping(value = "/modifyHolidayDiscount", method = {RequestMethod.GET, RequestMethod.POST})
  @SystemControllerLog(DataSource=MultipleDataSource.PLAN)//description 日志描述,tableType 日志类别， DataSource 访问的数据源
  public Object modifyHolidayDiscount(HolidayDiscount entity) {
    return service.updateById(entity)? renderSuccess("修改成功") : renderError("修改失败");
  }
  
  @ResponseBody
  @RequestMapping(value = "/AllMoney", method = {RequestMethod.GET, RequestMethod.POST})
  @SystemControllerLog(DataSource=MultipleDataSource.PLAN)//description 日志描述,tableType 日志类别， DataSource 访问的数据源
  public Object AllMoney(int WebSiteID,OrderBody body) {
    return service.TotalMoney(WebSiteID,body);
  }

}
