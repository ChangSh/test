package com.abc.common.controller;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.abc.core.Common.tools.BdpLogger;
import com.abc.core.Common.tools.UploadPicUtil;
import com.alibaba.fastjson.JSONObject;


@Controller
@RequestMapping(value = "/feature")
public class UploadPiccontroller {
  
  public MultipartFile FileImage;// 得到上传的文件
  
  public String fileImageContentType;// 得到文件的类型
  
  public String fileImageFileName;// 得到文件的名称
  
  @ResponseBody
  @RequestMapping("/uploadTencent")
  public String uploadLocal(String inputId,String prevFileUrl,HttpServletRequest req){

	String access_url="";
    MultipartHttpServletRequest request=(MultipartHttpServletRequest) req;
    //获取文件
    MultipartFile realPicFile = request.getFile(inputId);
    //服务器相对路径
    String path = request.getSession().getServletContext().getRealPath("uploadFiles");  
    //文件后缀
    String suffix = realPicFile.getOriginalFilename().substring(realPicFile.getOriginalFilename().lastIndexOf("."));    
    String fileNameStr = UUID.randomUUID().toString()+suffix;  
    double size=realPicFile.getSize() / 1024.0;
    int fileSize =(int)Math.floor(size);
    System.out.println("begin------------" + path + "/" + fileNameStr);
    File targetFile = new File(path, fileNameStr);  
    if(!targetFile.exists()){  
        targetFile.mkdirs();  
    }  
    //保存  
    try {  
      realPicFile.transferTo(targetFile); //上传至本地     
      String resulejson="";
      resulejson=TencentUpload.fileUpload(fileNameStr);
      JSONObject resultObject = JSONObject.parseObject(resulejson);
      String message = resultObject.getString("message");
      if (!message.equals("SUCCESS")) {
    	  return "false";
      }
      String data=resultObject.getString("data");
      JSONObject dataObject = JSONObject.parseObject(data);
      access_url = dataObject.getString("access_url");
      System.out.println("result---------"+resulejson);
      access_url=access_url.replace("http://appgg-10022965.file.myqcloud.com", "http://startpage.soufunimg.com");
      access_url=access_url.replace("http://video2n.soufun.com", "http://startpage.soufunimg.com");
    } catch (Exception e) { 
    	BdpLogger.error("上传本地服务器失败:" + e.getMessage());
        e.printStackTrace();  
    }
    
    try{
	    //上传滕新云成功后将本地服务器资源删除  
    	targetFile.delete();
    	System.out.println("end------------");
	    //删除未保存操作的上一个资源
	    if(!prevFileUrl.equals("")&&!prevFileUrl.equals(null)){
	  	  TencentUpload.deleteFile(prevFileUrl);
	    }
    }
    catch (Exception e) { 
    	BdpLogger.error("删除失败:" + e.getMessage());
        e.printStackTrace();  
    }

    return access_url+" "+fileSize;    
  }
  
  @ResponseBody
  @RequestMapping("/uploadImg2")
  public String execute(String inputId, HttpServletRequest req) throws Exception {// 使用MultipartFile
                                                                                  // 在SpringＭＶＣ下　　不能直接用File
    
    MultipartHttpServletRequest request = (MultipartHttpServletRequest) req;
    
    // 获取文件
    MultipartFile fileImage = request.getFile(inputId);
    
    if (fileImage != null) {
      System.out.println(fileImage.getName() + "--" + fileImage.getSize());
      
      // 文件后缀
      String suffix = fileImage.getOriginalFilename().substring(fileImage.getOriginalFilename().lastIndexOf("."));
      String fileNameStr = UUID.randomUUID().toString() + suffix;
      
      SaveFileFromInputStream(fileImage.getInputStream(), request.getSession().getServletContext().getRealPath("uploadFiles").replace("feature\\", ""), fileNameStr);// 保存到服务器的路径
      return fileNameStr; //开屏提测时，暂时取得文件大小
    }
    
    return "false";
  }
  
  // 将MultipartFile 转换为File
  public void SaveFileFromInputStream(InputStream stream, String path, String savefile)
      throws IOException {
    System.out.println("------------" + path + "/" + savefile);
    FileOutputStream fs = new FileOutputStream(path + "/" + savefile);
    
    byte[] buffer = new byte[1024 * 1024];
    int bytesum = 0;
    int byteread = 0;
    
    try {
      while ((byteread = stream.read(buffer)) != -1) {
        bytesum += byteread;
        fs.write(buffer, 0, byteread);
        fs.flush();
      }
    }
    catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      System.out.println("------------upload error!");
    }
    System.out.println("------------upload success!");
    fs.close();
    stream.close();
  }
  
  @ResponseBody
  @RequestMapping(value = "/uploadImg3", method = RequestMethod.POST)
  public String uploadImg(String inputId, HttpServletRequest req) throws IOException {
    MultipartHttpServletRequest request = (MultipartHttpServletRequest) req;
    // 获取文件
    MultipartFile realPicFile = request.getFile(inputId);
    // 服务器相对路径
    String path = request.getSession().getServletContext().getRealPath("uploadFiles");
    // 文件后缀
    String suffix = realPicFile.getOriginalFilename().substring(
        realPicFile.getOriginalFilename().lastIndexOf("."));
    
    String fileNameStr = UUID.randomUUID().toString() + suffix;
    File targetFile = new File(path, fileNameStr);
    if (!targetFile.exists()) {
      targetFile.mkdirs();
    }
    // 保存
    try {
      realPicFile.transferTo(targetFile);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return fileNameStr;
    
    // UploadPicUtil pmfr = new UploadPicUtil();
    // String url=pmfr.uploadImg(in,UUID.randomUUID().toString());
    // return url
  }
  
  @ResponseBody
  @RequestMapping(value = "/uploadImg", method = RequestMethod.POST)
  public String FTPUploadImg(String inputId, HttpServletRequest req) throws IOException {
    MultipartHttpServletRequest request = (MultipartHttpServletRequest) req;
    // 获取文件
    MultipartFile realPicFile = request.getFile(inputId);
    
    // 文件后缀
    String suffix = realPicFile.getOriginalFilename().substring(
        realPicFile.getOriginalFilename().lastIndexOf("."));
    
    String fileNameStr = UUID.randomUUID().toString() + suffix;
    
    return UploadPicUtil.FTPUploadFile(realPicFile.getInputStream(), fileNameStr);
  }
 
}