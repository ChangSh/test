package com.fang.plan.controller;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.fang.core.Common.tools.SoapHelper;
import com.fang.core.Common.tools.StoreHtml;
import com.fang.plan.entity.Banner;
import com.fang.plan.service.IBannerService;

/**
 * Banner模块
 * 
 * @auther ：
 * 
 * */

@Controller
@RequestMapping("/Banner")
public class Bannercontroller  extends BaseController {
  
  @Autowired
  private  IBannerService service;
  
  
 /* public Bannercontroller(IBannerService service) {
      this.service = service;
  }*/
  
  @RequestMapping(value = "/main", method = RequestMethod.GET)  
  public ModelAndView main() {
    ModelAndView mav = new ModelAndView("bdp/pages/Banner/main_Banner");
    return mav;
  }
  @ResponseBody                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              
  @RequestMapping(value = "/queryBannerByPage", method = {RequestMethod.GET, RequestMethod.POST})  
  @SystemControllerLog(DataSource=MultipleDataSource.PLAN)//description 日志描述,tableType 日志类别， DataSource 访问的数据源
  public  PagedResult<Banner> queryDepUserByPage(Banner entity,String sort,int limit,int offset) {  
    PagedResult<Banner> pagedResult = new PagedResult<Banner>();
    EntityWrapper<Banner> wrapper=new  EntityWrapper<Banner>();  
    wrapper.where("bannertype= {0}", entity.getBannertype()==0?"1":entity.getBannertype());
    if(entity.getBannertype()==6){
      wrapper.where("AdPlaceId= {0}", entity.getAdPlaceId()==0?"0":entity.getAdPlaceId());
    }
    Page<Banner> page=null;
    if(sort.equals("")||sort==""){
      page=new Page<Banner>(offset,limit,"1");
    }
    else{
      page=new Page<Banner>(offset,limit,sort);
    }
    page.setOptimizeCount(true);
    page=service.selectPage(page,wrapper);    
    List<Banner> banner=page.getRecords();
    if(entity.getHtml()!=""&&entity.getHtml()!=null){
      for(int i=0;i<banner.size();i++){
        banner.get(i).setHtml(StoreHtml.CheckTextOut(banner.get(i).getHtml()));
      }
    }
    pagedResult.setRows(page.getRecords()); 
    pagedResult.setTotal(page.getTotal());
  
    return pagedResult;
  }
  

