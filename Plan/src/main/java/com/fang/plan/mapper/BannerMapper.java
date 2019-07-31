package com.fang.plan.mapper;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fang.plan.entity.Banner;


/**
 *
 * Banner 表数据库控制层接口
 *
 */
public interface BannerMapper extends BaseMapper<Banner> {
  
  List<Banner> getAllBanner();
  List<String> getAllOrderId();
  List<Banner> getBannerbyOrderId(String orderid);
}