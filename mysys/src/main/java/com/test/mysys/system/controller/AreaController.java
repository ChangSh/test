package com.test.mysys.system.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.nutz.dao.Cnd;
import org.nutz.dao.QueryResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.mysys.common.utils.NewPager;
import com.test.mysys.common.utils.SearchFilter;
import com.test.mysys.common.utils.web.Servlets;
import com.test.mysys.system.model.Area;
import com.test.mysys.system.service.AreaService;
import com.test.mysys.system.service.UserService;


@Controller
@RequestMapping(value="/system/area")
public class AreaController {

	@Resource
	private AreaService service;
	@Resource
	private UserService userService;
	
	/*
	 * 列表json
	 */
	@RequestMapping("/ajax_list")
	@ResponseBody
	public QueryResult list(HttpServletRequest request,
			@RequestParam(value="page",defaultValue="1") int page,
			@RequestParam(value="pagesize",defaultValue="10") int pagesize ){
		
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search@");
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		
		NewPager pager = new NewPager();
		pager.setPageNumber(page);
		pager.setPageSize(pagesize);
		pager.setFilters(filters);
		
		return service.queryPage(pager);
	}
	/*
	 * 列表json
	 */
	@RequestMapping("/ajax_tree")
	@ResponseBody
	public List<Area> tree(HttpServletRequest request ){
		
		List<Area> list = service.query(null);
		 return list;
	}
	
	
	/*
	 * 列表json
	 */
	@RequestMapping("/ajax_tree_zyz")
	@ResponseBody
	public List<Area> tree_zyz(HttpServletRequest request ){
		
		List<Area> list = service.query(Cnd.where("fid","=",null));
		 return list;
	}
	
	/*
	 * 列表json
	 */
	@RequestMapping("/ajax_tree_dbt")
	@ResponseBody
	public List<Area> tree_dbt(HttpServletRequest request ){
		
		List<Area> list = service.query(Cnd.where("fid","=",null));
		 return list;
	}
	
	
	/*
	 * 列表json
	 */
	@RequestMapping("/ajax_tree_multi")
	@ResponseBody
	public List<Area> tree_multi(HttpServletRequest request ){
		
		List<Area> list = service.query(null);
		 return list;
	}

	/*
	 * 录入
	 */
	@RequestMapping("/input")
	public String input(HttpServletRequest request,
			@RequestParam(value="id",defaultValue="0") int id){
		request.setAttribute("ob", service.fetch(id));
		
		return "views/system/input-department";	
	}
	
	/*
	 * 保存
	 */
	@RequestMapping("/save")
	@ResponseBody
	public String save(Area department,
						HttpServletRequest request){
		if (department.getId()==null){
			service.insert(department);
		}else{
			service.update(department);
		}
		return "ok";	
	}
	
	
	
	/*
	 * 删除
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public String delete(HttpServletRequest request,
			@RequestParam(value="id",defaultValue="0") int id){
		service.delete(id);
		
		return "ok";	
	}
}