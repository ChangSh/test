package com.tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class Auto1 {
  
  /**
   * @param args
   * @throws Exception
   * @throws IOException
   * @throws TemplateException
   */
  public static void main(String[] args) throws Exception {
    System.out.println("请输入模块名称：(首字母大写如 User  页面中模块名称有首字母转大写控制)");
    String methodname = judge();
    System.out.println("等待生成...");
    String[] ftlname = {"controller.ftl", "dao.ftl", "daoimpl.ftl", "service.ftl",
        "serviceimpl.ftl", "entity.ftl"};
    String[] filename = {methodname + "Controller.java", methodname + "Dao.java",
        methodname + "DaoImpl.java", methodname + "Service.java", methodname + "ServiceImpl.java",
        methodname + "Entity.java"};
    for (int i = 0; i < ftlname.length; i++) {
      autoC(ftlname[i], filename[i], methodname);
    }
    System.out.println("生成完成！");
  }
  
  public static String judge(){
    Scanner s = new Scanner(System.in);
    String methodname = s.nextLine();
    if(methodname.equals("")){
      System.out.println("模块名称不能为空！");
      judge();
     }
       return methodname;
    
  }
  
  public static void autoC(String ftlname, String filename, String methodname) throws Exception {
    // 1.创建配置实例Cofiguration
    Configuration cfg = new Configuration();    
    // 2.设置模板文件目录
    // （1）src目录下的目录（template在src下）
    cfg.setDirectoryForTemplateLoading(new File("src/test/resources"));
    // 获取模板（template）
    Template template = cfg.getTemplate(ftlname);
    // 建立数据模型（Map）
    Map<String, String> root = new HashMap<String, String>();
    root.put("name", methodname);
    root.put("method",methodname.substring(0, 1).toLowerCase()+methodname.substring(1));
    
    String controllerpath = "src/main/java/com/fang/plan/controller/";
    String entitypath = "src/main/java/com/fang/plan/entity/";
    String servicepath = "src/main/java/com/fang/plan/service/";
    String daopath = "src/main/java/com/fang/plan/dao/";
    String fileN= controllerpath + filename;
    if (filename.contains("Controller.java")) {
      fileN = controllerpath + filename;
    }
    if (filename.contains("Entity.java")) {
      fileN = entitypath + filename.replace("Entity", "");
    }
    if (filename.contains("Service.java") || filename.contains("ServiceImpl.java")) {
      fileN = servicepath + filename;
    }
    if (filename.contains("Dao.java") || filename.contains("DaoImpl.java")) {
      fileN = daopath + filename;
    }
    File file = new File(fileN);
    if (!file.exists()) {
      file.createNewFile();
      // 向文件写入内容(输出流)
      Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
      template.process(root, out);     
    }
  }
}
