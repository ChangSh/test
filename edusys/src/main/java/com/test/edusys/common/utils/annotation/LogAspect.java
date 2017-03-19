package com.test.edusys.common.utils.annotation;  

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.entity.Record;
import org.nutz.dao.sql.Sql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.test.edusys.common.utils.TimeUtil;
import com.test.edusys.common.utils.reflection.ReflectionUtils;
import com.test.edusys.system.model.Log;
import com.test.edusys.system.model.User;
import com.test.edusys.system.service.LogService;

/**
 * 日志记录，添加、删除、修改方法AOP
 * @author mifei
 * 
 */
@Aspect
public class LogAspect {
	
	@Autowired
	private LogService logService;//日志记录Service
	@Autowired
	private Dao dao;
    
//    /**
//	 * 修改业务逻辑方法切入点
//	 */
//    @Pointcut("execution(* com.troila.kxe.system.service.*.update*(..))")
//    public void update() { }
//
//    /**
//	 * 修改业务逻辑方法切入点
//	 */
//    @Pointcut("execution(* com.troila.kxe.system.service.*.delete*(..))")
//    public void delete() { }
    /**
	 * 导入
	 */
    @Pointcut("execution(* com.troila.kxe.system.service.*.importFile*(..))")
    public void importFile() { }

//	@AfterReturning(value="insert()", argNames="rtv", returning="rtv")
//    public void insertServiceCall(JoinPoint joinPoint, Object rtv) throws Throwable{
//		//logMethod(joinPoint,"新增");
//	}
//	@Before("update()") 
//    public void updateServiceCall(JoinPoint joinPoint) throws Throwable{
//		//logMethod(joinPoint,"修改");
//	}
	@Before("importFile()")  
    public void importFileCalls(JoinPoint joinPoint) throws Throwable{
		logMethod(joinPoint,"导入");
	}
	
	private void logMethod(JoinPoint joinPoint,String type) throws Exception {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();  
        HttpSession session = request.getSession();  
        //读取session中的用户  
        User user = (User) session.getAttribute("currentUser");  
        //获取请求ip  
        String ip = request.getRemoteAddr();  
		
		//判断参数
		if(joinPoint.getArgs() == null){//没有参数
			return;
		}
		//获取操作内容
		//String opContent = adminOptionContent(joinPoint.getArgs(), methodName);
		//创建日志对象
		Log log = new Log();
		log.setCreateBy(user.getLoginname());//设置管理员id
		log.setCreateDate(TimeUtil.getStringTimestamp());//操作时间
		log.setDescription(type);
		log.setRequestIp(ip);  
		log.setType(type);
		logService.log(log);//添加日志
	}
	
	//没用的方法了
	@Deprecated
	private String getDescription(JoinPoint joinPoint){
		//获取方法名
		String methodName = joinPoint.getSignature().getName();
		String className = joinPoint.getThis().toString();
		className= className.substring(0,className.indexOf("@"));

		Sql sql = Sqls.create("SELECT * FROM  t_system_log_set where className=@name ");
		sql.params().set("name",className);
		sql.setCallback(Sqls.callback.record());
		dao.execute(sql);
		Record record = (Record)sql.getResult();
		if (methodName.indexOf("insert")==0){
			Object ob = joinPoint.getArgs()[0];
			String name="";
			try {
				name = BeanUtils.getProperty(ob, record.getString("fieldname"));
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
			
			String sDescription=String.format("对%s信息执行了操作，记录为%s",
					record.getString("tablename"),
					name);
			return sDescription;
		}else if(methodName.indexOf("delete")==0){
			String sRes = String.format(record.getString("sql"), joinPoint.getArgs()[0]+"");
			
			sql = Sqls.create(sRes);
			sql.setCallback(Sqls.callback.str());
			dao.execute(sql);
			String sDescription=String.format("对%s信息执行了操作，记录为%s",
					record.getString("tablename"),
					sql.getResult().toString());
			return sDescription;
		}else if(methodName.indexOf("update")==0){

			Object oNew = joinPoint.getArgs()[0];
			Object oOld =null;
			String sRes="";
			try {
				String key = ReflectionUtils.getKeyFieldName(Class.forName(record.getString("entityname")));
				String sId =null;
				try {
					sId = BeanUtils.getProperty(oNew, key);
				} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
					e.printStackTrace();
				}
				//数据库里查未更新的数据
				oOld = dao.fetch(Class.forName(record.getString("entityname")),sId);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			try {
				BeanInfo beanInfo = Introspector.getBeanInfo(oNew.getClass());
				PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
			    Object reValue = null;
				for(PropertyDescriptor pd : pds)
				    {
					try {
						String sNewName = BeanUtils.getProperty(oNew, pd.getName())+"";//加""去除空的情况
						String sOldName = BeanUtils.getProperty(oOld, pd.getName())+"";
						if ( !sNewName.equals(sOldName)){
							//如果是非空更新则需要跳过
							if(methodName.indexOf("updateIgnoreNull")==0 && sNewName.equals("null") ){
								continue;
							}
							sRes += String.format("[%s:%s]",sOldName,sNewName);
						}
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					}				
				}
			} catch (IntrospectionException e) {
				e.printStackTrace();
			}
			
			String sDescription = String.format("对%s信息执行了操作，信息变更对比为%s",
					record.getString("tablename"),
					sRes);
			return sDescription;
		}
		
		
		return null;
	}
	
	/**
	 * 使用Java反射来获取被拦截方法(insert、update)的参数值，
	 * 将参数值拼接为操作内容
	 */
	public String adminOptionContent(Object[] args, String mName) throws Exception{

		if (args == null) {
			return null;
		}
		
		StringBuffer rs = new StringBuffer();
		rs.append(mName);
		String className = null;
		int index = 1;
		// 遍历参数对象
		for (Object info : args) {
			
			//获取对象类型
			className = info.getClass().getName();
			className = className.substring(className.lastIndexOf(".") + 1);
			rs.append("[参数" + index + "，类型：" + className + "，值：");
			
			// 获取对象的所有方法
			Method[] methods = info.getClass().getDeclaredMethods();
			
			// 遍历方法，判断get方法
			for (Method method : methods) {
				
				String methodName = method.getName();
				// 判断是不是get方法
				if (methodName.indexOf("get") == -1) {// 不是get方法
					continue;// 不处理
				}
				
				Object rsValue = null;
				try {
					
					// 调用get方法，获取返回值
					rsValue = method.invoke(info);
					
					if (rsValue == null) {//没有返回值
						continue;
					}
					
				} catch (Exception e) {
					continue;
				}
				
				//将值加入内容中
				rs.append("(" + methodName + " : " + rsValue + ")");
			}
			
			rs.append("]");
			
			index++;
		}
		
		return rs.toString();
	}
	
}
