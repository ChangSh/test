package com.abc.myproj.controller;
import java.util.Arrays;
import java.util.List;

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
import com.abc.core.Common.tools.StoreHtml;
import com.abc.myproj.entity.SizeInfo;
import com.abc.myproj.service.ISizeInfoService;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * SizeInfo模块
 * 
 * @auther ：wangjing
 * 
 * */

@Controller
@RequestMapping("/SizeInfo")
public class SizeInfocontroller  extends BaseController {
  

  private final ISizeInfoService service;
  
  @Autowired
  public SizeInfocontroller(ISizeInfoService service) {
      this.service = service;
  }
  
  @RequestMapping(value = "/main", method = RequestMethod.GET)  
  public ModelAndView main() {
    ModelAndView mav = new ModelAndView("bdp/pages/SizeInfo/main_SizeInfo");
    return mav;
  }
    
  @ResponseBody
  @RequestMapping(value = "/querySizeInfoByPage", method = RequestMethod.GET)  
  @SystemControllerLog(DataSource=MultipleDataSource.PLAN)//description 日志描述,tableType 日志类别， DataSource 访问的数据源
  public  PagedResult<SizeInfo> queryDepUserByPage(SizeInfo entity,String sort,int limit,int offset) {  
   PagedResult<SizeInfo> pagedResult = new PagedResult<SizeInfo>();
    Page<SizeInfo> page=null;
    if(sort.equals("")||sort==""){
      page=new Page<SizeInfo>(offset,limit,"1");
    }
    else{
      page=new Page<SizeInfo>(offset,limit,sort);
    }
    if(entity.getType()==0){
      entity.setType("1");
    }   
    List<SizeInfo> size=service.sizelist(page, entity);
    for(int i=0;i<size.size();i++){
      size.get(i).setHtml(StoreHtml.CheckTextOut(size.get(i).getHtml()));
    }
    pagedResult.setRows(size); 
    pagedResult.setTotal(page.getTotal());
    return pagedResult;
  }
  

  @ResponseBody
  @RequestMapping(value = "/addSizeInfo", method = {RequestMethod.GET, RequestMethod.POST})
  @SystemControllerLog(DataSource=MultipleDataSource.PLAN)//description 日志描述,tableType 日志类别， DataSource 访问的数据源
  public Object AddDepUser(SizeInfo entity) {
    entity.setHtml(StoreHtml.CheckTextDanger(entity.getHtml()));  
    return service.insert(entity)? renderSuccess("添加成功") : renderError("添加失败");
  }
  
   
  @ResponseBody
  @RequestMapping(value = "/deleteSizeInfo", method = {RequestMethod.GET, RequestMethod.POST})
  @SystemControllerLog(DataSource=MultipleDataSource.PLAN)//description 日志描述,tableType 日志类别， DataSource 访问的数据源
  public Object DeleteUser(@RequestBody String[] userid) {  
    return service.deleteBatchIds(Arrays.asList(userid)) ? renderSuccess("删除成功") : renderError("删除失败");
  }
  
  @ResponseBody
  @RequestMapping(value = "/modifySizeInfo", method = {RequestMethod.GET, RequestMethod.POST})
  @SystemControllerLog(DataSource=MultipleDataSource.PLAN)//description 日志描述,tableType 日志类别， DataSource 访问的数据源
  public Object modifyDepUser(SizeInfo entity) {
    entity.setHtml(StoreHtml.CheckTextDanger(entity.getHtml()));  
    return service.updateById(entity)? renderSuccess("修改成功") : renderError("修改失败");
  }
  @ResponseBody
  @RequestMapping(value = "/getAllSize", method = {RequestMethod.GET, RequestMethod.POST})
  @SystemControllerLog(DataSource=MultipleDataSource.PLAN)//description 日志描述,tableType 日志类别， DataSource 访问的数据源
  public List<SizeInfo> getAllModal() {
    return service.getAllSizeInfo();
  }
  @ResponseBody
  @RequestMapping(value = "/getSizeById", method = {RequestMethod.GET, RequestMethod.POST})
  @SystemControllerLog(DataSource=MultipleDataSource.PLAN)//description 日志描述,tableType 日志类别， DataSource 访问的数据源
  public SizeInfo getSizeById(int id) {
    SizeInfo size=service.getSizeById(id);
    size.setHtml(StoreHtml.CheckTextOut(size.getHtml()));
    return size;
  }
}
