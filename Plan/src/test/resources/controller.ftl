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
import com.fang.plan.entity.${name};
import com.fang.plan.service.I${name}Service;

/**
 * ${model}模块
 * 
 * @author ：CHANG
 * 
 * */

@Controller
@RequestMapping("/${model}")
public class ${name}Controller  extends BaseController {
  

  private final I${name}Service service;
  
  @Autowired
  public ${name}Controller(I${name}Service service) {
      this.service = service;
  }
  
  @RequestMapping(value = "/main", method = RequestMethod.GET)  
  public ModelAndView main() {
    ModelAndView mav = new ModelAndView("bdp/pages/${model}/main_${model}");
    return mav;
  }
    
  @ResponseBody
  @RequestMapping(value = "/query${model}ByPage", method = RequestMethod.GET)  
  ${dts}//description 日志描述,tableType 日志类别， DataSource 访问的数据源
  public  PagedResult<${name}> query${model}ByPage(${name} entity,String sort,int limit,int offset) {  
    PagedResult<${name}> pagedResult = new PagedResult<${name}>();
    EntityWrapper<${name}> wrapper=new  EntityWrapper<${name}>();   
   //wrapper.where("email like  {0}", "%"+(entity.getEmail()==null?"":entity.getEmail())+"%");
    Page<${name}> page=null;
    if(sort.equals("")||sort==""){
      page=new Page<${name}>(offset,limit,"1");
    }
    else{
      page=new Page<${name}>(offset,limit,sort);
    }
    page.setOptimizeCount(true);
    page=service.selectPage(page,wrapper);    
    pagedResult.setRows(page.getRecords()); 
    pagedResult.setTotal(page.getTotal());
  
    return pagedResult;
  }
  

  @ResponseBody
  @RequestMapping(value = "/add${model}", method = {RequestMethod.GET, RequestMethod.POST})
  ${dts}//description 日志描述,tableType 日志类别， DataSource 访问的数据源
  public Object Add${model}(${name} entity) {
    return service.insert(entity)? renderSuccess("添加成功") : renderError("添加失败");
  }
  
   
  @ResponseBody
  @RequestMapping(value = "/delete${model}", method = {RequestMethod.GET, RequestMethod.POST})
  ${dts}//description 日志描述,tableType 日志类别， DataSource 访问的数据源
  public Object Delete${model}(@RequestBody String[] userid) {  
    return service.deleteBatchIds(Arrays.asList(userid)) ? renderSuccess("删除成功") : renderError("删除失败");
  }
  
  @ResponseBody
  @RequestMapping(value = "/modify${model}", method = {RequestMethod.GET, RequestMethod.POST})
  ${dts}//description 日志描述,tableType 日志类别， DataSource 访问的数据源
  public Object modify${model}(${name} entity) {
    return service.updateById(entity)? renderSuccess("修改成功") : renderError("修改失败");
  }

}
