package com.test.mysys.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.nutz.dao.QueryResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.mysys.common.utils.NewPager;
import com.test.mysys.common.utils.SearchFilter;
import com.test.mysys.common.utils.web.Servlets;
import com.test.mysys.system.model.Menu;
import com.test.mysys.system.service.MenuService;
import com.test.mysys.system.service.Tree;

@Controller
@RequestMapping(value = "/system/menu")
public class MenuController {

	@Resource
	private MenuService service;

	/*
	 * 列表json
	 */
	@RequestMapping("/ajax_list")
	@ResponseBody
	public QueryResult list(HttpServletRequest request, @RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "pagesize", defaultValue = "10") int pagesize) {

		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search@");
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);

		NewPager pager = new NewPager();
		pager.setPageNumber(page);
		pager.setPageSize(pagesize);
		pager.setFilters(filters);
		pager.setOrder(NewPager.ASC);
		pager.setOrderBy("sx");

		return service.queryPage(pager);
	}

	/*
	 * 显示菜单
	 */
	@RequestMapping("/show_menu")
	@ResponseBody
	public Object show_menu(HttpServletRequest request) {
		List<Menu> list = service.queryMenusByCurrentUserRole(null);
		Tree tree = new Tree(list);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("html", tree.buildTree());
		return map;
	}

	/*
	 * 列表json
	 */
	@RequestMapping("/ajax_all")
	@ResponseBody
	public List<Menu> all(HttpServletRequest request,
			@RequestParam(value = "pid", defaultValue = "", required = false) String pid,
			@RequestParam(value = "roleid", required = false) String roleid,
			@RequestParam(value = "check", required = false) boolean check) {
		if (check) {
			// 查当前角色的菜单
			List<Menu> list = service.queryMenusByRole(roleid);
			return list;
		} else {
			// 查所有菜单
			List<Menu> list = service.queryMenusByCurrentUserRole(null);
			return list;
		}
	}

	/*
	 * 列表json
	 */
	@RequestMapping("/ajax_tree")
	@ResponseBody
	public List<Menu> ajax_tree(HttpServletRequest request,
			@RequestParam(value = "pid", defaultValue = "", required = false) String pid,
			@RequestParam(value = "roleid", required = false) String roleid,
			@RequestParam(value = "check", required = false) boolean check) {

		// 查所有菜单
		List<Menu> list = service.query(null);
		return list;
	}

	/*
	 * 录入
	 */
	@RequestMapping("/input")
	public String input(HttpServletRequest request, @RequestParam(value = "id", defaultValue = "0") int id) {
		request.setAttribute("ob", service.fetch(id));
		return "views/system/input-menu";
	}

	/*
	 * 保存
	 */
	@RequestMapping("/save")
	@ResponseBody
	public String save(Menu menu, HttpServletRequest request, @RequestParam(value = "id", defaultValue = "0") long id) {
		if (id > 0) {
			service.update(menu);
		} else {
			service.insert(menu);
		}
		return "ok";
	}

	/*
	 * 删除
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public String delete(HttpServletRequest request, @RequestParam(value = "id", defaultValue = "0") int id) {
		service.delete(id);

		return "ok";
	}
}