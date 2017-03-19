package com.fang.plan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fang.plan.entity.HolidayDiscount;


/**
 *
 * HolidayDiscount 表数据库控制层接口
 *
 */
public interface HolidayDiscountMapper extends BaseMapper<HolidayDiscount> {
  
  public List<HolidayDiscount> GetHolidayDiscountByCityid(@Param("cityid")int cityid);
}