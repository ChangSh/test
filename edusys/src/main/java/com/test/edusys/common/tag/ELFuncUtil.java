package com.test.edusys.common.tag;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.test.edusys.common.utils.CacheUtils;
import com.test.edusys.common.utils.TimeUtil;
import com.test.edusys.common.utils.spring.SpringContextHolder;
import com.test.edusys.system.service.CodeDicService;


public class ELFuncUtil {
	
	private static Map<String, Map> getAllCodes(){
		Map<String, Map> map = (Map<String, Map>) CacheUtils.get("codes");
		if (map==null){
			CodeDicService codeDicService = (CodeDicService)SpringContextHolder.getBean("codeDicService"); //即可取到bean
			map = codeDicService.getAllMap(); 
			CacheUtils.put("codes", map);
		}
		return map;
	}
	 // 根据code和section 得到codename
	 public static String getCodeName(String code,String sectionname) {
		Map<String, Map> maps = getAllCodes();
		Map<String, String> map = maps.get(sectionname);
		Iterator it = map.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry key = (Entry) it.next();
				if (key.getKey().equals(code)){
				    return (String)key.getValue();
				}
			}
		return null;	
	 }	 
	 // 用于页面，拼成json供前台查询
	 public static String getCodes(String sectionname) {
		Map<String, Map> maps = getAllCodes();
		Map<String, String> map = maps.get(sectionname);
		StringBuffer sb = new StringBuffer();

		sb.append("{");
			if (map!=null){
			 Iterator it = map.entrySet().iterator();
				while (it.hasNext()) {
					Map.Entry key = (Entry) it.next();
					sb.append("'"+key.getKey()+"':'"+(String)key.getValue()+"',");
				}
				
			}
		sb.append("}");
		return sb.toString();	
	 }	
	 
	 
//	 // 用于页面，拼成json供前台查询
//	 public static String getMagazines() {
//		Dao dao = (Dao)SpringContextHolder.getBean("dao"); 
//		List<Magazine> list = dao.query(Magazine.class, null);
//		StringBuffer sb = new StringBuffer();
//
//		sb.append("{");
//			if (list!=null){
//				 for (Magazine mag : list) {
//					sb.append("'"+mag.getId()+"':'"+mag.getName()+"',");
//				 }
//			}
//		sb.append("}");
//		return sb.toString();	
//	 }	
	 
	 // 用于页面，拼成json供前台查询
	 public static String getDate() {
		return TimeUtil.getStringDate();	
	 }
}
