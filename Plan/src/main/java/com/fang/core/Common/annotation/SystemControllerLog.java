package com.fang.core.Common.annotation;


import java.lang.annotation.ElementType;  
import java.lang.annotation.Retention;  
import java.lang.annotation.RetentionPolicy;  
import java.lang.annotation.Target;  

import com.fang.core.Common.tools.MultipleDataSource;
  
  
 /** 
 * ClassName: SystemServiceLog <br/> 
 * Function: AOP日志记录，自定义注解 <br/> 
 * date: 2016年6月7日 上午9:29:01 <br/> 
 * @author lcma 
 * @version  
 * @since JDK 1.7 
 */  
@Target({ElementType.PARAMETER, ElementType.METHOD})      
@Retention(RetentionPolicy.RUNTIME)        
public @interface SystemControllerLog {  
/** 
* 日志描述 
*/  
String description()  default "";   
  
/** 
* 日志类型   0 无日志，1 登录日志， 2 业务日志 ，3 异常日志 ，4 操作日志
*/  
int tableType() default 0;  

/** 
* 数据源 ：默认工作台
*/  
String DataSource() default MultipleDataSource.PLAN;  
  
  
} 
