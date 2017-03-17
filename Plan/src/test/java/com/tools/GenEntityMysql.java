package com.tools;
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.PreparedStatement;  
import java.sql.ResultSetMetaData;  
import java.sql.SQLException;  
  
public class GenEntityMysql {  
  
    private static String tablename = "";//表名  
    private static String[] colnames; // 列名数组  
    private static String[] colTypes; //列名类型数组  
    private static int[] colSizes; //列名大小数组  
      

    //数据库连接  
    public static final String URL ="jdbc:sqlserver://220.181.149.7;databasename=advertising_test";  
    private static final String NAME = "advertising_test_admin";  
    private static final String PASS = "dw8R6afg";  
    private static final String DRIVER ="com.microsoft.sqlserver.jdbc.SQLServerDriver";  
  
    /* 
     * 构造函数 
     */  
    public static  String getcontent(String name,String t){  
        tablename=name;
        String content="";
        //创建连接  
        Connection con;  
        //查要生成实体类的表  
        String sql = "select * from " + tablename;  
        PreparedStatement pStemt = null;  
        try {  
            try {  
                Class.forName(DRIVER);  
            } catch (ClassNotFoundException e1) {  
                // TODO Auto-generated catch block  
                e1.printStackTrace();  
            }  
            con = DriverManager.getConnection(URL,NAME,PASS);  
            pStemt = con.prepareStatement(sql);  
            ResultSetMetaData rsmd = pStemt.getMetaData();  
            int size = rsmd.getColumnCount();   //统计列  
            colnames = new String[size];  
            colTypes = new String[size];  
            colSizes = new int[size];  
            for (int i = 0; i < size; i++) {  
                colnames[i] = (rsmd.getColumnName(i + 1)).toLowerCase();  
                colTypes[i] = rsmd.getColumnTypeName(i + 1);    
                colSizes[i] = rsmd.getColumnDisplaySize(i + 1);  
            }  
              
            content = parse(colnames,colTypes,colSizes,t);  
              
            return content;
              
        } catch (SQLException e) {  
            e.printStackTrace();  
        }
        return content; 
    }  
  
    /** 
     * 功能：生成实体类主体代码 
     * @param colnames 
     * @param colTypes 
     * @param colSizes 
     * @return 
     */  
    private static String parse(String[] colnames, String[] colTypes, int[] colSizes,String t) {  
        StringBuffer sb = new StringBuffer();  
     
        if(t=="t"){
          processAllTable(sb);
        }
        if(t=="a"){
          processAllpage_a(sb);
        }
        if(t=="i"){
          processAllpage_i(sb);
        }
        if(t==""){
          processAllAttrs(sb);//属性  
          processAllMethod(sb);//get set方法  
        }
        return sb.toString();  
    }  
      
    /** 
     * 功能：生成所有属性 
     * @param sb 
     */  
    private static void processAllAttrs(StringBuffer sb) {  
          
        for (int i = 0; i < colnames.length; i++) {  
            sb.append("\tprivate " + sqlType2JavaType(colTypes[i]) + " " + colnames[i] + ";\r\n");  
        }  
          
    }  
    
    /** 
     * 功能：生成所有 table
     * @param sb 
     */  
    private static void processAllTable(StringBuffer sb) {            
        for (int i = 0; i < colnames.length; i++) {  
          if(sqlType2JavaType(colTypes[i])=="Date"){
            sb.append(" {field: '"+colnames[i]+"',title: '"+colnames[i]+"',formatter: function(value,row,index){return dateFormat(row."+(colnames[i])+", 'yyyy-MM-dd HH:mm:ss');}}, \r\n");
          }
          else{
          sb.append(" {field: '"+colnames[i]+"',title: '"+colnames[i]+"',formatter: function(value,row,index){return row."+(colnames[i])+";}}, \r\n"); 
          }
        }  
    } 
    private static void processAllpage_a(StringBuffer sb) {          
      for (int i = 0; i < colnames.length; i++) {  
        if(i==0){
        sb.append("<input type='hidden' name='"+(colnames[i])+"' id='hiddenID'>\r\n");  
        }
        else
        {
        sb.append("             <div class='form-group'>\r\n");  
        sb.append("             <label class='col-sm-3 control-label'><span style='color:red'>*</span>"+colnames[i]+"：</label>\r\n");  
        sb.append("               <div class='col-sm-9'>\r\n");  
        sb.append("                 <input class='form-control' name='"+(colnames[i])+"'>\r\n");  
        sb.append("               </div>\r\n");  
        sb.append("             </div>\r\n");  
        }       
      }  
    }
    private static void processAllpage_i(StringBuffer sb) {          
      for (int i = 0; i < colnames.length; i++) {  
        sb.append("             <div class='form-group'> \r\n"); 
        sb.append("             <label class='col-sm-3 control-label'>"+colnames[i]+"：</label> \r\n"); 
        sb.append("               <div class='col-sm-9'> \r\n"); 
        sb.append("                 <label name='"+(colnames[i])+"' style='padding-top: 6px;'></label> \r\n"); 
        sb.append("               </div> \r\n"); 
        sb.append("             </div> \r\n");  
      }  
    }
  
