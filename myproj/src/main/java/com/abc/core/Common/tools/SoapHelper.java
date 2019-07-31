package com.abc.core.Common.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * @author CHANG
 */
public final class SoapHelper {
  
  public static final String SOAPURL = "http://manage.mshow.fang.com/soap/";
  
  private static Map<String,Object> entityMap;
  

  /**
   * @param param 属性名:值
   * @param method 请求方法名
   * @return 传漾主键id
   */
  public static int soapAdd(Map<String,Object> param, String method)
  {
    
    int id = 0;
    try {
      URL url = new URL(SOAPURL);
      URLConnection connection = url.openConnection();
      connection.setDoOutput(true);// 使用 URL 连接进行输出  ;  
      connection.setRequestProperty("Pragma:", "no-cache");  
      connection.setRequestProperty("Cache-Control", "no-cache");  
      connection.setRequestProperty("Content-Type", "text/xml");  
      OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
      //获取xml
      String xmlInfo = getAddXmlInfo(param,method);
      out.write(new String(xmlInfo.getBytes("UTF-8")));
      out.flush();
      out.close();
      BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      String xmlString = "";
      String line;
      while((line = br.readLine())!=null){
        xmlString += line;
      }
      //通过零宽断言正则提取ID
      Pattern p = Pattern.compile("(?<=<return xsi:type=\"xsd:int\">)(.*)(?=</return>)");
      Matcher m = p.matcher(xmlString);  
      while(m.find()){  
        id = Integer.parseInt(m.group());
        return id;
      //System.out.println(m.group());  
      }
        
    }
    catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }  
      
