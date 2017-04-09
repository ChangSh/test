package com.test.mysys.system.service;

import java.util.List;

import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.Dao;
import org.nutz.dao.QueryResult;
import org.nutz.dao.sql.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.mysys.common.service.BaseService;
import com.test.mysys.common.utils.NewPager;
import com.test.mysys.system.model.Area;



@Service
public class AreaService extends BaseService {
	
	@Autowired
	private Dao dao;

	//删除
	public int delete(Integer id) {
		return dao.delete(Area.class,id);
	}

	//插入
	public Area insert(Area record) {
		return dao.insert(record);
	}

	//取单条
	public Area fetch(Integer id) {
		return dao.fetch(Area.class,id);
	}

	//更新非空
	public int updateIgnoreNull(Area record) {
		return dao.updateIgnoreNull(record);
	}

	//更新
	public int update(Area record) {
		return dao.update(record);
	}

	//查询
	public List<Area> query(Condition c){
		if(c == null){
			c = Cnd.orderBy().asc("id");
		}
		return dao.query(Area.class,c, null);
	}
	
	//分页查询
	public QueryResult queryPage(NewPager page){
		page.setOrder("IF(ISNULL(ordercol),100000000,ordercol)");
		page.setOrderBy(NewPager.ASC);
		Criteria cri = getCriteriaFromPage(page);
		cri.getOrderBy().asc("IF(ISNULL(ordercol),100000000,ordercol)");
	    List<Area> list = dao.query(Area.class, cri, page);
	    page.setRecordCount(dao.count(Area.class, cri));
	    return new QueryResult(list, page);
	}
	
}
