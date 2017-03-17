package com.fang.plan.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.fang.plan.entity.Sitegroup;
import com.fang.plan.entity.Website;
/**
* @author ：CHANG
* Website 表数据服务层接口
*
*/
public interface IWebsiteService  extends IService<Website>{
  
  List<Sitegroup> selectSitegroup(int id);
  
}