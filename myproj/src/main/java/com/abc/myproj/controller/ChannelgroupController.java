package com.abc.myproj.controller;
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

import com.abc.common.controller.BaseController;
import com.abc.core.Common.annotation.SystemControllerLog;
import com.abc.core.Common.tools.MultipleDataSource;
import com.abc.core.Common.tools.PagedResult;
import com.abc.myproj.entity.Channelgroup;
import com.abc.myproj.entity.Website;
import com.abc.myproj.service.IChannelgroupService;
import com.abc.myproj.service.IWebsiteService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * Channelgroup模块
 * 
 * @author ：CHANG
 * 
 * */

@Controller
@RequestMapping("/Channelgroup")
public class ChannelgroupController  extends BaseController {
  

  private final IChannelgroupService service;
  
  private final IWebsiteService website_service;
  
  @Autowired
  public ChannelgroupController(IChannelgroupService service,IWebsiteService website_service) {
      this.service = service;
      this.website_service = website_service;
  }
  
  @RequestMapping(value = "/main", method = RequestMethod.GET)  
  public ModelAndView main() {
    ModelAndView mav = new ModelAndView("bdp/pages/Channelgroup/main_Channelgroup");
    return mav;
  }
    
  @ResponseBody
  @RequestMapping(value = "/queryChannelgroupByPage", method = RequestMethod.GET)  
  @SystemControllerLog(DataSource=MultipleDataSource.PLAN)//description 日志描述,tableType 日志类别， DataSource 访问的数据源
  public  PagedResult<Channelgroup> queryDepUserByPage(Channelgroup entity,String sort,int limit,int offset) {  
    PagedResult<Channelgroup> pagedResult = new PagedResult<Channelgroup>();
    EntityWrapper<Channelgroup> wrapper=new  EntityWrapper<Channelgroup>();   
   wrapper.where("channelgroupname like  {0}", "%"+(entity.getChannelgroupname()==null?"":entity.getChannelgroupname())+"%");
    Page<Channelgroup> page=null;
    if(sort.equals("")||sort==""){
      page=new Page<Channelgroup>(offset,limit,"1");
    }
    else{
      page=new Page<Channelgroup>(offset,limit,sort);
    }
    page.setOptimizeCount(true);
    page=service.selectPage(page,wrapper);    
    pagedResult.setRows(page.getRecords()); 
    pagedResult.setTotal(page.getTotal());
  
    return pagedResult;
  }
  

  @ResponseBody
  @RequestMapping(value = "/addChannelgroup", method = {RequestMethod.GET, RequestMethod.POST})
  @SystemControllerLog(DataSource=MultipleDataSource.PLAN)//description 日志描述,tableType 日志类别， DataSource 访问的数据源
  public Object AddDepUser(Channelgroup entity,HttpServletRequest req) {
    Website w = website_service.selectById(entity.getWebsiteid());
    entity.setSitegroupid(w.getSitegroupid()+"");
    entity.setSitegroupname(w.getSitegroupname());
    entity.setSitegrouppy(w.getSitegrouppy());
    entity.setWebsitename(w.getWebsitename());
    entity.setWebsitepy(w.getWebsitepy());
    entity.setAddperson(req.getSession().getAttribute("Plan_UserName").toString());
    return service.insert(entity)? renderSuccess("添加成功") : renderError("添加失败");
  }
  
   
  @ResponseBody
  @RequestMapping(value = "/deleteChannelgroup", method = {RequestMethod.GET, RequestMethod.POST})
  @SystemControllerLog(DataSource=MultipleDataSource.PLAN)//description 日志描述,tableType 日志类别， DataSource 访问的数据源
  public Object DeleteUser(@RequestBody String[] userid) {  
    return service.deleteBatchIds(Arrays.asList(userid)) ? renderSuccess("删除成功") : renderError("删除失败");
  }
  
  @ResponseBody
  @RequestMapping(value = "/modifyChannelgroup", method = {RequestMethod.GET, RequestMethod.POST})
  @SystemControllerLog(DataSource=MultipleDataSource.PLAN)//description 日志描述,tableType 日志类别， DataSource 访问的数据源
  public Object modifyDepUser(Channelgroup entity,HttpServletRequest req) {
    entity.setAddperson(req.getSession().getAttribute("Plan_UserName").toString());
    return service.updateById(entity)? renderSuccess("修改成功") : renderError("修改失败");
  }

  @ResponseBody
  @RequestMapping(value = "/groupwebsite", method = {RequestMethod.GET, RequestMethod.POST})
  @SystemControllerLog(DataSource=MultipleDataSource.PLAN)//description 日志描述,tableType 日志类别， DataSource 访问的数据源
  public Object sitegrouplist() {
    return service.selectsiteandgroup(0);
  }
  
  @ResponseBody
  @RequestMapping(value = "/getWebsiteByUser", method = {RequestMethod.GET, RequestMethod.POST})
  @SystemControllerLog(DataSource = MultipleDataSource.PLAN)
  // description 日志描述,tableType 日志类别， DataSource 访问的数据源，默认工作台的库
  public Object getWebsiteByUser(HttpServletRequest req) {
    
     List<Channelgroup> ws = service.getWebsiteByUser(req.getSession().getAttribute("Plan_Email").toString());
   
     String temp = JSONObject.toJSONString(ws);
     JSONArray array = JSONArray.parseArray(temp);
     return array;
  }

}
