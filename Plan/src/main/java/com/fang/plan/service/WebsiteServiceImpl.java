package com.fang.plan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fang.plan.entity.Sitegroup;
import com.fang.plan.entity.Website;
import com.fang.plan.mapper.WebsiteMapper;

/**
* @author ：CHANG
* Website 表数据服务层接口实现类
*
*/
@Service
public class WebsiteServiceImpl extends ServiceImpl<WebsiteMapper, Website> implements IWebsiteService {
  
  @Autowired
  private  WebsiteMapper mapper;
  @Override
  public List<Sitegroup> selectSitegroup(int id) {
    
    return mapper.selectSitegroup(id);
  }
  
}
