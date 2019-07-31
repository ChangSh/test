package com.test.edusys.input.controller;

import java.text.ParseException;
import java.util.HashMap;
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
import com.test.edusys.input.model.CargoChild;
import com.test.edusys.input.model.CargoParent;
import com.test.edusys.input.service.InputService;

@Controller
@RequestMapping(value = "/input")
public class InputController {
	@Autowired
	private InputService service;

	// 新增一次进货（parent）
	@RequestMapping("/newInput")
	public String newInput(HttpServletRequest request) {
		CargoParent cargo = service.insert();
		request.setAttribute("ob", cargo);
		return "views/input/inputNew";
	}

	// 点击行新增
	@RequestMapping("/input")
	public String input(HttpServletRequest request, String id) {
		CargoParent cargo = service.fetchCargo(id);
		request.setAttribute("ob", cargo);
		return "views/input/inputNew";
	}

	/**
	 * 
	 * @param request
	 * @param pid
	 *            主表id
	 * @param goodsid
	 *            商品id
	 * @param amount
	 *            商品数量
	 * @param inputprice
	 *            商品进价
	 * @return
	 */
	@RequestMapping("/addGoods")
	@ResponseBody
	public Map<String, Object> addGoods(HttpServletRequest request,
			@RequestParam(value = "pid", defaultValue = "1") String pid,
			@RequestParam(value = "cid", defaultValue = "0") String goodsid,
			@RequestParam(value = "jj", defaultValue = "0") double inputprice,
			@RequestParam(value = "amount", defaultValue = "0") int amount) {

		CargoChild cargo_c = new CargoChild();

		cargo_c.setCargoamount(amount);
		cargo_c.setCargounitprice(inputprice);
		cargo_c.setPid(pid);
		cargo_c.setGid(goodsid);
		service.insertChild(cargo_c);
		service.sumTotal(pid, amount, inputprice);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("info", "添加成功");

		return map;
	}

	// 显示子表的信息
	@RequestMapping("/ajax_ChildList")
	@ResponseBody
	public Map<String, Object> listDetail(HttpServletRequest request,
			@RequestParam(value = "pid", defaultValue = "1") String pid,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "pagesize", defaultValue = "10") int pageSize) throws ParseException {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);

		NewPager pager = new NewPager();
		pager.setOrder(NewPager.DESC);
		/* pager.setOrderBy("cargotime"); */
		pager.setPageNumber(page);
		pager.setPageSize(pageSize);
		pager.setFilters(filters);

		return service.queryDetail(pager, pid);
	}

	// 显示主表的信息
	@RequestMapping("/ajax_ParentList")
	@ResponseBody
	public Map<String, Object> list(HttpServletRequest request,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "pagesize", defaultValue = "10") int pageSize) throws ParseException {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);

		NewPager pager = new NewPager();
		pager.setOrder(NewPager.DESC);
		pager.setOrderBy("cargotime");
		pager.setPageNumber(page);
		pager.setPageSize(pageSize);
		pager.setFilters(filters);

		return service.queryPage(pager);
	}

	@RequestMapping("/delete")
	@ResponseBody
	public String delete(HttpServletRequest request, @RequestParam(value = "id", defaultValue = "0") String id) {
		service.delete(id);
		return "ok";
	}

	@RequestMapping("/remove")
	@ResponseBody
	public String remove(HttpServletRequest request, @RequestParam(value = "id", defaultValue = "0") String id) {
		service.remove(id);
		return "ok";
	}

}