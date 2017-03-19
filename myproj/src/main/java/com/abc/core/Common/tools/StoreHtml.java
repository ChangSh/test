package com.abc.core.Common.tools;


public class StoreHtml {
  public static String CheckTextDanger(String checkText)

  {
      String newText = checkText.trim();  //去掉头尾空格
     // newText = newText.replace("\n", "<br>");
        //textBox里的换行是用\n来表示的，如果要在HTML里显示换行要用<br>
      newText = newText.replace("<", "&lt");  //置换 <
      newText = newText.replace(">", "&gt");  //置换 >
    //  newText = newText.replace("'", "''");
        //如果是用存储过程存储数据，这行不用加，如果你用的是SQL语句来存数据，这行要加上，功能为置换 ‘
      return newText;
  }

//如果你从数据库返回的值要用到TextBox
//那还得有个回调的方法，因为TextBox会把Html代码原封不动的列出来
  public static String CheckTextOut(String checkText)
    
  {
      String newText = checkText.trim();  //去掉头尾空格
    //  newText = newText.replace("<br>", "\n");  
      newText = newText.replace("&lt", "<");    
      newText = newText.replace("&gt", ">");        
      return newText;
  }
}
