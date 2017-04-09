package com.test.mysys.message.service;

import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.Dao;

import org.nutz.dao.sql.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.mysys.common.service.BaseService;
import com.test.mysys.common.utils.NewPager;
import com.test.mysys.common.utils.TimeUtil;
import com.test.mysys.common.utils.UserUtils;
import com.test.mysys.customer.model.Customer;
import com.test.mysys.goods.model.Goods;
import com.test.mysys.goods.model.Msg;
import com.test.mysys.goods.model.PicFile;



@Service
public class MessageService extends BaseService {
	@Autowired
	private Dao dao;
	
	public String msg(String m){
		Msg msg=new Msg();
		msg.setMsg(m);
		msg.setLoginname(UserUtils.getUser().getLoginname());
		UUID uuid=UUID.randomUUID();
		msg.setId(uuid.toString());
		msg.setMsgTime(TimeUtil.getCurrentTimestamp().toString());
		dao.insert(msg);
		return "ok";
	}
	
	public Map<String,Object> queryMsg(NewPager page){         
		Criteria cri = getCriteriaFromPage(page);               
	    List<Msg> list = dao.query(Msg.class, cri, page);
	    page.setRecordCount(dao.count(Msg.class, cri));
	    Map<String,Object> map = new HashMap<String,Object>();
		map.put("Total", page.getRecordCount());
		map.put("Rows", list);
	    return map;
	}
	
	public int delete(String id){
		 dao.delete(Msg.class,id);
		 return 0;
				
	}
}
