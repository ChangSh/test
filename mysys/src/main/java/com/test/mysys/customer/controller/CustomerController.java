package com.test.mysys.customer.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.test.mysys.common.utils.Encryption;
import com.test.mysys.common.utils.NewPager;
import com.test.mysys.common.utils.SearchFilter;
import com.test.mysys.common.utils.TimeUtil;
import com.test.mysys.common.utils.web.Servlets;
import com.test.mysys.customer.model.Customer;
import com.test.mysys.customer.service.CusotmerService;

@Controller
@RequestMapping(value="/customer")
public class CustomerController {
	@Autowired
	private CusotmerService service;
	@Autowired
	private Dao dao;
	
	@RequestMapping("/ajax_list")
	@ResponseBody
	public Map list(HttpServletRequest request,
			@RequestParam(value="page",defaultValue="1")int page,
			@RequestParam(value="pagesize",defaultValue="10")int pageSize) throws ParseException{
		Map<String,Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		Map<String,SearchFilter> filters = SearchFilter.parse(searchParams);
		
		NewPager pager = new NewPager();
		pager.setOrder(NewPager.DESC);
		pager.setOrderBy("code");
		pager.setPageNumber(page);
		pager.setPageSize(pageSize);
		pager.setFilters(filters);
		
		return service.queryPage(pager);
	}
	@RequestMapping("/edit")
	public String edit(HttpServletRequest request,
			@RequestParam(value="id",defaultValue="0") String id ){
		
		Customer customer=new Customer();
	    customer=service.fetch(id);
		request.setAttribute("ob", customer);
		return "views/customer/Edit";	
	}
	
	 
	@RequestMapping("/delete")
	@ResponseBody
	public String delete(HttpServletRequest request,
			@RequestParam(value="id",defaultValue="0") String id){
			
		   service.delete(id);
		return "ok";	
	}
	/*
	@RequestMapping("/save")
	@ResponseBody
	public String save(V_customer entity,
			HttpServletRequest request) throws UnsupportedEncodingException{
			List<Customer> existCu = service.query(Cnd.where("name","=",entity.getName()).and("phone","=",entity.getPhone()));
			if(existCu.size()>0)//表中已有此客户，则不创建新用户
			{
				Customer cusEntity = new Customer();
				cusEntity.setId(existCu.get(0).getId());//插入客户表
				cusEntity.setPhone(entity.getPhone());
				cusEntity.setName(entity.getName());
				service.updateIgnoreNull(cusEntity);
				//插入地址表选择的地址
				T_repair_addr addEntity = new T_repair_addr();
				addEntity.setCustomer_id(existCu.get(0).getId());//插入地址表
				addEntity.setRepair_address(entity.getRepair_address());
				service.insert(addEntity);
			}else{
				//插入新建的客户信息
				Customer code1 =new Customer();
				code1.setCode(TimeUtil.getTimeString());
				code1.setPassword(Encryption.hashToMD5("111111"));//默认密码
				code1.setRegistration_date(TimeUtil.getStringTimestamp());
				code1.setName(entity.getName());
				code1.setPhone(entity.getPhone());
				Customer insertT = new Customer();
				insertT = service.insert(code1);
				//插入地址表选择的地址
				T_repair_addr addEntity = new T_repair_addr();
				addEntity.setCustomer_id(insertT.getId());//插入地址表
				addEntity.setRepair_address(entity.getRepair_address());
				service.insert(addEntity);
			}
			return "ok";
	}
	*/
	@RequestMapping("/savedit")
	@ResponseBody
	//
	public String savedit(Customer entity,
			HttpServletRequest request){
			//更新客户信息
			service.updateIgnoreNull(entity);
		return "ok";	
	}
	
	
	@RequestMapping("/detail")
	public String detail(HttpServletRequest request,
			@RequestParam(value="id",defaultValue="0") String id ){
		
		Customer customer=new Customer();
	    customer=service.fetch(id);
		request.setAttribute("ob", customer);
		return "views/customer/Detail";	
	}
	
	/*
	@RequestMapping(value="/import")
	@ResponseBody
	public Map<String,Object> impExcel(HttpServletRequest request, HttpServletResponse response,String type,String typess) throws Exception {
		//1级错误提示
		Map<String,Object> errmap=new HashMap<String,Object>();
		//楼宇月报数据类型list集合
		List<String> lyybinfo = new ArrayList<String>();
		// 转型为MultipartHttpRequest：
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		// 获得文件：
		MultipartFile file1 = multipartRequest.getFile("file");
 
			CommonsMultipartFile cf = (CommonsMultipartFile) file1; // 这个myfile是MultipartFile的
			DiskFileItem fi = (DiskFileItem) cf.getFileItem();
			File newfile = fi.getStoreLocation();
			Map lyyb = service.ExcelComInfo(newfile);
			lyybinfo=(List<String>) lyyb.get("info");
			if(lyybinfo.size() == 0){
				errmap.put("message", "ok");
			}else{
				errmap.put("message", lyybinfo);
			}
		return errmap;
	}*/
	
}