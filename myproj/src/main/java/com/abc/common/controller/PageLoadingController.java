/**
 * File：PageLoadingController.java
 * Package：com.abc.bdp.core.controller
 * Author：wangjiashuai
 * Date：2015-7-7 上午10:23:07
 */
package com.abc.common.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.abc.core.Common.tools.CtResult;


/**
 * 
 * 页面加载信息统计
 * 
 * 使用spring mvc interceptor进行拦截，拦截完成后返回到此controller
 * 页面点击统计信息时，请求此Controller
 * 
 */
@Controller
public class PageLoadingController {
  
  /**
   * 页面信息放到List中
   */
  private List<Map<String, String>> loadingInfoList;
  
  /**
   * 获取页面加载信息
   * 
   * @return CtResult
   */
  @ResponseBody
  @RequestMapping("/pageLoadingInfos")
  public CtResult loadInfos() {
    return CtResult.success(loadingInfoList);
  }
  
  /**
   * @param loadingInfoList
   *        set loadingInfoList
   */
  public void setLoadingInfoList(List<Map<String, String>> loadingInfoList) {
    this.loadingInfoList = loadingInfoList;
  }
  
}
