package com.abc.myproj.service;

import java.util.List;

import com.abc.myproj.entity.Sitegroup;
import com.abc.myproj.entity.Website;
import com.baomidou.mybatisplus.service.IService;
/**
* @author ：CHANG
* Website 表数据服务层接口
*
*/
public interface IWebsiteService  extends IService<Website>{
  
  List<Sitegroup> selectSitegroup(int id);
  
}