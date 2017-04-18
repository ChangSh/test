package com.test.mysys.customer.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nutz.dao.Dao;
import org.nutz.dao.sql.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.mysys.common.service.BaseService;
import com.test.mysys.common.utils.NewPager;
import com.test.mysys.customer.model.Customer;

@Service
public class CusotmerService extends BaseService {
	@Autowired
	private Dao dao;

	public Map<String, Object> queryPage(NewPager page) {
		Criteria cri = getCriteriaFromPage(page);
		List<Customer> list = dao.query(Customer.class, cri, page);
		page.setRecordCount(dao.count(Customer.class, cri));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Total", page.getRecordCount());
		map.put("Rows", list);
		return map;
	}

	public Customer fetch(String id) {
		return dao.fetch(Customer.class, id);
	}

	public int updateIgnoreNull(Customer record) {
		return dao.updateIgnoreNull(record);
	}

	public int delete(String id) {
		return dao.delete(Customer.class, id);

	}

}
