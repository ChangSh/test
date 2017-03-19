package com.abc.myproj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.abc.myproj.entity.Sitegroup;
import com.abc.myproj.entity.Website;
import com.baomidou.mybatisplus.mapper.BaseMapper;


/**
 * @author ：CHANG
 * Website 表数据库控制层接口
 *
 */
public interface WebsiteMapper extends BaseMapper<Website> {
  
  List<Sitegroup> selectSitegroup(@Param("id") int id);
}