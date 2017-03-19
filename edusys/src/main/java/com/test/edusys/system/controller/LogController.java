package com.test.edusys.system.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.edusys.common.utils.NewPager;
import com.test.edusys.common.utils.SearchFilter;
import com.test.edusys.common.utils.web.Servlets;
import com.test.edusys.system.service.LogService;


@Controller
@RequestMapping(value="/system/log")
public class LogController {

	@Autowired
	private LogService service;
	
	/*
	 * 用户列表
	 */
	@RequestMapping("/ajax_list")
	@ResponseBody
	public Map list(HttpServletRequest request,
			@RequestParam(value="page",defaultValue="1") int page ,
			@RequestParam(value="pagesize",defaultValue="10") int pagesize){
		
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search@");
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		
		NewPager pager = new NewPager();
		pager.setPageNumber(page);
		pager.setPageSize(pagesize);
		pager.setFilters(filters);
		pager.setOrder("desc");
		pager.setOrderBy("createDate");
		return service.queryPage(pager);
		
	}
	
	
	/*
	 * 本人信息
	 */
	@RequestMapping("/input")
	public String input(HttpServletRequest request ,
			@RequestParam(value="id",defaultValue="0") int id){
		
		request.setAttribute("ob", service.fetch(id));
		return "views/system/logInput";	
	}
	
	
}
