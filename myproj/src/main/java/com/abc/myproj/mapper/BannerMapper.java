package com.abc.myproj.mapper;

import java.util.List;

import com.abc.myproj.entity.Banner;
import com.baomidou.mybatisplus.mapper.BaseMapper;


/**
 *
 * Banner 表数据库控制层接口
 *
 */
public interface BannerMapper extends BaseMapper<Banner> {
  
  List<Banner> getAllBanner();
  List<String> getAllOrderId();
  List<Banner> getBannerbyIdAndType(Banner entity);
}