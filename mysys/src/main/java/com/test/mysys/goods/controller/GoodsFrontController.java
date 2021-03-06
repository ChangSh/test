package com.test.mysys.goods.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.mysys.common.utils.NewPager;
import com.test.mysys.common.utils.SearchFilter;
import com.test.mysys.common.utils.web.Servlets;
import com.test.mysys.goods.model.Goods;
import com.test.mysys.goods.model.GoodsDetailVo;
import com.test.mysys.goods.service.GoodsFrontService;
import com.test.mysys.goods.service.GoodsService;

@Controller
@RequestMapping(value = "/goodsFront")
public class GoodsFrontController {
	@Autowired
	private GoodsFrontService service;
	@Autowired
	private GoodsService goodsService;

	@RequestMapping("/cart_list")
	@ResponseBody
	public Map<String, Object> cart_list() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cart", service.findCart());
		return map;

	}

	@RequestMapping("/order_list")
	@ResponseBody
	public Map<String, Object> order_list() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("order", service.findOrder());
		return map;

	}

	@RequestMapping("/house_list")
	@ResponseBody
	public Map<String, Object> house_list() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("house", service.findHouse());
		return map;

	}

	@RequestMapping("/ajax_listfocus")
	@ResponseBody
	public Map<String, Object> findFocus() {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("focus", service.findFocus());
		return map;

	}

	@RequestMapping("/ajax_listIndex")
	@ResponseBody
	public Map<String, Object> pagination(@RequestParam(value = "pageIndex", defaultValue = "0") int pageIndex,
			@RequestParam(value = "pageSize", defaultValue = "15") int pageSize, HttpServletRequest request) {

		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);

		NewPager pager = new NewPager();
		pager.setOrder(NewPager.DESC);
		/* pager.setOrderBy(""); */
		pager.setPageNumber(pageIndex + 1);
		pager.setPageSize(pageSize);
		pager.setFilters(filters);
		return service.findIndex(pager);
	}

	@RequestMapping("/fetchDetail")
	public String fetchDetail(HttpServletRequest request, String goodsid) {

		Goods g = goodsService.fetch(goodsid);
		if (g.getGclick() == null) {
			g.setGclick(1);
		} else {
			g.setGclick(g.getGclick() + 1);
		}
		goodsService.updateIgnoreNull(g);
		GoodsDetailVo gdv = service.fetchDetail(goodsid);
		request.setAttribute("ob", gdv);

		return "/views/front/goodsDetail";
	}

	@RequestMapping("/ajax_listFenlei")
	@ResponseBody
	public Map<String, Object> pagination_fenlei(@RequestParam(value = "pageIndex", defaultValue = "0") int pageIndex,
			@RequestParam(value = "pageSize", defaultValue = "15") int pageSize, String code,
			HttpServletRequest request) {

		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);

		NewPager pager = new NewPager();
		pager.setOrder(NewPager.DESC);
		/* pager.setOrderBy(""); */
		pager.setPageNumber(pageIndex + 1);
		pager.setPageSize(pageSize);
		pager.setFilters(filters);
		return service.fenlei_Index(pager, code);
	}

	/**
	 * 添加购物车
	 * 
	 * @param request
	 * @param goodsid
	 * @return
	 */
	@RequestMapping("/addCart")
	public String addCart(HttpServletRequest request, String id) {

		goodsService.addCart(id);

		return "/views/front/buycart";

	}

	@RequestMapping("/removeCart")
	public String removeCart(HttpServletRequest request, String cartId) {

		goodsService.removeCart(cartId);

		return "/views/front/buycart";

	}

	@RequestMapping("/change")
	public String change(HttpServletRequest request, String id) {

		goodsService.change(id);

		return "/views/front/order";

	}

	@RequestMapping("/guess")
	@ResponseBody
	public Map<String, Object> guess(HttpServletRequest request) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("guess", service.guess());
		return map;
	}

	@RequestMapping("/find")
	public String find(HttpServletRequest request, String content) {
		if (content.equals("")) {
			return "/views/front/index";
		} else {
			GoodsDetailVo gdv = service.find(content);
			if (gdv != null) {
				request.setAttribute("ob", gdv);
				return "/views/front/goodsDetail";
			} else
				return "/views/front/index";
		}
	}

	@RequestMapping("/fenlei_list")
	@ResponseBody
	public Map<String, Object> fenlei_list(HttpServletRequest request) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("fenleilist", service.fenleiList());
		return map;

	}

	@RequestMapping("/book")
	@ResponseBody
	public String book(HttpServletRequest request, @RequestParam(value = "id", defaultValue = "0") String id) {
		goodsService.updateStatus(id, 2);
		return "ok";
	}
}