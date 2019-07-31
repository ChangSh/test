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
import com.abc.myproj.entity.Sitegroup;
import com.abc.myproj.entity.Website;
import com.abc.myproj.service.IWebsiteService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * Website模块
 * 
 * @author ：CHANG
 * 
 * */

@Controller
@RequestMapping("/Website")
public class WebsiteController  extends BaseController {
  

  private final IWebsiteService service;
  
  @Autowired
  public WebsiteController(IWebsiteService service) {
      this.service = service;
  }
  
  @RequestMapping(value = "/main", method = RequestMethod.GET)  
  public ModelAndView main() {
    ModelAndView mav = new ModelAndView("bdp/pages/Website/main_Website");
    return mav;
  }
    
  @ResponseBody
  @RequestMapping(value = "/queryWebsiteByPage", method = RequestMethod.GET)  
  @SystemControllerLog(DataSource=MultipleDataSource.PLAN)//description 日志描述,tableType 日志类别， DataSource 访问的数据源
  public  PagedResult<Website> queryDepUserByPage(Website entity,String sort,int limit,int offset) {  
    PagedResult<Website> pagedResult = new PagedResult<Website>();
    EntityWrapper<Website> wrapper=new  EntityWrapper<Website>();   
   wrapper.where("websitename like  {0}", "%"+(entity.getWebsitename()==null?"":entity.getWebsitename())+"%");
    Page<Website> page=null;
    if(sort.equals("")||sort==""){
      page=new Page<Website>(offset,limit,"1");
    }
    else{
      page=new Page<Website>(offset,limit,sort);
    }
    page.setOptimizeCount(true);
    page=service.selectPage(page,wrapper);    
    pagedResult.setRows(page.getRecords()); 
    pagedResult.setTotal(page.getTotal());
  
    return pagedResult;
  }
  

  @ResponseBody
  @RequestMapping(value = "/addWebsite", method = {RequestMethod.GET, RequestMethod.POST})
  @SystemControllerLog(DataSource=MultipleDataSource.PLAN)//description 日志描述,tableType 日志类别， DataSource 访问的数据源
  public Object AddDepUser(Website entity,HttpServletRequest req) {
    entity.setSitegroupname(service.selectSitegroup(entity.getSitegroupid()).get(0).getSitegroupname());
    entity.setSitegrouppy(service.selectSitegroup(entity.getSitegroupid()).get(0).getSitegrouppy());
    entity.setAddperson(req.getSession().getAttribute("Plan_UserName").toString());
    return service.insert(entity)? renderSuccess("添加成功") : renderError("添加失败");
  }
  
   
  @ResponseBody
  @RequestMapping(value = "/deleteWebsite", method = {RequestMethod.GET, RequestMethod.POST})
  @SystemControllerLog(DataSource=MultipleDataSource.PLAN)//description 日志描述,tableType 日志类别， DataSource 访问的数据源
  public Object DeleteUser(@RequestBody String[] userid) {  
    return service.deleteBatchIds(Arrays.asList(userid)) ? renderSuccess("删除成功") : renderError("删除失败");
  }
  
  @ResponseBody
  @RequestMapping(value = "/modifyWebsite", method = {RequestMethod.GET, RequestMethod.POST})
  @SystemControllerLog(DataSource=MultipleDataSource.PLAN)//description 日志描述,tableType 日志类别， DataSource 访问的数据源
  public Object modifyDepUser(Website entity,HttpServletRequest req) {
    entity.setAddperson(req.getSession().getAttribute("Plan_UserName").toString());
    return service.updateById(entity)? renderSuccess("修改成功") : renderError("修改失败");
  }
  
  @ResponseBody
  @RequestMapping(value = "/sitegrouplist", method = {RequestMethod.GET, RequestMethod.POST})
  @SystemControllerLog(DataSource=MultipleDataSource.PLAN)//description 日志描述,tableType 日志类别， DataSource 访问的数据源
  public JSONArray sitegrouplist() {
    List<Sitegroup> l = service.selectSitegroup(0);
    String temp = JSONObject.toJSONString(l);
    JSONArray array = JSONArray.parseArray(temp);
    return array;
  }

}
