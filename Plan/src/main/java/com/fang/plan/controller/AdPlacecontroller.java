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
import com.fang.plan.entity.*;
import com.fang.plan.service.IAdPlaceService;

/**
 * AdPlace模块
 * 
 * @auther ：
 * 
 * */

@Controller
@RequestMapping("/AdPlace")
public class AdPlacecontroller  extends BaseController {
  

  private final IAdPlaceService service;
  
  @Autowired
  public AdPlacecontroller(IAdPlaceService service) {
      this.service = service;
  }
  
  @RequestMapping(value = "/main", method = RequestMethod.GET)  
  public ModelAndView main() {
    ModelAndView mav = new ModelAndView("bdp/pages/AdPlace/main_AdPlace");
    return mav;
  }
    
  @ResponseBody
  @RequestMapping(value = "/queryAdPlaceByPage", method = RequestMethod.GET)  
  @SystemControllerLog(DataSource=MultipleDataSource.PLAN)//description 日志描述,tableType 日志类别， DataSource 访问的数据源
  public  PagedResult<AdPlace> queryDepUserByPage(AdPlace entity,String sort,int limit,int offset) {  
    PagedResult<AdPlace> pagedResult = new PagedResult<AdPlace>();
    EntityWrapper<AdPlace> wrapper=new  EntityWrapper<AdPlace>();   
   //wrapper.where("email like  {0}", "%"+(entity.getEmail()==null?"":entity.getEmail())+"%");
    System.out.println(entity.getName());
    if(entity.getName()!=null&&!entity.getName().equals(""))
    {
      String where=" Name like '%"+entity.getName()+"%'";
      wrapper.where(where);
    }
    
    Page<AdPlace> page=null;
    if(sort.equals("")||sort==""){
      page=new Page<AdPlace>(offset,limit,"1");
    }
    else{
      page=new Page<AdPlace>(offset,limit,sort);
    }
    page.setOptimizeCount(true);
    page=service.selectPage(page,wrapper);    
    pagedResult.setRows(page.getRecords()); 
    pagedResult.setTotal(page.getTotal());
    
    return pagedResult;
  }
  

  @ResponseBody
  @RequestMapping(value = "/addAdPlace", method = {RequestMethod.GET, RequestMethod.POST})
  @SystemControllerLog(DataSource=MultipleDataSource.PLAN)//description 日志描述,tableType 日志类别， DataSource 访问的数据源
  public Object AddDepUser(AdPlace entity) {
    return service.AddAdplace(entity)? renderSuccess("添加成功") : renderError("添加失败");
  }
  
   
  @ResponseBody
  @RequestMapping(value = "/deleteAdPlace", method = {RequestMethod.GET, RequestMethod.POST})
  @SystemControllerLog(DataSource=MultipleDataSource.PLAN)//description 日志描述,tableType 日志类别， DataSource 访问的数据源
  public Object DeleteUser(@RequestBody String[] userid) {  
    return service.deleteBatchIds(Arrays.asList(userid)) ? renderSuccess("删除成功") : renderError("删除失败");
  }
  
  @ResponseBody
  @RequestMapping(value = "/modifyAdPlace", method = {RequestMethod.GET, RequestMethod.POST})
  @SystemControllerLog(DataSource=MultipleDataSource.PLAN)//description 日志描述,tableType 日志类别， DataSource 访问的数据源
  public Object modifyDepUser(AdPlace entity) {
    return service.updateById(entity)? renderSuccess("修改成功") : renderError("修改失败");
  }
  
  @ResponseBody
  @RequestMapping(value = "/queryAdPlaceList", method = {RequestMethod.GET, RequestMethod.POST})
  @SystemControllerLog(DataSource=MultipleDataSource.PLAN)//description 日志描述,tableType 日志类别， DataSource 访问的数据源
  public Object queryAdPlaceList() {
    return service.selectList(null);
  }
  
