package com.abc.core.Common.tools;

import java.lang.reflect.Field;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MySqlHelper {
  
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	  
	  private static final String URL = "jdbc:mysql://202.108.253.3:3311/newhouse?useUnicode=true";
	  
	  private static final String UID = "hezuo_newhouse";
	  
	  private static final String PWD = "6c018d61";
  
  /**
   * JDBC驱动类型
   */
  public static Class<?> CLS = null;
  
  /**
   * 获取数据库连接对象
   * 
   * @return
   * @throws ClassNotFoundException
   * @throws SQLException
   */
  public static Connection getConnection() throws ClassNotFoundException, SQLException {
	    if (CLS == null) {
	      CLS = Class.forName(DRIVER);
	    }
	    return DriverManager.getConnection(URL, UID, PWD);
	  }
  
  /**
   * 执行SQL语句不返回查询的操作，返回受影响的行数
   * 
   * @param sql
   *        SQL语句
   * @return 受影响的行数
   * @throws ClassNotFoundException
   * @throws SQLException
   */
  public static int executeNonQuery(String sql) {
    int result = -1;
    Connection con = null;
    PreparedStatement ps = null;
    try {
      con = getConnection();
      ps = con.prepareStatement(sql);
      result = ps.executeUpdate();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    finally {
      close(con, ps, null);
    }
    return result;
  }
  
  /**
   * 执行Insert语句，返回Insert成功之后标识列的值
   * 
   * @param sql
   * @return
   * @throws ClassNotFoundException
   * @throws SQLException
   */
  public static int executeIdentity(String sql) {
    int identity = -1;
    Connection con = null;
    Statement ps = null;
    ResultSet rs = null;
    try {
      con = getConnection();
      ps = con.createStatement();
      ps.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
      rs = ps.getGeneratedKeys();
      if (rs.next()) {
        // identity = rs.getInt("GENERATED_KEYS");
        identity = rs.getInt(1);
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    finally {
      close(con, ps, null);
    }
    return identity;
  }
  
  /**
   * 执行不返回结果集的存储过程
   * 
   * @param sql
   *        存储过程名称
   * @param params
   *        存储过程参数
   * @throws ClassNotFoundException
   * @throws SQLException
   */
  public static void executeNonQuery(String sql, SqlParameter... params) {
    Connection con = null;
    CallableStatement cs = null;
    try {
      con = getConnection();
      cs = con.prepareCall(sql);
      setSqlParameter(cs, params);
      cs.executeUpdate();
      getSqlParameter(cs, params);
    }
    catch (Exception e) {
      e.printStackTrace();
      BdpLogger.error("executeNonQueryHelper:" + e.getMessage());
    }
    finally {
      close(con, cs, null);
    }
  }
  
  /**
   * 执行返回聚合函数的操作
   * 
   * @param sql
   *        含有聚合函数的SQL语句
   * @return 聚合函数的执行结果
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  public static int executeScalar(String sql) {
    int result = -1;
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
      con = getConnection();
      ps = con.prepareStatement(sql);
      rs = ps.executeQuery();
      if (rs.next()) {
        result = rs.getInt(1);
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    finally {
      close(con, ps, rs);
    }
    return result;
  }
  
  /**
   * 执行返回泛型集合的SQL语句
   * 
   * @param cls
   *        泛型类型
   * @param sql
   *        查询SQL语句
   * @return 泛型集合
   * @throws ClassNotFoundException
   * @throws SQLException
   * @throws InstantiationException
   * @throws IllegalAccessException
   */
  public static <T> List<T> executeList(Class<T> cls, String sql) {
    List<T> list = new ArrayList<T>();
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
      con = getConnection();
      ps = con.prepareStatement(sql);
      rs = ps.executeQuery();
      while (rs.next()) {
        T obj = executeResultSet(cls, rs);
        list.add(obj);
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    finally {
      close(con, ps, rs);
    }
    return list;
  }
  
  /**
   * 执行返回泛型集合的存储过程
   * 
   * @param cls
   *        泛型类型
   * @param sql
   *        存储过程名称
   * @param params
   *        存储过程参数
   * @return 泛型集合
   * @throws ClassNotFoundException
   * @throws SQLException
   * @throws InstantiationException
   * @throws IllegalAccessException
   */
  public static <T> List<T> executeList(Class<T> cls, String sql, SqlParameter... params) {
    List<T> list = new ArrayList<T>();
    Connection con = null;
    CallableStatement cs = null;
    ResultSet rs = null;
    try {
      con = getConnection();
      cs = con.prepareCall(sql);
      setSqlParameter(cs, params);
      rs = cs.executeQuery();
      while (rs.next()) {
        T obj = executeResultSet(cls, rs);
        list.add(obj);
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    finally {
      close(con, cs, rs);
    }
    return list;
  }
  
  /**
   * 执行返回泛型类型对象的SQL语句
   * 
   * @param cls
   *        泛型类型
   * @param sql
   *        SQL语句
   * @return 泛型类型对象
   * @throws SQLException
   * @throws ClassNotFoundException
   * @throws InstantiationException
   * @throws IllegalAccessException
   */
  public static <T> T executeEntity(Class<T> cls, String sql) {
    T obj = null;
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
      con = getConnection();
      ps = con.prepareStatement(sql);
      rs = ps.executeQuery();
      while (rs.next()) {
        obj = executeResultSet(cls, rs);
        break;
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      BdpLogger.error("-------------executeEntity<T>Helper:" + e.getMessage());
    }
    finally {
      close(con, ps, rs);
    }
    return obj;
  }
  
  /**
   * 执行返回泛型类型对象的存储过程
   * 
   * @param cls
   *        泛型类型
   * @param sql
   *        SQL语句
   * @param params
   *        存储过程参数
   * @return 泛型类型对象
   * @throws SQLException
   * @throws ClassNotFoundException
   * @throws InstantiationException
   * @throws IllegalAccessException
   */
  public static <T> T executeEntity(Class<T> cls, String sql, SqlParameter... params) {
    T obj = null;
    Connection con = null;
    CallableStatement cs = null;
    ResultSet rs = null;
    try {
      con = getConnection();
      cs = con.prepareCall(sql);
      setSqlParameter(cs, params);
      rs = cs.executeQuery();
      while (rs.next()) {
        obj = executeResultSet(cls, rs);
        break;
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      BdpLogger.error("--------------executeEntityHelper:" + e.getMessage());
    }
    finally {
      close(con, cs, rs);
    }
    return obj;
  }
  
  /**
   * 将一条记录转成一个对象
   * 
   * @param cls
   *        泛型类型
   * @param rs
   *        ResultSet对象
   * @return 泛型类型对象
   * @throws InstantiationException
   * @throws IllegalAccessException
   * @throws SQLException
   */
  private static <T> T executeResultSet(Class<T> cls, ResultSet rs) throws InstantiationException,
      IllegalAccessException, SQLException {
    T obj = cls.newInstance();
    ResultSetMetaData rsm = rs.getMetaData();
    int columnCount = rsm.getColumnCount();
    // Field[] fields = cls.getFields();
    Field[] fields = cls.getDeclaredFields();
    for (int i = 0; i < fields.length; i++) {
      Field field = fields[i];
      String fieldName = field.getName();
      for (int j = 1; j <= columnCount; j++) {
        String columnName = rsm.getColumnName(j);
        if (fieldName.equalsIgnoreCase(columnName)) {
          Object value = rs.getObject(j);
          field.setAccessible(true);
          field.set(obj, value);
          break;
        }
      }
    }
    return obj;
  }
  
  /**
   * 设置存储过程参数名称，参数值，参数方向
   * 
   * @param cs
   * @param params
   * @throws SQLException
   */
  private static void setSqlParameter(CallableStatement cs, SqlParameter... params)
      throws SQLException {
    if (params != null) {
      for (SqlParameter param : params) {
        if (param.OutPut) {
          String paramName = param.Name;
          if (paramName == null || paramName.equals("")) {
            cs.registerOutParameter(1, param.Type);// 设置返回类型参数
          }
          else {
            cs.registerOutParameter(paramName, param.Type);// 设置输出类型参数
          }
        }
        else {
          cs.setObject(param.Name, param.Value);// 设置输入类型参数
        }
      }
    }
  }
  
  /**
   * 得到存储过程参数执行结果
   * 
   * @param cs
   * @param params
   * @throws SQLException
   */
  private static void getSqlParameter(CallableStatement cs, SqlParameter... params)
      throws SQLException {
    for (SqlParameter param : params) {
      if (param.OutPut) {
        String paramName = param.Name;
        if (paramName == null || paramName.equals("")) {
          param.Value = cs.getObject(1);// 返回类型参数值
        }
        else {
          param.Value = cs.getObject(paramName);// 输出类型参数值
        }
      }
    }
  }
  
  /**
   * 关闭JDBC对象，释放资源。
   * 
   * @param con
   *        连接对象
   * @param ps
   *        命令对象
   * @param rs
   *        结果集对象
   * @throws SQLException
   */
  private static void close(Connection con, Statement ps, ResultSet rs) {
    try {
      rs.close();
      if (rs != null) {
        rs = null;
      }
      if (ps != null) {
        ps.close();
        ps = null;
      }
      if (con != null) {
        con.close();
        con = null;
      }
    }
    catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      BdpLogger.error("-----------closeHelper:" + e.getMessage());
    }
  }
}