package com.fang.plan.controller;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fang.common.controller.BaseController;
import com.fang.core.Common.annotation.SystemControllerLog;
import com.fang.core.Common.tools.MultipleDataSource;
import com.fang.core.Common.tools.PagedResult;
import com.fang.core.Common.tools.SoapHelper;
import com.fang.plan.entity.AdPlace;
import com.fang.plan.entity.AdPlacePrice;
import com.fang.plan.service.IAdPlacePriceService;

/**
 * AdPlacePrice模块
 * 
 * @auther ：
 * 
 * */

@Controller
@RequestMapping("/AdPlacePrice")
public class AdPlacePricecontroller  extends BaseController {
  

  private final IAdPlacePriceService service;
  
  @Autowired
  public AdPlacePricecontroller(IAdPlacePriceService service) {
      this.service = service;
  }
  
  @RequestMapping(value = "/main", method = RequestMethod.GET)  
  public ModelAndView main() {
    ModelAndView mav = new ModelAndView("bdp/pages/AdPlacePrice/main_AdPlacePrice");
    return mav;
  }
    
  @ResponseBody
  @RequestMapping(value = "/queryAdPlacePriceByPage", method = RequestMethod.GET)  
  @SystemControllerLog(DataSource=MultipleDataSource.PLAN)//description 日志描述,tableType 日志类别， DataSource 访问的数据源
  public  PagedResult<AdPlacePrice> queryDepUserByPage(AdPlacePrice entity,String sort,int limit,int offset) {  
    PagedResult<AdPlacePrice> pagedResult = new PagedResult<AdPlacePrice>();
    EntityWrapper<AdPlacePrice> wrapper=new  EntityWrapper<AdPlacePrice>();   
    String where="where 1=1";
    if(entity.getCompanygroup()!=null&&!entity.getCompanygroup().equals("")){where+=" and a.Companygroup='"+entity.getCompanygroup()+"'";}
    if(entity.getPlatform()!=null&&!entity.getPlatform().equals("")){where+=" and a.Platform='"+entity.getPlatform()+"'";}
    if(entity.getWebsiteid()!=null&&!entity.getWebsiteid().equals("")){where+=" and a.Websiteid="+entity.getWebsiteid();}
    if(entity.getChannelid()!=null&&!entity.getChannelid().equals("")){where+=" and a.Channelid="+entity.getChannelid();}
    if(entity.getAdplaceid()!=null&&!entity.getAdplaceid().equals("")){where+=" and a.Adplaceid="+entity.getAdplaceid();}
    if(entity.getOriginality()!=null&&!entity.getOriginality().equals("")){where+=" and a.Originality='"+entity.getOriginality()+"'";}
    if(entity.getPricestart()!=0 &&entity.getPriceend()!=0){where+=" and a.Price>="+entity.getPricestart()+" and a.price <="+entity.getPriceend();}
    if(entity.getPricestart()!=0 &&entity.getPriceend()==0){where+=" and a.Price>="+entity.getPricestart();}
    if(entity.getPricestart()==0 &&entity.getPriceend()!=0){where+=" and a.Price<="+entity.getPriceend();}
     wrapper.where(where);
    Page<AdPlacePrice> page=null;
    if(sort.equals("")||sort==""){
      page=new Page<AdPlacePrice>(offset,limit,"1");
    }
    else{
      page=new Page<AdPlacePrice>(offset,limit,sort);
    }
    page.setOptimizeCount(true);
    //page=;    
    pagedResult.setRows(service.GetAdPlacePriceByPage(page,entity,where)); 
    pagedResult.setTotal(page.getTotal());
    
    return pagedResult;
  }
  

  @ResponseBody
  @RequestMapping(value = "/addAdPlacePrice", method = {RequestMethod.GET, RequestMethod.POST})
  @SystemControllerLog(DataSource=MultipleDataSource.PLAN)//description 日志描述,tableType 日志类别， DataSource 访问的数据源
  public Object AddDepUser(AdPlacePrice entity) {
    return service.insert(entity)? renderSuccess("添加成功") : renderError("添加失败");
  }
  
   
  @ResponseBody
  @RequestMapping(value = "/deleteAdPlacePrice", method = {RequestMethod.GET, RequestMethod.POST})
  @SystemControllerLog(DataSource=MultipleDataSource.PLAN)//description 日志描述,tableType 日志类别， DataSource 访问的数据源
  public Object DeleteUser(@RequestBody String[] userid) {  
    return service.deleteBatchIds(Arrays.asList(userid)) ? renderSuccess("删除成功") : renderError("删除失败");
  }
  
  @ResponseBody
  @RequestMapping(value = "/modifyAdPlacePrice", method = {RequestMethod.GET, RequestMethod.POST})
  @SystemControllerLog(DataSource=MultipleDataSource.PLAN)//description 日志描述,tableType 日志类别， DataSource 访问的数据源
  public Object modifyDepUser(AdPlacePrice entity) {
    return service.updateById(entity)? renderSuccess("修改成功") : renderError("修改失败");
  }

  @ResponseBody
  @RequestMapping(value = "/GetAdPlaceByWeb", method = {RequestMethod.GET, RequestMethod.POST})
  @SystemControllerLog(DataSource=MultipleDataSource.PLAN)//description 日志描述,tableType 日志类别， DataSource 访问的数据源
  public JSONArray GetAdPlaceByWeb(int id,int channelid) {
    List<AdPlace> group=service.GetAdPlaceByWeb(id,channelid);
    
   /* Map<String,Object> m = new HashMap<String,Object>();
    for (Website li : group) {
      String single="<option value='"+li.getId()+"'>"+li.getWebsitename()+"</option>";
      s+=single;
    }
    m.put("list", group);
    return m;*/
    String temp=JSONObject.toJSONString(group);
    JSONArray array = JSONArray.parseArray(temp);
    return array;
  }
  
  @ResponseBody
  @RequestMapping(value = "/GetAdPlacePriceList", method = {RequestMethod.GET, RequestMethod.POST})
  @SystemControllerLog(DataSource=MultipleDataSource.PLAN)//description 日志描述,tableType 日志类别， DataSource 访问的数据源
  public Object GetAdPlacePriceList(AdPlacePrice entity) {
   return service.selectPlacePriceList(entity);
  }

  
  @ResponseBody
  @RequestMapping(value = "/GetAdPlacePriceListById", method = {RequestMethod.GET, RequestMethod.POST})
  @SystemControllerLog(DataSource=MultipleDataSource.PLAN)//description 日志描述,tableType 日志类别， DataSource 访问的数据源
  public Object GetAdPlacePriceListById(AdPlacePrice entity) {
   return service.selectPlacePriceListById(entity);
  }

}
