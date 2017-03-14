package com.abc.core.Aspect;

import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;

import com.abc.core.Common.annotation.SystemControllerLog;
import com.abc.core.Common.tools.MultipleDataSource;
import com.abc.core.Common.tools.WebUtils;
import com.abc.myproj.entity.OptLog;
import com.abc.myproj.service.IOptLogService;
  
@Aspect    
public class SystemLogAspect {  
 
  @Autowired
  public IOptLogService ilogservice;

  /**
   * 
   * @Title：doAfterInServiceLayer
   * @Description: 方法调用后触发 
   *  记录结束时间
   * @author shaojian.yu 
   * @date 2014年11月2日 下午4:46:21
   * @param joinPoint
   */
  @Before("execution(* com.fang.plan.controller.*.*(..))")
  public void doBeforeInServiceLayer(JoinPoint joinPoint) {
   try {
     String dts=getServiceMthodDataSource(joinPoint);
     if(!dts.equals("")||dts!=""){
     MultipleDataSource.setDataSourceKey(dts);
     }
  }
  catch (Exception e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
  }
  }
  
  /**
   * 
   * @Title：doAfterInServiceLayer
   * @Description: 方法调用后触发 
   *  记录结束时间
   * @author shaojian.yu 
   * @date 2014年11月2日 下午4:46:21
   * @param joinPoint
   */
  @After("execution(* com.fang.plan.controller.*.*(..))")
  public void doAfterInServiceLayer(JoinPoint joinPoint) {
   try {
    int folg=getServiceMthodTableType(joinPoint);
    if(folg>0){
    OptLog entity =new OptLog();
    entity.setOperatorIp(WebUtils.getCurrentUserIp());
    entity.setOperatorName(WebUtils.getCurrentUserName());
    entity.setType(folg);
    entity.setOptTime(DateUtils.truncate(new Date(), Calendar.MILLISECOND).getTime());
    entity.setOptLogDesc(getServiceMthodDescription(joinPoint));    
    ilogservice.insert(entity);
    }
  }
  catch (Exception e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
  }
  }
  
  
  private String getServiceMthodDescription(JoinPoint joinPoint)  
      throws Exception {  
       String targetName = joinPoint.getTarget().getClass().getName();  
       String methodName = joinPoint.getSignature().getName();  
       Object[] arguments = joinPoint.getArgs();  
       Class<?> targetClass = Class.forName(targetName);  
       Method[] methods = targetClass.getMethods();  
       String description = "";  
        for(Method method : methods) {  
            if(method.getName().equals(methodName)) {  
               Class<?>[] clazzs = method.getParameterTypes();  
                if(clazzs.length == arguments.length) {  
                  if(method.getAnnotation(SystemControllerLog.class)!=null){
                   description = method.getAnnotation(SystemControllerLog.class).description();  
                    break;  
                  }
               }  
           }  
        }
        return description;  
  }
  
  private int getServiceMthodTableType(JoinPoint joinPoint)  
      throws Exception {  
       String targetName = joinPoint.getTarget().getClass().getName();  
       String methodName = joinPoint.getSignature().getName();  
       Object[] arguments = joinPoint.getArgs();  
       Class<?> targetClass = Class.forName(targetName);  
       Method[] methods = targetClass.getMethods();  
       int tableType = 0;  
        for (Method method : methods) {  
            if (method.getName().equals(methodName)) {  
               Class<?>[] clazzs = method.getParameterTypes();  
                if (clazzs.length == arguments.length) {  
                  if(method.getAnnotation(SystemControllerLog.class)!=null){
                    tableType = method.getAnnotation(SystemControllerLog.class).tableType();  
                    break;  
                  }
                } 
            } 
        }
        return tableType;  
  }
  
  private String getServiceMthodDataSource(JoinPoint joinPoint)  
      throws Exception {  
      String targetName = joinPoint.getTarget().getClass().getName();  
      String methodName = joinPoint.getSignature().getName();  
      Object[] arguments = joinPoint.getArgs();  
      Class<?> targetClass = Class.forName(targetName);  
      Method[] methods = targetClass.getMethods();  
      String dataSource = "";  
       for(Method method : methods) {  
           if(method.getName().equals(methodName)) {  
              Class<?>[] clazzs = method.getParameterTypes();  
               if(clazzs.length == arguments.length) {  
                 if(method.getAnnotation(SystemControllerLog.class)!=null){
                   dataSource = method.getAnnotation(SystemControllerLog.class).DataSource();  
                   break;  
                 }
              }  
          }  
       }
       return dataSource; 
  }
  
} 