  @ResponseBody
  @RequestMapping(value = "/addBanner", method = {RequestMethod.GET, RequestMethod.POST})
  @SystemControllerLog(DataSource=MultipleDataSource.PLAN)//description 日志描述,tableType 日志类别， DataSource 访问的数据源
  public Object AddDepUser(Banner entity,HttpServletRequest request) {
    if(entity.getBannertype() == 6){//素材库
      return service.insert(entity)? renderSuccess("添加成功") : renderError("添加失败");
    }else{
   // int CYid=AddBannerToChuanYang(entity);
      // if(CYid > 0){
       //  entity.setCyid(String.valueOf(CYid));
      if(entity.getHtml()!=""&&entity.getHtml()!=null){
        entity.setHtml(StoreHtml.CheckTextDanger(entity.getHtml())); 
      }
         String userName=(String) request.getSession().getAttribute("Plan_UserName");
         Date d=new Date();
         SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
         entity.setAddtime(sdf.format(d));
         entity.setAddoperator(userName);
         return service.insert(entity)? renderSuccess("添加成功") : renderError("添加失败");
       /*}else{
         return renderError("添加失败");
       }*/
    }
   
  }
  
   
  @ResponseBody
  @RequestMapping(value = "/deleteBanner", method = {RequestMethod.GET, RequestMethod.POST})
  @SystemControllerLog(DataSource=MultipleDataSource.PLAN)//description 日志描述,tableType 日志类别， DataSource 访问的数据源
  public Object DeleteUser(@RequestBody String[] userid) { 
    return service.deleteBatchIds(Arrays.asList(userid)) ? renderSuccess("删除成功") : renderError("删除失败");
  }
  @ResponseBody
  @RequestMapping(value = "/selectById", method = {RequestMethod.GET, RequestMethod.POST})
  @SystemControllerLog(DataSource=MultipleDataSource.PLAN)//description 日志描述,tableType 日志类别， DataSource 访问的数据源
  public Object selectById(int id) { 
    return service.selectById(id);
  }
  @ResponseBody
  @RequestMapping(value = "/modifyBanner", method = {RequestMethod.GET, RequestMethod.POST})
  @SystemControllerLog(DataSource=MultipleDataSource.PLAN)//description 日志描述,tableType 日志类别， DataSource 访问的数据源
  public Object modifyDepUser(Banner entity) {
  //  if(EditBannerToChuanYang(entity)){
      entity.setHtml(StoreHtml.CheckTextDanger(entity.getHtml()));  
      return service.updateById(entity)? renderSuccess("修改成功") : renderError("修改失败");
   /* }else{
      return renderError("修改失败");
    }*/
    
  }
  @ResponseBody
  @RequestMapping(value = "/getAllBanner", method = {RequestMethod.GET, RequestMethod.POST})
  @SystemControllerLog(DataSource=MultipleDataSource.PLAN)//description 日志描述,tableType 日志类别， DataSource 访问的数据源
  public List<Banner> getAllModal() {
    return service.getAllBanner();
  }
  @ResponseBody
  @RequestMapping(value = "/getBannerbyOrderId", method = {RequestMethod.GET, RequestMethod.POST})
  @SystemControllerLog(DataSource=MultipleDataSource.PLAN)//description 日志描述,tableType 日志类别， DataSource 访问的数据源
  public List<Banner> getBannerbyOrderId(String orderid) {
    //String userName=(String) request.getSession().getAttribute("Plan_UserName");
    return service.getBannerbyOrderId(orderid);
  }
  @ResponseBody
  @RequestMapping(value = "/getAllOrder", method = {RequestMethod.GET, RequestMethod.POST})
  @SystemControllerLog(DataSource=MultipleDataSource.MEDIA_WORK)//description 日志描述,tableType 日志类别， DataSource 访问的数据源
  public List<String> getAllOrder() {
    return service.getAllOrderId();
  }
  
  @ResponseBody
  @RequestMapping(value = "/AddBannerToChuanYang", method = {RequestMethod.GET, RequestMethod.POST})
  @SystemControllerLog(DataSource=MultipleDataSource.MEDIA_WORK)//description 日志描述,tableType 日志类别， DataSource 访问的数据源
  public int AddBannerToChuanYang(Banner b) {
    Map <String,Object> m=new HashMap<String,Object> ();
  //  m.put("ID","888888");
    m.put("name", b.getName());
    m.put("status", b.getIfeffective());
    m.put("solutionid", "1");
    m.put("fid", "1");
    m.put("weight", b.getWeight());
    m.put("targeturl", b.getTargeturl());
    m.put("bannerimage", "http://");
    m.put("html", "<![CDATA["+b.getHtml()+"]]>");
    int id=SoapHelper.soapAdd(m, "addBanner");
    return id;
    
  }
  
  @ResponseBody
  @RequestMapping(value = "/EditBannerToChuanYang", method = {RequestMethod.GET, RequestMethod.POST})
  @SystemControllerLog(DataSource=MultipleDataSource.MEDIA_WORK)//description 日志描述,tableType 日志类别， DataSource 访问的数据源
  public boolean EditBannerToChuanYang(Banner b) {
    Map <String,Object> m=new HashMap<String,Object> ();
    int id=0;//这个是传样返回的id  不是咱们数据库里边的bannerid
    m.put("name", b.getName());
    m.put("status", b.getIfeffective());
    m.put("solutionid", "1");
    m.put("fid", "1");
    m.put("weight", b.getWeight());
    m.put("targeturl", b.getTargeturl());
    m.put("bannerimage", "http://");
    m.put("html", "<![CDATA["+b.getHtml()+"]]>");
    boolean result=SoapHelper.soapEdit(m, "editBanner", id, "banner");
    return result;
  }
}
