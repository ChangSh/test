package com.test.mysys.goods.controller;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.druid.support.json.JSONUtils;
import com.test.mysys.common.utils.ImgUtils;
import com.test.mysys.common.utils.NewPager;
import com.test.mysys.common.utils.PropertiesUtil;
import com.test.mysys.common.utils.SearchFilter;
import com.test.mysys.common.utils.UserUtils;
import com.test.mysys.common.utils.web.Servlets;
import com.test.mysys.goods.model.Goods;
import com.test.mysys.goods.model.Order;
import com.test.mysys.goods.model.PicFile;
import com.test.mysys.goods.service.GoodsService;
import com.test.mysys.system.model.Code;

@Controller
@RequestMapping(value = "/goods")
public class GoodsController {
	@Autowired
	private GoodsService service;

	@RequestMapping("/ajax_list")
	@ResponseBody
	public Map<String, Object> list(HttpServletRequest request,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "pagesize", defaultValue = "10") int pageSize) throws ParseException {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);

		NewPager pager = new NewPager();
		pager.setOrder(NewPager.DESC);
		/* pager.setOrderBy(""); */
		pager.setPageNumber(page);
		pager.setPageSize(pageSize);
		pager.setFilters(filters);

		return service.queryPage(pager);
	}

	@RequestMapping("/edit")
	public String edit(HttpServletRequest request, @RequestParam(value = "id", defaultValue = "0") String id) {
		Goods g = service.fetch(id);
		request.setAttribute("ob", g);
		PicFile p = service.getPath(id);
		request.setAttribute("op", p);
		return "views/goods/goodsInput";
	}

	@RequestMapping("/delete")
	@ResponseBody
	public String delete(HttpServletRequest request, @RequestParam(value = "id", defaultValue = "0") String id) {
		service.delete(id);
		return "ok";
	}

	@RequestMapping("/save")
	@ResponseBody
	public String save(Goods entity, String img_src, HttpServletRequest request) {
		if ((entity.getId() == null) || (entity.getId() == "")) {
			service.insert(entity, img_src);
		} else {
			if (img_src == "") {
				service.updateIgnoreNull(entity);
			} else {
				PicFile p = service.getPath(entity.getId());
				p.setFilepath(img_src);
				service.updateIgnoreNull(entity);
				service.updatePic(p);
			}

		}

		return "ok";
	}

	@RequestMapping("/savedit")
	@ResponseBody
	public String savedit(Goods entity, HttpServletRequest request) {
		service.updateIgnoreNull(entity);
		return "ok";
	}

	/*
	 * 留言
	 */
	@RequestMapping("/message")
	@ResponseBody
	public String msg(@RequestParam(value = "msg") String ln, HttpServletRequest request) {
		String yzcode = request.getParameter("code");
		if (!(yzcode.equalsIgnoreCase(request.getSession().getAttribute("code").toString()))) {
			return "codeFail";
		} else {
			service.msg(ln);

			return "ok";
		}

	}

	@RequestMapping("/detail")
	public String detail(HttpServletRequest request, @RequestParam(value = "id", defaultValue = "0") String id) {
		Goods g = service.fetch(id);
		PicFile p = service.getPath(id);
		request.setAttribute("ob", g);
		request.setAttribute("op", p);
		return "views/goods/goodsInfoDetail";
	}

	@RequestMapping("/fenlei")
	@ResponseBody
	public String fenlei(HttpServletRequest request, Code code) {
		String result = service.fenlei(code);
		if (result.equals("ok")) {
			return "ok";
		} else
			return "error";

	}

	@RequestMapping("/jiesuan")
	@ResponseBody
	public String jiesuan(HttpServletRequest request, String[] id, String[] count, String[] cartId) {
		List<Order> l = new ArrayList<Order>();
		for (int i = 0; i < id.length; i++) {
			Goods g = new Goods();
			g = service.fetch(id[i]);
			Order order = new Order();
			order.setGid(id[i]);
			order.setLoginname(UserUtils.getUser().getLoginname());
			order.setOrdernum(UUID.randomUUID().toString().substring(0, 8));
			order.setId(UUID.randomUUID().toString());
			order.setStatu(-1);
			order.setUnitprice(g.getGunitprice());
			l.add(order);

		}

		service.jiesuan(l, cartId);

		return "ok";
	}

	// 上传头像
	@RequestMapping("/upload")
	@ResponseBody
	public String upload(Goods goods, HttpServletRequest request) throws IOException {
		// String fid = request.getParameter("file");
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile file = multipartRequest.getFile("file");
		String fileName = file.getOriginalFilename();
		String suffix = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf("."), fileName.length())
				: null;
		String ctxPath = PropertiesUtil.getString("uploadFilePath"); // 当前路径
		String uuid = UUID.randomUUID().toString();
		InputStream stream = file.getInputStream();
		ImgUtils.uploadImg(stream, ctxPath + uuid + suffix);
		Map<String, String> map = new HashMap<String, String>();
		map.put("fid", "fid");
		map.put("filepath", ctxPath + uuid + suffix);
		map.put("filerealname", "/" + uuid + suffix);
		/* file.transferTo(new File(ctxPath+uuid+suffix)); */
		System.out.println(JSONUtils.toJSONString(map));
		return JSONUtils.toJSONString(map);
	}

}