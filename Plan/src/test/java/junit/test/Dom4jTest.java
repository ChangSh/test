package junit.test;



import java.lang.reflect.Field;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.ss.formula.functions.T;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.Test;


public class Dom4jTest {
  
  @Test
  public void test() {
    StringBuffer ss = new StringBuffer();
    ss.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
    ss.append("<SOAP-ENV:Envelope xmlns:SOAP-ENV = \"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns1=\"server.php\" ");
    ss.append("xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:SOAP-");
    ss.append("ENC=\"http://schemas.xmlsoap.org/soap/encoding/\" SOAP-");
    ss.append("ENV:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">");
    ss.append("<SOAP-ENV:Body><ns1:addWebsiteResponse>");
    ss.append("<return xsi:type=\"xsd:int\">1704</return>");
    ss.append("</ns1:addWebsiteResponse></SOAP-ENV:Body></SOAP-ENV:Envelope>");
    String xmlString = ss.toString();
    //利用零宽断言匹配
    Pattern p = Pattern.compile("(?<=<return xsi:type=\"xsd:int\">)(.*)(?=</return>)");
    Matcher m = p.matcher(xmlString);  
    while(m.find()){  
    System.out.println(m.group());  
    }
    String str1 = "<return xsi:type=\"xsd:int\">1704</return>";
    
    
    //利用xmlparsers解析
    /*DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); 
    DocumentBuilder builder= null;
    try {
      builder = factory.newDocumentBuilder();
      org.w3c.dom.Document doc = builder.parse(xmlString);
      NodeList nodelist = doc.getElementsByTagName("return");
      for (int i = 0; i < nodelist.getLength(); i++) {
        String s = nodelist.item(i).getTextContent();
        System.out.println(s);
      }
    }
    catch (SAXException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    catch (ParserConfigurationException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }*/
    //使用dom4j
    try {
      Document doc = DocumentHelper.parseText(xmlString);
      Element root = doc.getRootElement();
      //Node aa = doc.selectSingleNode("//*[@type'xsdint']");
      this.getNodes(root);
    }
    catch (DocumentException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    
    try {
      Class<?> t = Class.forName("com.fang.plan.entity.Website");
      Object obj = t.newInstance();
      Field[] field = t.getDeclaredFields();
      for(int i=0;i<field.length;i++){
        field[i].set(obj, "");
      }
      
    }
    catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }
  
  void getNodes(Element node){  
    System.out.println("--------------------");  
      
    //当前节点的名称、文本内容和属性  
    System.out.println("当前节点名称："+node.getName());//当前节点名称  
    System.out.println("当前节点的内容："+node.getTextTrim());//当前节点名称  
   
    List<Attribute> listAttr = node.attributes();//当前节点的所有属性的list  
    for(Attribute attr:listAttr){//遍历当前节点的所有属性  
        String name = attr.getName();//属性名称  
        String value = attr.getValue();//属性的值  
        System.out.println("属性名称："+name+"属性值："+value);  
    }  
      
    //递归遍历当前节点所有的子节点  
    List<Element> listElement = node.elements();//所有一级子节点的list  
    for(Element e:listElement){//遍历所有一级子节点  
        this.getNodes(e);//递归  
    }  
} 
  
  
  public static T getNodes(Element node,Class<T> c){  
    //entityMap.put(node.getName(), node.getTextTrim());
    T t = null;
    try {
      t = c.newInstance();
      Class<?> clazz = Class.forName("com.fang.plan.entity.Website");
      Object obj = clazz.newInstance();
      Field[] field = clazz.getDeclaredFields();
      for(int i=0;i<field.length;i++){
        if(field[i].getName().equalsIgnoreCase(node.getName())){
          field[i].setAccessible(true);
          System.out.println(field[i].getGenericType());
          if(String.valueOf(field[i].getGenericType()).equals("int")){
            field[i].set(obj, Integer.parseInt(node.getTextTrim()));
          }else{
            field[i].set(obj,node.getTextTrim());
          }
        }
      }
      
    }
    catch (ClassNotFoundException | InstantiationException | IllegalAccessException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
    //所有一级子节点的list 
    List<Element> listElement = node.elements();
    //遍历所有一级子节点  
    for(Element e:listElement){
        //getNodes(e);
    }
    return t;  
}  
}
