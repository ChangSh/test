package com.test.edusys.topic.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.edusys.college.model.Major;
import com.test.edusys.common.utils.NewPager;
import com.test.edusys.common.utils.SearchFilter;
import com.test.edusys.common.utils.web.Servlets;
import com.test.edusys.topic.model.Topic;
import com.test.edusys.topic.service.TopicService;

@Controller
@RequestMapping(value = "/topic")
public class TopicController {
	@Autowired
	private TopicService service;

	/**
	 * 根据当前教师添加课题
	 * @param request
	 * @param page
	 * @param pageSize
	 * @return
	 * @throws ParseException
	 */
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

	@RequestMapping("/topicInput")
	public String topicInput(HttpServletRequest request, @RequestParam(value = "id", defaultValue = "0") String id) {
		Topic c = service.fetchTopic(id);
		request.setAttribute("ob", c);
		return "views/topic/topicInput";
	}

	@RequestMapping("/majorInput")
	public String majorInput(HttpServletRequest request, @RequestParam(value = "id", defaultValue = "0") String id) {
		Major c = service.fetchMajor(id);
		request.setAttribute("ob", c);
		return "views/college/majorInput";
	}

	@RequestMapping("/deleteTopic")
	@ResponseBody
	public String deleteTopic(HttpServletRequest request, @RequestParam(value = "id", defaultValue = "0") String id) {
		service.deleteTopic(id);
		return "ok";
	}

	@RequestMapping("/deleteMajor")
	@ResponseBody
	public String deleteMajor(HttpServletRequest request, @RequestParam(value = "id", defaultValue = "0") String id) {
		service.deleteMajor(id);
		return "ok";
	}

	@RequestMapping("/saveTopic")
	@ResponseBody
	public String saveTopic(Topic topic, HttpServletRequest request) {
		if (topic.getId().equals("") || topic.getId() == null) {
			this.service.insertTopic(topic);
		} else {
			service.updateIgnoreNull(topic);
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

	@RequestMapping("/topicDetail")
	public String collegeDetail(HttpServletRequest request, @RequestParam(value = "id", defaultValue = "0") String id) {
		Topic c = new Topic();
		c = service.fetchTopic(id);
		request.setAttribute("ob", c);
		return "views/topic/topicDetail";
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
	public Map<String, Object> majorByCollegeId(HttpServletRequest request,
			@RequestParam(value = "collegeId", defaultValue = "0") String collegeId) {
		List<Major> list = service.queryMajorByCollegeId(collegeId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		return map;
	}

}