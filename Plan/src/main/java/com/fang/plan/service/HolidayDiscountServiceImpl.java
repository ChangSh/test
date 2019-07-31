package com.fang.plan.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fang.plan.entity.HolidayDiscount;
import com.fang.plan.entity.OrderBody;
import com.fang.plan.entity.OrderDetails;
import com.fang.plan.mapper.HolidayDiscountMapper;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

/**
*
* HolidayDiscount 表数据服务层接口实现类
*
*/
@Service
public class HolidayDiscountServiceImpl extends ServiceImpl<HolidayDiscountMapper, HolidayDiscount> implements IHolidayDiscountService {

  @Autowired
  private HolidayDiscountMapper HolidayDiscountMapper;
  
  //再添加两个接受参数，订单号和订单折扣
  @Override
  public double TotalMoney(int WebSiteID, OrderBody body) {
    // with discount
    int cityid = WebSiteID;
    double total = 0;
    List<HolidayDiscount> list = HolidayDiscountMapper.GetHolidayDiscountByCityid(cityid);
    // 循环整个排期数据
    for (OrderDetails details : body.getList()) {
      if (details.getDetails().getIsPay() > 0) {
        // 循环排气数据中的每一条数据
        for (String s : details.getTimes()) {
          
          // 循环该网站下的所有优惠
          for (HolidayDiscount a : list) {
            if (a.getChannelid() == -1 || String.valueOf(a.getChannelid()).equals(details.getDetails().getChannelid())) {
              if (a.getAdplaceid().equals("*") || a.getAdplaceid().equals(details.getDetails().getAdplaceid())) {
                if (String.valueOf(a.getConfigyear()).equals(s.substring(0, 3))) {
                  if (String.valueOf(a.getConfigmonth()).equals(s.substring(5, 6)) || a.getConfigmonth() == -1) {
                    if (String.valueOf(a.getConfigday()).equals(s.substring(8, 9)) || a.getConfigday() == -1) {
                      // 判断今天是工作日还是周末还是节日
                      
                      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                      try {
                        Date date = sdf.parse(s);
                        // 现在还未添加节日，如果有的话这个个的外层套一层节日的判断条件（若不是节日则走下边的判断）
                        if (date.getDay() == 0 || date.getDay() == 6) {
                          total += details.getDetails().getPrice() * a.getWeekenddiscount();
                          continue;
                        }
                        else {
                          total += details.getDetails().getPrice() * a.getWeekdaydiscount();
                          continue;
                        }
                      }
                      catch (java.text.ParseException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                      }
                    }
                  }
                }
              }
            }
            total += details.getDetails().getPrice();
          }
        }
      }
    }
    
    // without discount
    double total2 = 0.0;
    for (OrderDetails details : body.getList()) {
      if (details.getDetails().getIsPay() > 0) {
        total2 += details.getDetails().getPrice() * details.getTimes().size();
      }
    }
    return total;
  }
  
  
}
