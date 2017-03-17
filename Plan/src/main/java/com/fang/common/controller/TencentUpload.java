package com.fang.common.controller;

import com.fang.core.Common.tools.BdpLogger;
import com.fang.core.Common.tools.Constants;
import com.qcloud.cosapi.api.CosCloud;

public class TencentUpload {
  
  // 通过控制台获取AppId,SecretId,SecretKey
  private static final int APP_ID = 10022965;
  
  private static final String SECRET_ID = "AKIDFzKUF5VqV5ai4Seh9xZrdG9VhhAJmTXP";
  
  private static final String SECRET_KEY = "zTFSIbP9db0v5XXkdrfLswTE7t9lSd4E";
  
  // 通过控制台提前建好的bucket
  private static final String bucketName = "appgg";
  
  // 用来测试单文件上传的小文件的远程路径与本地路径
  private static final String SMALLFile_REMOTE_PATH = "/searchAD/";
  //正式站
  //private static final String SMALLFile_LOCAL_PATH = "/usr/local/resin/webapps/ROOT/media.fang.com/uploadFiles/";
 
  //本地
   //private static final String SMALLFile_LOCAL_PATH = "D:/javaCode/SearchAD_V2/src/main/webapp/uploadFiles/";
  
 //测试站
 private static final String SMALLFile_LOCAL_PATH = "/usr/local/resin/webapps/ROOT/media.test.fang.com/uploadFiles/";
  
  public static String fileUpload(String filename) throws Exception {
    CosCloud cos = new CosCloud(APP_ID, SECRET_ID, SECRET_KEY, 60);
    String result = null;
    String rp = SMALLFile_REMOTE_PATH + filename;
    String lp = Constants.SMALLFile_LOCAL_PATH + filename;
    result = cos.uploadFile(bucketName, rp, lp);
    // 关闭释放资源
    cos.shutdown();
    return result;
  }
  
  public static String deleteFile(String filename) {
    CosCloud cos = new CosCloud(APP_ID, SECRET_ID, SECRET_KEY, 60);
    String result = null;
    try {
      result =  cos.deleteFile("appgg", SMALLFile_REMOTE_PATH+filename);
    }
    catch (Exception e) {
      // TODO Auto-generated catch block
      BdpLogger.error("删除腾讯云文件失败:" + e.getMessage());
      e.printStackTrace();
    }
    // 关闭释放资源
    cos.shutdown();
    return result;
  }
}
