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

public class Auto {
  
  /**
   * @param args
   * @throws Exception
   * @throws IOException
   * @throws TemplateException
   */
  public static void main(String[] args) throws Exception {
    
    Scanner s = new Scanner(System.in);
    System.out.println("请输入（表）实体名称 ：(如 User)");
    String methodname = s.nextLine();
    System.out.println("请输入模块名称：(首字母 大写 。。。。。。。如 UserInit， 页面中模块名称有首字母转大写控制)");
    String modelname = s.nextLine();
    System.out.println("等待生成。。。。。。");
    String[] ftlname = {"controller.ftl", "mapper.ftl", "service.ftl","serviceimpl.ftl","entity.ftl","mapperxml.ftl","page.ftl"};
    String[] filename = {methodname + "Controller.java", methodname + "Mapper.java", "I"+methodname + "Service.java", methodname + "ServiceImpl.java",methodname +".java",methodname +"Mapper.xml","main_"+modelname+".jsp"};
    for (int i = 0; i < ftlname.length; i++) {
      autoC(ftlname[i], filename[i], methodname,modelname);
    }
    System.out.println("生成完成！ 请刷新 项目目录");
  }
  
  public static void autoC(String ftlname, String filename, String methodname,String modelname) throws Exception {
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
    root.put("model", modelname);
    root.put("entitycontent", GenEntityMysql.getcontent(methodname,""));
    root.put("paget", GenEntityMysql.getcontent(methodname,"t"));
    root.put("pagea", GenEntityMysql.getcontent(methodname,"a"));
    root.put("pagei", GenEntityMysql.getcontent(methodname,"i"));
    if(GenEntityMysql.URL.contains("220.181.149.7")){
    root.put("dts","@SystemControllerLog(DataSource=MultipleDataSource.PLAN)");
    }
    else{
    root.put("dts","");
    }

    String controllerpath = "src/main/java/com/fang/plan/controller/";
    String servicepath = "src/main/java/com/fang/plan/service/";
    String daopath = "src/main/java/com/fang/plan/mapper/";
    String entity = "src/main/java/com/fang/plan/entity/";
    String xml = "src/main/resources/com/fang/bdp/core/mybatis/";
    String page = "src/main/webapp/bdp/pages/"+modelname+"/";
    String fileN= controllerpath + filename;
    if (filename.contains("Controller.java")) {
      fileN = controllerpath + filename;
    }
    else if (filename.contains("Service.java") || filename.contains("ServiceImpl.java")) {
      fileN = servicepath + filename;
    }
    else if (filename.contains("Mapper.java")) {
      fileN = daopath + filename;
    }
    else if (filename.contains("Mapper.xml")) {
      fileN = xml + filename;
    }
    else if (filename.contains(".jsp")) {
      fileN = page + filename;
    }
    else{
      fileN = entity + filename;
    }
    
    File pagedir =new File(page);    
    //如果文件夹不存在则创建    
    if  (!pagedir .exists()  && !pagedir .isDirectory())      
    {       
      pagedir .mkdir();    
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
