package com.test.edusys.system.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nutz.dao.Dao;
import org.nutz.dao.sql.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.edusys.common.service.BaseService;
import com.test.edusys.common.utils.NewPager;
import com.test.edusys.system.model.Config;
@Service
public class ConfigService  extends BaseService {
	
	@Autowired
	private Dao dao;
	//分页查询
	public Map<String,Object> queryPage(NewPager page){
		Criteria cri = getCriteriaFromPage(page);//打开查询条件
		List<Config>  list = dao.query(Config.class,cri,page);
		page.setRecordCount(dao.count(Config.class,cri));
			
	    Map<String,Object> map = new HashMap<String,Object>();
		map.put("Total", page.getRecordCount());
		map.put("Rows", list);
			
		return map;
		}
}
