package com.test.edusys.college.controller;

import java.text.ParseException;
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

import com.test.edusys.college.model.College;
import com.test.edusys.college.model.Major;
import com.test.edusys.college.service.CollegeMajorService;
import com.test.edusys.common.utils.NewPager;
import com.test.edusys.common.utils.SearchFilter;
import com.test.edusys.common.utils.web.Servlets;
import com.test.edusys.customer.model.Customer;

@Controller
@RequestMapping(value = "/college")
public class CollegeMajorController {
	@Autowired
	private CollegeMajorService service;

	@RequestMapping("/college_list")
	@ResponseBody
	public Map<String, Object> college_list(HttpServletRequest request,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "pagesize", defaultValue = "10") int pageSize) throws ParseException {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);

		NewPager pager = new NewPager();
		pager.setOrderBy("college");
		pager.setOrder(NewPager.ASC);
		pager.setPageNumber(page);
		pager.setPageSize(pageSize);
		pager.setFilters(filters);

		return this.service.queryCollegePage(pager);
	}

	@RequestMapping("/major_list")
	@ResponseBody
	public Map<String, Object> major_list(HttpServletRequest request,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "pagesize", defaultValue = "10") int pageSize) throws ParseException {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		NewPager pager = new NewPager();
		pager.setOrder(NewPager.ASC);
		pager.setOrderBy("major");
		pager.setPageNumber(page);
		pager.setPageSize(pageSize);
		pager.setFilters(filters);

		return this.service.queryMajorPage(pager);
	}

	@RequestMapping("/collegeInput")
	public String collegeInput(HttpServletRequest request, @RequestParam(value = "id", defaultValue = "0") String id) {
		College c = service.fetchCollege(id);
		request.setAttribute("ob", c);
		return "views/college/collegeInput";
	}

	@RequestMapping("/majorInput")
	public String majorInput(HttpServletRequest request, @RequestParam(value = "id", defaultValue = "0") String id) {
		Major c = service.fetchMajor(id);
		request.setAttribute("ob", c);
		return "views/college/majorInput";
	}

	@RequestMapping("/edit")
	public String edit(HttpServletRequest request, @RequestParam(value = "id", defaultValue = "0") String id) {

		Customer customer = new Customer();
		// customer = service.fetch(id);
		request.setAttribute("ob", customer);
		return "views/customer/Edit";
	}

	@RequestMapping("/deleteCollege")
	@ResponseBody
	public String deleteCollege(HttpServletRequest request, @RequestParam(value = "id", defaultValue = "0") String id) {
		service.deleteCollege(id);
		return "ok";
	}

	@RequestMapping("/deleteMajor")
	@ResponseBody
	public String deleteMajor(HttpServletRequest request, @RequestParam(value = "id", defaultValue = "0") String id) {
		service.deleteMajor(id);
		return "ok";
	}

	@RequestMapping("/savecollege")
	@ResponseBody
	public String savecollege(College college, HttpServletRequest request) {
		if (college.getId().equals("") || college.getId() == null) {
			college.setId(UUID.randomUUID().toString());
			this.service.insert(college);
		} else {
			service.updateIgnoreNullCollege(college);
		}
		return "ok";
	}

	@RequestMapping("/savemajor")
	@ResponseBody
	public String savemajor(Major major, HttpServletRequest request) {
		if (major.getId().equals("") || major.getId() == null) {
			this.service.insertMajor(major);
		} else {
			service.updateIgnoreNullMajor(major);
		}
		return "ok";
	}

	@RequestMapping("/collegeDetail")
	public String collegeDetail(HttpServletRequest request, @RequestParam(value = "id", defaultValue = "0") String id) {
		College c = new College();
		c = service.fetchCollege(id);
		request.setAttribute("ob", c);
		return "views/college/collegeDetail";
	}

	@RequestMapping("/majorDetail")
	public String majorDetail(HttpServletRequest request, @RequestParam(value = "id", defaultValue = "0") String id) {
		Major c = new Major();
		c = service.fetchMajor(id);
		request.setAttribute("ob", c);
		return "views/college/majorDetail";
	}
	
	@RequestMapping("/majorByCollegeId")
	@ResponseBody
	public Map<String, Object> majorByCollegeId(HttpServletRequest request, @RequestParam(value = "collegeId", defaultValue = "0") String collegeId) {
		List<Major> list = service.queryMajorByCollegeId(collegeId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		return map;
	}

}