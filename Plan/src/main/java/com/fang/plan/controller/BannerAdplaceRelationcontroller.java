package com.fang.plan.controller;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
import com.fang.plan.entity.AdPlacePrice;
import com.fang.plan.entity.BannerAdplaceRelation;
import com.fang.plan.entity.OrderInfo;
import com.fang.plan.entity.Plan_Detail;
import com.fang.plan.service.IAdPlacePriceService;
import com.fang.plan.service.IBannerAdplaceRelationService;
import com.fang.plan.service.IPlan_DetailService;

/**
 * BannerAdplace模块
 * 
 * @auther ：
 * 
 * */

@Controller
@RequestMapping("/BannerAdplace")
public class BannerAdplaceRelationcontroller  extends BaseController {
  

  private final IBannerAdplaceRelationService service;

  private final IPlan_DetailService pservice;
  @Autowired
  public BannerAdplaceRelationcontroller(IBannerAdplaceRelationService service,IPlan_DetailService pservice) {
      this.service = service;
      this.pservice=pservice;
  }
  
  
 
  @RequestMapping(value = "/main", method = RequestMethod.GET)  
  public ModelAndView main(OrderInfo info) {
    ModelAndView mav = new ModelAndView("bdp/pages/BannerAdplace/main_CreateBanner");
    mav.addObject("info", info);
    return mav;
  }
  @RequestMapping(value = "/toCheck", method = RequestMethod.GET)  
  public ModelAndView toCheck(OrderInfo info) {
    ModelAndView mav = new ModelAndView("bdp/pages/BannerAdplace/main_CheckBanner");
    mav.addObject("info", info);
    return mav;
  }
  @ResponseBody
  @RequestMapping(value = "/GetAdPlacePriceList", method = {RequestMethod.GET, RequestMethod.POST})
  @SystemControllerLog(DataSource=MultipleDataSource.PLAN)//description 日志描述,tableType 日志类别， DataSource 访问的数据源
  public Object GetAdPlacePriceList(AdPlacePrice entity) {
   return service.selectPlacePriceList(entity);
  }
  @ResponseBody
  @RequestMapping(value = "/addBannerAdplace", method = {RequestMethod.GET, RequestMethod.POST})
  @SystemControllerLog(DataSource=MultipleDataSource.PLAN)//description 日志描述,tableType 日志类别， DataSource 访问的数据源
  public Object AddBannerAdplace(BannerAdplaceRelation entity,HttpServletRequest request) {
    String userName=(String) request.getSession().getAttribute("Plan_UserName");
   try{
     service.deleteByAdplaceid(entity.getE());
     service.bandBannerAdplace(entity.getE(),userName);
     return renderSuccess("添加成功");
   }catch(Exception e){
     return renderError("添加失败");
   }
  }
  @ResponseBody
  @RequestMapping(value = "/deleteBannerAdplace", method = {RequestMethod.GET, RequestMethod.POST})
  @SystemControllerLog(DataSource=MultipleDataSource.PLAN)//description 日志描述,tableType 日志类别， DataSource 访问的数据源
  public Object DeleteBannerAdplace(@RequestBody String[] userid) {  
    return service.deleteBatchIds(Arrays.asList(userid)) ? renderSuccess("删除成功") : renderError("删除失败");
  }
  
  @ResponseBody
  @RequestMapping(value = "/modifyBannerAdplace", method = {RequestMethod.GET, RequestMethod.POST})
  @SystemControllerLog(DataSource=MultipleDataSource.PLAN)//description 日志描述,tableType 日志类别， DataSource 访问的数据源
  public Object modifyBannerAdplace(BannerAdplaceRelation entity) {
    return service.updateById(entity)? renderSuccess("修改成功") : renderError("修改失败");
  }
  
  @ResponseBody
  @RequestMapping(value = "/getBannerAdplaceID", method = {RequestMethod.GET, RequestMethod.POST})
  @SystemControllerLog(DataSource=MultipleDataSource.PLAN)//description 日志描述,tableType 日志类别， DataSource 访问的数据源
  public Object getBannerAdplaceID(String orderId,String adpId,String date,String reserveHour) {
    return service.getBannerAdplaceID(orderId, adpId, date,reserveHour);
  }
  
  @ResponseBody
  @RequestMapping(value = "/getBannerInfo", method = {RequestMethod.GET, RequestMethod.POST})
  @SystemControllerLog(DataSource=MultipleDataSource.PLAN)//description 日志描述,tableType 日志类别， DataSource 访问的数据源
  public Object getBannerInfo(String orderId,String adpId,String date,String reserveHour) {
    return service.getBannerInfo(orderId, adpId, date, reserveHour);
  }
}
