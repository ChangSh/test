package com.fang.plan.service;

import com.baomidou.mybatisplus.service.IService;
import com.fang.plan.entity.HolidayDiscount;
import com.fang.plan.entity.OrderBody;
/**
*
* HolidayDiscount 表数据服务层接口
*
*/
public interface IHolidayDiscountService  extends IService<HolidayDiscount>{
  
  double TotalMoney(int WebSiteID,OrderBody body);
}