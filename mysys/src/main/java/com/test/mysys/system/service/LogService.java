package com.test.mysys.system.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.nutz.dao.Dao;
import org.nutz.dao.sql.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.test.mysys.common.service.BaseService;
import com.test.mysys.common.utils.NewPager;
import com.test.mysys.system.model.Log;
import com.test.mysys.system.model.User;

@Service
public class LogService extends BaseService{
	
	@Autowired
	private Dao dao;
	
	public Log fetch(Integer id) {
		return dao.fetch(Log.class,id);
	}
	public Log log(Log record) {
		return dao.insert(record);
	}

	public Log log(Map map) {
		Log log = new Log();
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();  
        HttpSession session = request.getSession();  
        //读取session中的用户  
        User user = (User) session.getAttribute("currentUser");  
        //获取请求ip  
        String ip = request.getRemoteAddr();  
		log.setRequestIp(ip);
		String sDescription=String.format("对%信息执行了%操作，记录为%s",
				map.get("tablename"),
				map.get("operate"),
				map.get("description"));
		log.setDescription(sDescription);
		return dao.insert(log);
	}
	public int delete(Integer id) {
		return dao.delete(Log.class,id);
	}
	public int updateIgnoreNull(Log record) {
		return dao.updateIgnoreNull(record);
	}

	public Map<String,Object> queryPage(NewPager page){
		Criteria cri = getCriteriaFromPage(page);
		
	    List<Log> list = dao.query(Log.class, cri, page);
	    page.setRecordCount(dao.count(Log.class, cri));
	    
	    Map<String,Object> map = new HashMap<String,Object>();
		map.put("Total", page.getRecordCount());
		map.put("Rows", list);
	    return map;
	}
}
