package com.abc.core.Common.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

/**
 * properties文件读取工具类
 * 
 * @author zhaolingfei
 */
public final class PropertiesReaderUtils {
  
  /**
   * 私有无参构造
   */
  private PropertiesReaderUtils() {
    
  }
  
  /**
   * 获取配置文件单独key值内容
   * 
   * @param address
   *        配置文件路径
   * @param key
   *        需要湖片区内容的key值
   * @return
   *         所需key值得value，如果配置文件不存在，返回key值，如果没有对应的key值，返回key值
   */
  public static String getProperties(String address, String key) {
    InputStream inputStream = PropertiesReaderUtils.class.getClassLoader().getResourceAsStream(
        address);
    Properties properties = new Properties();
    try {
      if (inputStream == null) {
        BdpLogger.error("properties file : " + address + " can not find");
        return key;
      }
      properties.load(inputStream);
    }
    catch (IOException ioE) {
      BdpLogger.error("url:" + address + "get properties:" + key + " error");
      return key;
    }
    finally {
      IOUtils.closeQuietly(inputStream);
    }
    String value = properties.getProperty(key);
    if (StringUtils.isBlank(value)) {
      return key;
    }
    return value;
  }
  
  /**
   * 获取配置文件行值key value对
   * 
   * @param address
   *        配置文件路径
   * @return
   *         配置文件中所有键值对
   * @throws IOException
   *         io异常
   */
  public static List<String[]> getProperties(String address) throws IOException {
    // 获取输入流
    InputStream inputStream = PropertiesReaderUtils.class.getClassLoader().getResourceAsStream(
        address);
    // 读取字符串
    BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
    // 接收键值对数组
    List<String[]> liStr = new ArrayList<String[]>();
    // 接收输入流行字符串
    String line = "";
    try {
      // 读取每行数据,知道行为null
      while ((line = in.readLine()) != null) {
        // 分割字符串 留下key value
        String[] lineArray = line.split("=");
        // 去除key值无用空格
        lineArray[0] = lineArray[0].trim();
        // 去除value值无用空格
        lineArray[1] = lineArray[1].trim();
        // 当今list
        liStr.add(lineArray);
      }
    }
    catch (IOException ioE) {
      // 捕获IO异常
      ioE.printStackTrace();
    }
    finally {
      // 关闭输入流
      inputStream.close();
    }
    // 返回结果
    return liStr;
  }
  
}