    return id;
}
  
  private static String getAddXmlInfo(Map<String,Object> param, String method){
    StringBuffer soapXml = new StringBuffer();
    soapXml.append("<soapenv:Envelope xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xmlns:xsd='http://www.w3.org/2001/XMLSchema' xmlns:soapenv='http://schemas.xmlsoap.org/soap/envelope/' xmlns:dol='http://www.adsame.com/dolphin/'>");
    soapXml.append("<soapenv:Header><ns1:SOAP_AUTH>fang.com</ns1:SOAP_AUTH></soapenv:Header><soapenv:Body>");
    soapXml.append("<dol:");
    soapXml.append(method);
    soapXml.append(" soapenv:encodingStyle='http://schemas.xmlsoap.org/soap/encoding/'>");
    soapXml.append("<");
    soapXml.append(method);
    soapXml.append("Request xsi:type='dol:");
    soapXml.append(method);
    soapXml.append("'>");
    
    for (Map.Entry<String,Object> entity:param.entrySet())
    {
        soapXml.append("<");
        soapXml.append(entity.getKey());
        soapXml.append(" xsi:type='xsd:string'>");
        soapXml.append(entity.getValue());
        soapXml.append("</");
        soapXml.append(entity.getKey());
        soapXml.append(">");
       
    }
    soapXml.append("</");
    soapXml.append(method);
    soapXml.append("Request>");
    soapXml.append("</dol:");
    soapXml.append(method);
    soapXml.append(">");
    soapXml.append("</soapenv:Body></soapenv:Envelope>");
    
    return soapXml.toString();
  }
  
  /**
   * @param param 属性名-值
   * @param method edit方法名
   * @param id 主键id
   * @param entityNameLowerCase 实体名称首字母小写
   * @return bool 修改成功返回true
   */
  public static boolean soapEdit(Map<String,Object> param, String method, int id, String entityNameLowerCase)
  {
    
    
    try {
      URL url = new URL(SOAPURL);
      URLConnection connection = url.openConnection();
      connection.setDoOutput(true);// 使用 URL 连接进行输出  ;  
      connection.setRequestProperty("Pragma:", "no-cache");  
      connection.setRequestProperty("Cache-Control", "no-cache");  
      connection.setRequestProperty("Content-Type", "text/xml");  
      OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
      //获取xml
      String xmlInfo = getEditXmlInfo(param,method,id,entityNameLowerCase);
      out.write(new String(xmlInfo.getBytes("UTF-8")));
      out.flush();
      out.close();
      BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      String xmlString = "";
      String line;
      while((line = br.readLine())!=null){
        xmlString += line;
      }
      //通过零宽断言正则提取ID
      Pattern p = Pattern.compile("(?<=<return xsi:type=\"xsd:boolean\">)(.*)(?=</return>)");
      Matcher m = p.matcher(xmlString);  
      while(m.find()){  
        if(m.group().equals("true"))
          return true;
        else
          return false;
      }
    }
    catch (IOException e) {
      e.printStackTrace();
    }  
      
    return false;
}
  
  private static String getEditXmlInfo(Map<String,Object> param, String method, int id, String entityNameLowerCase){
    StringBuffer soapXml = new StringBuffer();
    soapXml.append("<soapenv:Envelope xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xmlns:xsd='http://www.w3.org/2001/XMLSchema' xmlns:soapenv='http://schemas.xmlsoap.org/soap/envelope/' xmlns:dol='http://www.adsame.com/dolphin/'>");
    soapXml.append("<soapenv:Header><ns1:SOAP_AUTH>fang.com</ns1:SOAP_AUTH></soapenv:Header><soapenv:Body>");
    soapXml.append("<dol:");
    soapXml.append(method);
    soapXml.append(" soapenv:encodingStyle='http://schemas.xmlsoap.org/soap/encoding/'>");
    soapXml.append("<");
    soapXml.append(entityNameLowerCase);
    soapXml.append("id");
    soapXml.append(" xsi:type='xsd:int'>");
    soapXml.append(id);
    soapXml.append("</");
    soapXml.append(entityNameLowerCase);
    soapXml.append("id");
    soapXml.append(">");
    soapXml.append("<");
    soapXml.append(entityNameLowerCase);
    soapXml.append(" xsi:type='dol:");
    soapXml.append(entityNameLowerCase);
    soapXml.append("'>");
    
    for (Map.Entry<String,Object> entity:param.entrySet())
    {
        soapXml.append("<");
        soapXml.append(entity.getKey());
        soapXml.append(" xsi:type='xsd:string'>");
        soapXml.append(entity.getValue());
        soapXml.append("</");
        soapXml.append(entity.getKey());
        soapXml.append(">");
       
    }
    soapXml.append("</");
    soapXml.append(entityNameLowerCase);
    soapXml.append(">");
    soapXml.append("</dol:");
    soapXml.append(method);
    soapXml.append(">");
    soapXml.append("</soapenv:Body></soapenv:Envelope>");
    
    return soapXml.toString();
  }
  
  /**
   * 
   * @param 实体的主键id
   * @param get方法名
   * @param 实体的全路径
   * @return 一个包含实体属性-名称<key-value>的map
   */
  public static Map<String,Object> soapEntity(int id, String method,String beanUrl)
  {
    
    String xmlString = "";
    try {
      URL url = new URL(SOAPURL);
      URLConnection connection = url.openConnection();
      connection.setDoOutput(true);// 使用 URL 连接进行输出  ;  
      connection.setRequestProperty("Pragma:", "no-cache");  
      connection.setRequestProperty("Cache-Control", "no-cache");  
      connection.setRequestProperty("Content-Type", "text/xml");  
      OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
      //获取xml
      String xmlInfo = getEntityXmlInfo(id,method);
      out.write(new String(xmlInfo.getBytes("UTF-8")));
      out.flush();
      out.close();
      BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      String line;
      while((line = br.readLine())!=null){
        xmlString += line;
      }
     Document doc = DocumentHelper.parseText(xmlString);
     Element root = doc.getRootElement();
     entityMap = new HashMap<String,Object>();
     getNodes(root);
     Class<?> clazz = Class.forName(beanUrl);
     Object obj = clazz.newInstance();
     getN(root,obj);
     entityMap.put("object", obj);
    }
    catch (Exception e) {
      e.printStackTrace();
    }  
   
    return entityMap;
    
}
  
  private static String getEntityXmlInfo(int id, String method){
    StringBuffer soapXml = new StringBuffer();
    soapXml.append("<soapenv:Envelope xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xmlns:xsd='http://www.w3.org/2001/XMLSchema' xmlns:soapenv='http://schemas.xmlsoap.org/soap/envelope/' xmlns:dol='http://www.adsame.com/dolphin/'>");
    soapXml.append("<soapenv:Header><ns1:SOAP_AUTH>fang.com</ns1:SOAP_AUTH></soapenv:Header><soapenv:Body>");
    soapXml.append("<dol:");
    soapXml.append(method);
    soapXml.append(" soapenv:encodingStyle='http://schemas.xmlsoap.org/soap/encoding/'>");
    soapXml.append("<");
    soapXml.append(method);
    soapXml.append("Request xsi:type='xsd:int'>");
    soapXml.append(id);
    soapXml.append("</");
    soapXml.append(method);
    soapXml.append("Request>");
    soapXml.append("</dol:");
    soapXml.append(method);
    soapXml.append(">");
    soapXml.append("</soapenv:Body></soapenv:Envelope>");
    
    return soapXml.toString();
  }
  
  @SuppressWarnings("unchecked")
  private static void getNodes(Element node){
    entityMap.put(node.getName(), node.getTextTrim());
    //所有一级子节点的list 
    List<Element> listElement = node.elements();
    //遍历所有一级子节点  
    for(Element e:listElement){
        getNodes(e);
    }
} 

  
  @SuppressWarnings("unchecked")
  private static void getN(Element node,Object obj) throws Exception{
      makeEntity(node,obj);
      //所有一级子节点的list 
      List<Element> listElement = node.elements();
      //遍历所有一级子节点  
      for(Element e:listElement){
        getN(e,obj);
      }
    
  }
  
  public static void makeEntity(Element node, Object obj) throws Exception{  
     
      Method[] methods = obj.getClass().getMethods();
      for(Method me:methods){
        String methodName = me.getName();
        if(methodName.startsWith("set")){
          //set方法的属性名setName
          String methodAttrName = methodName.substring(3).toLowerCase();
          //获取set方法的参数列表String
          Class<?>[] paramTypes = me.getParameterTypes();
          String parm = paramTypes[0].getName();
          //节点名称和set方法的属性名称相同
          if(node.getName().toLowerCase().equals(methodAttrName)){
              if(parm.equals("java.lang.String")){
                me.invoke(obj, String.valueOf(node.getTextTrim()));
              }else if(parm.equals("java.lang.int")){
                me.invoke(obj, Integer.valueOf(node.getTextTrim()));
              }else if(parm.equals("java.lang.Double")){
                me.invoke(obj, Double.parseDouble(node.getTextTrim()));
              }else if(parm.equals("java.util.Date")){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                me.invoke(obj, sdf.parse(node.getTextTrim()));
              }
          }
        }
      }
}  
  
  
  /**
   * 通过httpclient的方式post xml
   * @param 实体的主键id
   * @param get方法名
   * @param 实体的全路径
   * @return 一个包含实体属性-名称<key-value>的map
   * @throws IOException 
   * @throws ClientProtocolException 
   */
  public static Map<String,Object> soapEntityByHttpClient(int id, String method) throws ClientProtocolException, IOException
  {
    String xmlString = "";
    String xmlInfo = getEntityXmlInfo(id, method);
    CloseableHttpClient httpclient = HttpClients.createDefault(); 
     
      HttpPost httpPost = new HttpPost(SOAPURL);
      //通过指定参数名的方式POST XML,SERVER端需要requset.getParameter("");这里不适用
      /*List<BasicNameValuePair> parameters = new ArrayList<BasicNameValuePair>();
      parameters.add(new BasicNameValuePair("XML",xmlInfo));
      httpPost.setEntity(new UrlEncodedFormEntity(parameters,"UTF-8"));*/
      //不指定参数名方式POST XML
      StringEntity myEntity = new StringEntity(xmlInfo, "UTF-8");
      httpPost.addHeader("Content-Type", "text/xml");
      httpPost.setEntity(myEntity);
      HttpResponse response = httpclient.execute(httpPost);
      HttpEntity entity = response.getEntity();
      
      BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent()));
      String line;
      while((line = br.readLine())!=null){
        xmlString += line;
      }
      System.out.println(xmlString);
    return entityMap;
    
}
  public static void main(String[] args) throws ClientProtocolException, IOException{
     Map<String,Object> m = new HashMap<String,Object>();
   /* //新增
     m.put("name", "test0302chang1");
     m.put("websiteid", 333);
     m.put("sitegroupid", 10);
     int a = soapAdd(m,"addChannelGroup");
     System.out.println(a);*/
     /* //修改
     m.put("name", "022803update6");
     m.put("citypy", "022803changupdate6");
     boolean a = soapEdit(m,"editWebsite",1704,"website");
     System.out.println(a);*/
     //获取实体
    /* m = soapEntity(333,"getWebsite","com.fang.plan.entity.Website");
     Website b = (Website)(m.get("object"));*/
    m = soapEntityByHttpClient(333,"getWebsite");
     //System.out.println(b.getId());
     
  }
}
