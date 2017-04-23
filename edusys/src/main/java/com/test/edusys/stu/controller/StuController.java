package com.test.edusys.stu.controller;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.druid.support.json.JSONUtils;
import com.test.edusys.college.model.College;
import com.test.edusys.college.model.Major;
import com.test.edusys.college.service.CollegeMajorService;
import com.test.edusys.common.utils.ImgUtils;
import com.test.edusys.common.utils.NewPager;
import com.test.edusys.common.utils.PropertiesUtil;
import com.test.edusys.common.utils.SearchFilter;
import com.test.edusys.common.utils.UserUtils;
import com.test.edusys.common.utils.web.Servlets;
import com.test.edusys.customer.model.Customer;
import com.test.edusys.goods.model.PicFile;
import com.test.edusys.stu.service.StuService;

@Controller
@RequestMapping(value = "/stu")
public class StuController {
	@Autowired
	private StuService service;
	@Autowired
	private CollegeMajorService cm_service;

	// 所有未选择的课题
	@RequestMapping("/topic_list")
	@ResponseBody
	public Map<String, Object> topic_list(HttpServletRequest request,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "pagesize", defaultValue = "10") int pageSize) throws ParseException {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);

		NewPager pager = new NewPager();
		pager.setOrderBy("tname");
		pager.setOrder(NewPager.ASC);
		pager.setPageNumber(page);
		pager.setPageSize(pageSize);
		pager.setFilters(filters);

		return this.service.queryTopicPage(pager);
	}

	@RequestMapping("/stuInfo")
	@ResponseBody
	public Map<String, Object> stuInfo(HttpServletRequest request) {
		Customer c = service.fetchStuInfo();
		College college = this.cm_service.fetchCollege(c.getCollege());
		Major major = this.cm_service.fetchMajor(c.getMajor());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("stu", c);
		map.put("college", college);
		map.put("major", major);
		return map;
	}

	@RequestMapping("/checktopic")
	@ResponseBody
	public String checktopic(HttpServletRequest request, String topicid) {
		this.service.checkTopic(topicid);
		return "ok";
	}

	@RequestMapping(value = "/upload", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String upload(HttpServletRequest request) throws IOException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile file = multipartRequest.getFile("file");
		String fileName = file.getOriginalFilename();
		String filename1 = fileName.indexOf(".") != -1 ? fileName.substring(0, fileName.lastIndexOf("."))
				: null;
		String ctxPath = PropertiesUtil.getString("uploadFilePath"); // 当前路径
		InputStream stream = file.getInputStream();
		ImgUtils.uploadImg(stream, ctxPath + fileName);
		Map<String, String> map = new HashMap<String, String>();
		map.put("fid", "fid");
		map.put("filepath", ctxPath + fileName);
		map.put("filerealname", filename1);
		String loginname = UserUtils.getUser().getLoginname();
		PicFile pf = new PicFile();
		pf.setFileid(loginname);
		pf.setFilepath(fileName);
		service.insertUpload(pf);
		System.out.println(JSONUtils.toJSONString(map));
		return JSONUtils.toJSONString(map);
	}
}