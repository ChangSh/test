package com.abc.core.Common.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wangyouwei
 *         日期格式装换工具
 */
public final class DateUtil {
  
  /**
   *  构造方法私有化
   */
  private DateUtil() {
    
  }
  /**
   * 获取SimpleDateFormat
   * 
   * @param parttern
   *        日期格式
   * @return SimpleDateFormat对象
   *         异常：非法日期格式
   */
  private static SimpleDateFormat getDateFormat(String parttern) {
    return new SimpleDateFormat(parttern);
  }
  
  /**
   * 将日期转化为日期字符串。失败返回null。
   * 
   * @param date
   *        日期
   * @param parttern
   *        日期格式
   * @return 日期字符串
   */
  public static String dateToString(Date date, String parttern) {
    String dateString = null;
    if (date != null) {
      try {
        dateString = getDateFormat(parttern).format(date);
      }
      catch (Exception e) {
      }
    }
    return dateString;
  }
  
  /**
   * 将Long型时间戳转换为Date
   * 
   * @param dateLong
   *        日期
   * 
   * @return 日期字符串
   */
  public static Date longToDate(Long dateLong) {
    // 前面的lSysTime是秒数，先乘1000得到毫秒数，再转为java.util.Date类型
    Date date = new Date(dateLong);
    return date;
  }

  
  /**
   * 将日期字符串转化为日期。失败返回null。
   * 
   * @param dateString
   *        日期
   * @param parttern
   *        日期格式
   * @return 日期字符串
   */
  public static Date stringToDate(String dateString, String parttern) {
    Date date = null;
    try {
      date = getDateFormat(parttern).parse(dateString);
    }
    catch (ParseException e) {
      e.printStackTrace();
    } 
    return date;
  }
  
  /**
   * 将Date型时间戳转换为Long
   * 
   * @param date
   *        日期
   * 
   * @return 日期字符串
   */
  public static Long dateToLong(Date date) {
    // 前面的lSysTime是秒数，先乘1000得到毫秒数，再转为java.util.Date类型
    Long dateLong = date.getTime();
    return dateLong;
  }
}
