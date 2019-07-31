package com.abc.core.Common.tools;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


/**
 * request公共处理类
 * @author pengmaokui
 */
public final class RequestUtil {
  
  /**
   * private constructor
   */
  private RequestUtil() { }
  
  /**
   * requestMap为<String,String[]>存储，转化为Map<String, Object>
   * @param request request
   * @return map
   */
  public static Map<String, String> getMap(HttpServletRequest request) {
    Map<String, String> map = new HashMap<String, String>();
    Enumeration<String> paramNames = request.getParameterNames();
    while (paramNames.hasMoreElements()) {
        String paramName = (String) paramNames.nextElement();

        String[] paramValues = request.getParameterValues(paramName);
        if (paramValues.length == 1) {
            String paramValue = paramValues[0];
            if (paramValue.length() != 0) {
                map.put(paramName, paramValue);
            }
        }
    }
    return map;
  }
}
