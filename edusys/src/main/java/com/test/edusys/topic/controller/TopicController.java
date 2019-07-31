package com.test.edusys.topic.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.PictureType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.Document;

import com.test.edusys.college.model.Major;
import com.test.edusys.common.utils.FileOperateUtil;
import com.test.edusys.common.utils.NewPager;
import com.test.edusys.common.utils.PropertiesUtil;
import com.test.edusys.common.utils.SearchFilter;
import com.test.edusys.common.utils.web.Servlets;
import com.test.edusys.goods.model.PicFile;
import com.test.edusys.topic.model.Topic;
import com.test.edusys.topic.service.TopicService;

@Controller
@RequestMapping(value = "/topic")
public class TopicController {
	@Autowired
	private TopicService service;

	/**
	 * 根据当前教师添加课题
	 * 
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

	@RequestMapping("/online")
	public String online(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "stuloginname", defaultValue = "0") String stuloginname) throws Throwable {
		PicFile f = service.fetchFile(stuloginname);
		final String path = PropertiesUtil.getString("uploadFilePath");
		final String file = f.getFilepath();
		InputStream input = new FileInputStream(path + file);
		HWPFDocument wordDocument = new HWPFDocument(input);
		WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(
				DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());
		wordToHtmlConverter.setPicturesManager(new PicturesManager() {
			public String savePicture(byte[] content, PictureType pictureType, String suggestedName, float widthInches,
					float heightInches) {
				return suggestedName;
			}
		});
		wordToHtmlConverter.processDocument(wordDocument);
		List<Picture> pics = wordDocument.getPicturesTable().getAllPictures();
		if (pics != null) {
			for (int i = 0; i < pics.size(); i++) {
				Picture pic = (Picture) pics.get(i);
				try {
					pic.writeImageContent(new FileOutputStream(path + pic.suggestFullFileName()));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
		Document htmlDocument = wordToHtmlConverter.getDocument();
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		DOMSource domSource = new DOMSource(htmlDocument);
		StreamResult streamResult = new StreamResult(outStream);
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer serializer = tf.newTransformer();
		serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
		serializer.setOutputProperty(OutputKeys.INDENT, "yes");
		serializer.setOutputProperty(OutputKeys.METHOD, "html");
		serializer.transform(domSource, streamResult);
		outStream.close();
		String content = new String(outStream.toByteArray());
		String realrootpath = System.getProperty("evan.webapp");
		// 通过一个随即字符串来命名文件。
		String token = UUID.randomUUID().toString().substring(0, 5);
		request.setAttribute("token", token);
		// File oldFile = new File(realrootpath + "views\\topic\\" + token +
		// ".html");
		FileUtils.writeStringToFile(new File(realrootpath, "views\\topic\\" + token + ".html"), content, "UTF-8");
		return "views/topic/online";
	}

	@RequestMapping("/download")
	public void download(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "stuloginname", defaultValue = "0") String stuloginname) {
		PicFile f = service.fetchFile(stuloginname);
		final String path = PropertiesUtil.getString("uploadFilePath");
		final String file = f.getFilepath();
		try {
			FileOperateUtil.download(request, response, path, file);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}