    /** 
     * 功能：生成所有方法 
     * @param sb 
     */  
    private static void processAllMethod(StringBuffer sb) {  
        for (int i = 0; i < colnames.length; i++) {  
            sb.append("\tpublic void set" + initcap(colnames[i]) + "(" + (sqlType2JavaType(colTypes[i])=="int"||sqlType2JavaType(colTypes[i])=="double"?"String":sqlType2JavaType(colTypes[i])) + " " +   
                    colnames[i] + "){\r\n");  
            if(sqlType2JavaType(colTypes[i])=="int"){
              sb.append("\tthis." + colnames[i] + "=Integer.parseInt((" + colnames[i] + "==null||" + colnames[i] + ".equals(\"\")?\"0\":" + colnames[i] + "));\r\n");  
            }
            else if(sqlType2JavaType(colTypes[i])=="double"){
              sb.append("\tthis." + colnames[i] + "=Double.parseDouble((" + colnames[i] + "==null||" + colnames[i] + ".equals(\"\")?\"0\":" + colnames[i] + "));\r\n");  
            }
            else{
              sb.append("\tthis." + colnames[i] + "=" + colnames[i] + ";\r\n");  
            }
            sb.append("\t}\r\n");  
            sb.append("\tpublic " + sqlType2JavaType(colTypes[i]) + " get" + initcap(colnames[i]) + "(){\r\n");  
            sb.append("\t\treturn " + colnames[i] + ";\r\n");  
            sb.append("\t}\r\n");  
        }  
          
    }  
      
    /** 
     * 功能：将输入字符串的首字母改成大写 
     * @param str 
     * @return 
     */  
    private static String initcap(String str) {  
          
        char[] ch = str.toCharArray();  
        if(ch[0] >= 'a' && ch[0] <= 'z'){  
            ch[0] = (char)(ch[0] - 32);  
        }  
          
        return new String(ch);  
    }  
  
    /** 
     * 功能：获得列的数据类型 
     * @param sqlType 
     * @return 
     */  
    private static String sqlType2JavaType(String sqlType) {  
          
        if(sqlType.equalsIgnoreCase("bit")){  
            return "boolean";  
        }else if(sqlType.equalsIgnoreCase("tinyint")){  
            return "int";  
        }else if(sqlType.equalsIgnoreCase("smallint")){  
            return "short";  
        }else if(sqlType.equalsIgnoreCase("int")){  
            return "int";  
        }else if(sqlType.equalsIgnoreCase("bigint")){  
            return "long";  
        }else if(sqlType.equalsIgnoreCase("float")){  
            return "float";  
        }else if(sqlType.equalsIgnoreCase("decimal") || sqlType.equalsIgnoreCase("numeric")   
                || sqlType.equalsIgnoreCase("real") || sqlType.equalsIgnoreCase("money")   
                || sqlType.equalsIgnoreCase("smallmoney")){  
            return "double";  
        }else if(sqlType.equalsIgnoreCase("varchar") || sqlType.equalsIgnoreCase("char")   
                || sqlType.equalsIgnoreCase("nvarchar") || sqlType.equalsIgnoreCase("nchar")   
                || sqlType.equalsIgnoreCase("text")){  
            return "String";  
        }else if(sqlType.equalsIgnoreCase("datetime")||sqlType.equalsIgnoreCase("date")){  
            return "String";  
        }else if(sqlType.equalsIgnoreCase("image")){  
            return "Blod";  
        }  
          
        return null;  
    }  
    public static String toLowerCaseFirstOnes(String s){
      if(Character.isLowerCase(s.charAt(0)))
        return s;
      else
        return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }
  
} 