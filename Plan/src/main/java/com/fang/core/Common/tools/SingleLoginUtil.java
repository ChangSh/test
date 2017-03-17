package com.fang.core.Common.tools;

import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;


public final class SingleLoginUtil {
  /**
   * 单点请求结果中返回的结果中有<code>标记，0表示成功
   */
  private static final String ISSO_CODE_BEGIN = "<code>";
  
  /**
   * 单点请求结果中返回的结果中有<code>标记，0表示成功
   */
  private static final String ISSO_CODE_END = "</code>";
  
  /**
   * 单点请求结果中返回的结果中有<msg>>标记
   */
  private static final String ISSO_MSG_BEGIN = "<msg>";
  
  /**
   * 单点请求结果中返回的结果中有</msg>标记
   */
  private static final String ISSO_MSG_END = "</msg>";
  
  
  /**
   * 固定的加密字符串(DES加密必须要求加密字符串长度为8)
   */
  private static final String SECRET_KEY = "isso_key";
  /**
   * 构造函数
   */
  private SingleLoginUtil() {
    
  }
  
  /**
   * DES加密
   * @param data 待加密串
   * @return 加密后字符串
   */
  public static String encrypt(String data) {
    try {
      IvParameterSpec iv = new IvParameterSpec(SECRET_KEY.getBytes("gbk"));
      Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
      cipher.init(Cipher.ENCRYPT_MODE, generateKey(SECRET_KEY), iv);
      
      byte[] b = cipher.doFinal(data.getBytes());
      return Base64.encode(b);
    }
    catch (Exception e) {
      BdpLogger.error("DES加密出现异常：" + e);
      return null;
    }

  }

  /**
   * 获得加密密钥
   * 
   * @param secretKey 密钥
   * @return 加密密钥
   */
  private static SecretKey generateKey(String secretKey) {
    try {
      DESKeySpec desKeySpec = new DESKeySpec(secretKey.getBytes("gbk"));
      SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
      return keyFactory.generateSecret(desKeySpec);
    }
    catch (Exception e) {
      BdpLogger.error("获得加密密钥出现异常：" + e);
      return null;
    }

  }
  
  
  /**
   * 验证激活或者注销权限是否成功。
   * 
   * @param act true表示激活，增加用户的时候使用，false表示注销删除用户时使用
   * @param email 邮箱
   * @return 返回验证信息
   */
  public static boolean optUserFromISSO(boolean act, String email) {
    try {
      URL url = new URL(getUrl(act, email));
      URLConnection uc = url.openConnection();
      String result = IOUtils.toString(new InputStreamReader(uc.getInputStream(), "GBK"));
      String code = StringUtils.substringBefore(
          StringUtils.substringAfterLast(result, ISSO_CODE_BEGIN), ISSO_CODE_END);
      String message = StringUtils.substringBefore(
          StringUtils.substringAfterLast(result, ISSO_MSG_BEGIN), ISSO_MSG_END);
      
      /**
       * act为true时如果已经激活则返回-8，此时可以认为激活成功。
       */
      if (act) {
        BdpLogger.info("激活单点:" + email + ":" + "  返回代码：" + code + "  返回信息:" + message);
        return "0".equals(code) || "-8".equals(code);
      }
      else {
        BdpLogger.info("注销单点:" + email + ":" + "  返回代码：" + code + "  返回信息:" + message);
        return "0".equals(code);
      }
    }
    catch (Exception e) {
      BdpLogger.error("验证激活或者注销权限出现异常：" + e);
      return false;
    }

  }
  
  /**
   * true表示激活，增加用户的时候使用，false表示注销删除用户时使用
   * 先对sign加密，然后使用 URLEncoder.encode
   * 
   * @param act true表示激活，增加用户的时候使用，false表示注销删除用户时使用
   * @param email 邮箱
   * @return url
   */
  private static String getUrl(boolean act, String email) {
    try {
      // 注销权限
      String input = "deactv_v2" + "_" + email + "_" + Constants.ISSO_SERVICE;
      String url = "http://home.www2.fang.com/isso/isso.php?act=deactv_v2&oa_username=" + email
          + "&isso_sid=" + Constants.ISSO_SERVICE + "&sign="
          + URLEncoder.encode(SingleLoginUtil.encrypt(input), "utf-8");
     
      // 激活权限
      if (act) {
        input = "actv_v2" + "_" + email + "_" + Constants.ISSO_SERVICE;
        url = "http://home.www2.fang.com/isso/isso.php?act=actv_v2&oa_username=" + email
            + "&isso_sid=" + Constants.ISSO_SERVICE + "&sign="
            + URLEncoder.encode(SingleLoginUtil.encrypt(input), "utf-8");
      }
      BdpLogger.info("权限id:" + Constants.ISSO_SERVICE);
      return url;
    }
    catch (Exception e) {
      BdpLogger.error("连接异常:" + e);
      return null;
    }

  }
}
