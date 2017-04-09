package com.test.mysys.customer.service;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.mysys.common.service.BaseService;
import com.test.mysys.common.utils.NewPager;
import com.test.mysys.common.utils.TimeUtil;


import com.test.mysys.customer.model.Customer;





@Service
public class CusotmerService extends BaseService {
	@Autowired
	private Dao dao;
	
	
	
	public Map<String,Object> queryPage(NewPager page){         
		Criteria cri = getCriteriaFromPage(page);               
	    List<Customer> list = dao.query(Customer.class, cri, page);
	    page.setRecordCount(dao.count(Customer.class, cri));
	    Map<String,Object> map = new HashMap<String,Object>();
		map.put("Total", page.getRecordCount());
		map.put("Rows", list);
	    return map;
	}
	
	public Customer fetch(String id){
		return dao.fetch(Customer.class,id);
	}
	
	public int updateIgnoreNull(Customer record) { 
		return dao.updateIgnoreNull(record);                    
	}  
	
	public int delete(String id){
		return dao.delete(Customer.class,id);
		
	}
	
}
