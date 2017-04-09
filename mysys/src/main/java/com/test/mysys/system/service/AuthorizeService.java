package com.test.mysys.system.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nutz.dao.Condition;
import org.nutz.dao.Dao;
import org.nutz.dao.sql.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.mysys.common.service.BaseService;
import com.test.mysys.common.utils.NewPager;
import com.test.mysys.system.model.Authorize;



@Service
public class AuthorizeService extends BaseService {
	
	@Autowired
	private Dao dao;

	public int delete(Integer id) {
		return super.delete(Authorize.class,id);
	}

	public Authorize insert(Authorize record) {
		return (Authorize)super.insert(record);
	}

	public Authorize fetch(Integer id) {
		return dao.fetch(Authorize.class,id);
	}

	public int updateIgnoreNull(Authorize record) {
		return super.updateIgnoreNull(record);
	}

	public int update(Authorize record) {
		return super.update(record);
	}

	public List<Authorize> query(Condition c){
		return dao.query(Authorize.class,c, null);
	}
	
	public Map<String,Object> queryPage(NewPager page){
		Criteria cri = getCriteriaFromPage(page);
		
	    List<Authorize> list = dao.query(Authorize.class, cri, page);
	    page.setRecordCount(dao.count(Authorize.class, cri));
	    Map<String,Object> map = new HashMap<String,Object>();
		map.put("Total", page.getRecordCount());
		map.put("Rows", list);
	    return map;
	}
	
}
