package com.abc.core.Common.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串操作工具类
 * 
 * @author lt
 */
public class StringUtils {
  
  /**
   * sql非法字符数组 ()[]需要加转义
   * 如有不足 以后再补充 TODO
   */
  public final static String[] ILLAGAL_SQL = {" ", "\\(" , "\\)", "--", ",", ";", "#"};
  
  /**
   * sql非法字符
   */
  public static String ILLAGAL_SQL_REG = null;
  
  static {
    StringBuffer sb = new StringBuffer("(");
    for (String str : ILLAGAL_SQL) {
      sb.append(str).append("|");
    }
    ILLAGAL_SQL_REG = sb.deleteCharAt(sb.length()-1).append(")").toString();
  }

  
  /**
   * 获取非空的字符串
   * @param value 字符串
   * @return 非空的字符串
   */
  public static String getNotNullValue(String value) {
      if(isNotBlank(value))
      {
        return value;
      }
      else
      {
        return "";
      }
  }
  
  /**
   * 判断字符串为空串
   * @param str 字符串
   * @return 结果
   */
  public static boolean isBlank(String str) {
    return str == null || str.trim().length() == 0;
  }

  /**
   * 判断字符串不为空串
   * @param str 字符串
   * @return 结果
   */
  public static boolean isNotBlank(String str) {
    return !isBlank(str);
  }
  
  /**
   * 验证sql非法字符 包括<code>StringUtils.ILLAGAL_SQL</code>里面的字符则判定非法
   * @param str 字符串
   * @return 非法返回null 否则返回原字符串
   */
  public static String checkValidSQL(String str) {
    if (str == null) {
      return null;
    }
    
    Pattern p = Pattern.compile(ILLAGAL_SQL_REG);
    Matcher m = p.matcher(str);
    if (m.find()) {
      BdpLogger.error("非法sql注入");
      return null;
    }
    return str;
  }
  
  /*public static void main(String[] args) {
    String str = "bac()";
    String regEx = "( |\\(|\\)|--)";
    Pattern p = Pattern.compile(regEx);
    Matcher m = p.matcher(null);
    System.out.println(m.find());
   }*/
}