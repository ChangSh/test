package com.fang.plan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fang.plan.entity.Sitegroup;
import com.fang.plan.entity.Website;


/**
 * @author ：CHANG
 * Website 表数据库控制层接口
 *
 */
public interface WebsiteMapper extends BaseMapper<Website> {
  
  List<Sitegroup> selectSitegroup(@Param("id") int id);
}