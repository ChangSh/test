package com.fang.core.Common.tools;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class UploadPicUtil {
  
  public static String FTPUploadFile(InputStream stream, String fileName) {
    
    if (uploadFile("218.30.110.244", 21, "liyinglong", "jf3e9df2t",
        "/upload.kehu2.soufun.com/searchad", fileName, stream)) {
      return "http://upload.kehu2.soufun.com/searchad/" + fileName;
    }
    else {
      return "";
    }
  }
  
  /**
   * Description: 向FTP服务器上传文件
   * 
   * @Version1.0 Jul 27, 2008 4:31:09 PM by 崔红保（cuihongbao@d-heaven.com）创建
   * @param url
   *        FTP服务器hostname
   * @param port
   *        FTP服务器端口
   * @param username
   *        FTP登录账号
   * @param password
   *        FTP登录密码
   * @param path
   *        FTP服务器保存目录
   * @param filename
   *        上传到FTP服务器上的文件名
   * @param input
   *        输入流
   * @return 成功返回true，否则返回false
   */
  public static boolean uploadFile(String url, int port, String username, String password,
                                   String path, String filename, InputStream input) {
    boolean success = false;
    FTPClient ftp = new FTPClient();
    try {
      int reply;
      ftp.connect(url, port);// 连接FTP服务器
      // 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
      boolean b0 = ftp.login(username, password);// 登录
      reply = ftp.getReplyCode();
      if (!FTPReply.isPositiveCompletion(reply)) {
        ftp.disconnect();
        return false;
      }
      
      if (!ftp.changeWorkingDirectory(path)) {
        return false;
      }
      
      if (!ftp.setFileType(FTPClient.BINARY_FILE_TYPE)) {
        return false;
      }
      
      if (!ftp.storeFile(filename, input)) {
        return false;
      }
      
      input.close();
      ftp.logout();
      success = true;
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    finally {
      if (ftp.isConnected()) {
        try {
          ftp.disconnect();
        }
        catch (IOException ioe) {
        }
      }
    }
    return success;
  }
  
  public String uploadImg(InputStream in, String filename) {
    // 模拟post上传
    System.out.println("开始上传图片~");
    // String urlStr =
    // "http://img1nu.soufun.com/upload/album?isflash=y&channel=%u65B0%u95FB%u7CFB%u7EDF%u76F8%u518C.%u5BB6%u5C45%u7F51&city=%3F%3F%3F%3F&seal=no";
    // // city=青岛
    String urlStr = "http://imgu.fang.com/upload/pic?channel=crm.pic&seal=no&i="
        + UUID.randomUUID();
    String res = null;
    
    HttpURLConnection conn = null;
    String BOUNDARY = "------------------------------WebKitFormBoundaryK1RWe2zu9JI91pu7"; // boundary就是request头和上传文件内容的分隔符
    // String BOUNDARY = "-----------------------------WebKitFormBoundaryK1RWe2zu9JI91pu7"; //
    // boundary就是request头和上传文件内容的分隔符
    try {
      URL url = new URL(urlStr);
      conn = (HttpURLConnection) url.openConnection();
      conn.setConnectTimeout(5000);
      conn.setReadTimeout(30000);
      conn.setDoOutput(true);
      conn.setDoInput(true);
      conn.setUseCaches(false);
      conn.setRequestMethod("POST");
      conn.setRequestProperty("Accept", "*/*");
      conn.setRequestProperty("Connection", "Keep-Alive");
      // conn.setRequestProperty("Accept-Encoding", "gzip");
      conn.setRequestProperty("Accept-Language", "zh-CN");
      conn.setRequestProperty("Origin", "http://kehu.fang.com");
      conn.setRequestProperty("User-Agent",
          " Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");
      conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
      conn.setRequestProperty(
          "Referer",
          "http://jk.photo.fang.com/Album/UploadInterface.aspx?interface=yes&domain=&channel=&city=%C7%E0%B5%BA&albumid=0");
      OutputStream stream = new DataOutputStream(conn.getOutputStream());
      
      // file
      String contentType = "application/octet-stream";
      String name = "file";
      
      StringBuffer strBuf = new StringBuffer();
      strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
      strBuf.append("Content-Disposition: form-data; name=\"" + name + "\"; filename=\"" + filename
          + "\"\r\n");
      strBuf.append("Content-Type:" + contentType + "\r\n\r\n");
      
      stream.write(strBuf.toString().getBytes());
      
      int bytes = 0;
      byte[] bufferOut = new byte[1024];
      while ((bytes = in.read(bufferOut)) != -1) {
        stream.write(bufferOut, 0, bytes);
      }
      in.close();
      
      byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();
      stream.write(endData);
      stream.flush();
      stream.close();
      
      // 读取返回数据
      StringBuffer sb = new StringBuffer();
      BufferedReader reader;
      try {
        String line = null;
        InputStream isr = conn.getInputStream();
        InputStreamReader iread = new InputStreamReader(isr);
        reader = new BufferedReader(iread);
        while ((line = reader.readLine()) != null) {
          sb.append(line).append("\n");
          res = sb.toString();
        }
        reader.close();
        reader = null;
        System.out.println("图片上传结束~");
      }
      catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    catch (Exception e) {
      System.out.println("发送POST请求出错。" + urlStr);
      e.printStackTrace();
    }
    finally {
      if (conn != null) {
        conn.disconnect();
        conn = null;
      }
    }
    
    String url = "";
    if (res != null && res.indexOf("url:") > -1 && res.indexOf("',m:") > -1) {
      url = res.substring(res.indexOf("url:") + 5, res.indexOf("',m:"));
    }
    
    return url;
  }
  
}