  @ResponseBody
  @RequestMapping(value = "/test", method = {RequestMethod.GET, RequestMethod.POST})
  @SystemControllerLog(DataSource=MultipleDataSource.PLAN)//description 日志描述,tableType 日志类别， DataSource 访问的数据源
  public JSONArray test() {
    List<Website> group=service.GetWebsiteList();
    
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
  @RequestMapping(value = "/GetWebInfo", method = {RequestMethod.GET, RequestMethod.POST})
  @SystemControllerLog(DataSource=MultipleDataSource.PLAN)//description 日志描述,tableType 日志类别， DataSource 访问的数据源
  public Website GetWebInfo(String id) {
    Website group=service.GetWebsiteInfo(id);
    
   /* Map<String,Object> m = new HashMap<String,Object>();
    for (Website li : group) {
      String single="<option value='"+li.getId()+"'>"+li.getWebsitename()+"</option>";
      s+=single;
    }
    m.put("list", group);
    return m;*/
    
    return group;

  }
  @ResponseBody
  @RequestMapping(value = "/GetChannel", method = {RequestMethod.GET, RequestMethod.POST})
  @SystemControllerLog(DataSource=MultipleDataSource.PLAN)//description 日志描述,tableType 日志类别， DataSource 访问的数据源
  public JSONArray GetChannel(int id) {
    List<Channelgroup> group=service.GetChannel(id);
    
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
  @RequestMapping(value = "/GetCitys", method = {RequestMethod.GET, RequestMethod.POST})
  @SystemControllerLog(DataSource=MultipleDataSource.PLAN)//description 日志描述,tableType 日志类别， DataSource 访问的数据源
  public JSONArray GetCitys() {
    List<City> group=service.GetCitys();
    
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
  @RequestMapping(value = "/AddAdPlaceToChuanYang", method = {RequestMethod.GET, RequestMethod.POST})
  @SystemControllerLog(DataSource=MultipleDataSource.PLAN)//description 日志描述,tableType 日志类别， DataSource 访问的数据源
  public void AddAdPlaceToChuanYang() {
    
    
    AdPlace a=new AdPlace();
    a.setName("测试1");
    a.setId("2");
    a.setState("1");
    a.setBannerid("2");
    a.setWebid("2");
    a.setWidth(20);
    a.setHeight(20);
    a.setUrl("123");
    a.setRemarks("123123");
    Map <String,Object> m =new HashMap<String,Object>();
    //put("ID", a.getId());
    m.put("name", a.getName());
    m.put("status", a.getState());
    m.put("websiteid", a.getWebid());
    m.put("channelgroupid", 1);//a.getChannelid());
    m.put("adsizeid", a.getBannerid());
    m.put("width", a.getWidth());
    m.put("height", a.getHeight());
    m.put("url", a.getUrl());
    m.put("memo", a.getRemarks());
    m.put("sitegroupid", 10);//a.getCompanygroupid());
    m.put("sitegrouppy", "test");//a.getCompanygroupshort());
    int b= SoapHelper.soapAdd(m, "addChannel");
    service.AddChuanYangAdPlaceId(a.getId(), b);
  }
  
  @ResponseBody
  @RequestMapping(value = "/EditAdPlaceToChuanYang", method = {RequestMethod.GET, RequestMethod.POST})
  @SystemControllerLog(DataSource=MultipleDataSource.PLAN)//description 日志描述,tableType 日志类别， DataSource 访问的数据源
  public void EditAdPlaceToChuanYang() {
    
    
    AdPlace a=new AdPlace();
    a.setName("测试修改");
    a.setId("2");
    a.setState("1");
    a.setBannerid("2");
    a.setWebid("2");
    a.setWidth(20);
    a.setHeight(20);
    a.setUrl("123");
    a.setRemarks("123123");
    Map <String,Object> m =new HashMap<String,Object>();
    //m.put("ID", "159343");//a.getId());
    m.put("name", a.getName());
    m.put("status", a.getState());
    m.put("websiteid", a.getWebid());
    m.put("channelgroupid", 1);//a.getChannelid());
    m.put("adsizeid", a.getBannerid());
    m.put("width", a.getWidth());
    m.put("height", a.getHeight());
    m.put("url", a.getUrl());
    m.put("memo", a.getRemarks());
    m.put("sitegroupid", 10);//a.getCompanygroupid());
    m.put("sitegrouppy", "test");//a.getCompanygroupshort());
    boolean b= SoapHelper.soapEdit(m, "editChannel", 159343, "adPlace");
    //service.AddChuanYangAdPlaceId(a.getId(), b);
  }

}
