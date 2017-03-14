package com.abc.myproj.controller;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.abc.common.controller.BaseController;
import com.abc.core.Common.annotation.SystemControllerLog;
import com.abc.core.Common.tools.MultipleDataSource;
import com.abc.myproj.entity.BannerAdplaceRelation;
import com.abc.myproj.entity.OrderInfo;
import com.abc.myproj.service.IBannerAdplaceRelationService;
import com.abc.myproj.service.IPlan_DetailService;

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
  @ResponseBody
  @RequestMapping(value = "/addBannerAdplace", method = {RequestMethod.GET, RequestMethod.POST})
  @SystemControllerLog(DataSource=MultipleDataSource.PLAN)//description 日志描述,tableType 日志类别， DataSource 访问的数据源
  public Object AddBannerAdplace(BannerAdplaceRelation entity) {
   /* for(int i=0;i<entity.getE().size();i++){
      int id=entity.getE().get(i).getId();
      service.deleteById(id);
    }*/
   try{
     service.deleteBatchIds(entity.getE());
     service.insertBatch(entity.getE());
     pservice.updateState(entity);
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
  public Object getBannerAdplaceID(String orderId,String adpId,String date,String allowance) {
    return service.getBannerAdplaceID(orderId, adpId, date,allowance);
  }
  
  @ResponseBody
  @RequestMapping(value = "/getBannerId", method = {RequestMethod.GET, RequestMethod.POST})
  @SystemControllerLog(DataSource=MultipleDataSource.PLAN)//description 日志描述,tableType 日志类别， DataSource 访问的数据源
  public Object getBannerId(String orderId,String adpId,String date,String allowance) {
    return service.getBannerId(orderId, adpId, date, allowance);
  }
}
