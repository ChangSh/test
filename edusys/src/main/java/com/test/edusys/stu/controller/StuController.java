package com.test.edusys.stu.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.w3c.dom.Document;

import com.alibaba.druid.support.json.JSONUtils;
import com.test.edusys.college.model.College;
import com.test.edusys.college.model.Major;
import com.test.edusys.college.service.CollegeMajorService;
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
	public String upload(HttpServletRequest request) throws IOException, Exception {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile file = multipartRequest.getFile("file");
		String fileName = file.getOriginalFilename();
		String filename1 = fileName.indexOf(".") != -1 ? fileName.substring(0, fileName.lastIndexOf(".")) : null;
		String ctxPath = PropertiesUtil.getString("uploadFilePath"); // 当前路径
		InputStream stream = file.getInputStream();
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		// 创建一个Buffer字符串
		byte[] buffer = new byte[1024];
		// 每次读取的字符串长度，如果为-1，代表全部读取完毕
		int len = 0;
		// 使用一个输入流从buffer里把数据读取出来
		while ((len = stream.read(buffer)) != -1) {
			// 用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
			outStream.write(buffer, 0, len);
		}
		// 关闭输入流
		stream.close();
		// 把outStream里的数据写入内存

		// 得到图片的二进制数据，以二进制封装得到数据，具有通用性
		byte[] data = outStream.toByteArray();
		// new一个文件对象用来保存图片，默认保存当前工程根目录
		File imageFile = new File(ctxPath + fileName);
		// 创建输出流
		FileOutputStream fileOutStream = new FileOutputStream(imageFile);
		// 写入数据
		fileOutStream.write(data);
		fileOutStream.close();
		Map<String, String> map = new HashMap<String, String>();
		map.put("fid", "fid");
		map.put("filepath", ctxPath + fileName);
		map.put("filerealname", filename1);
		String loginname = UserUtils.getUser().getLoginname();
		PicFile pf = new PicFile();
		pf.setFileid(loginname);
		pf.setFilepath(fileName);
		service.insertUpload(pf);

		InputStream input = new FileInputStream(ctxPath + fileName);
		// 转换成html
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
					pic.writeImageContent(new FileOutputStream(ctxPath + pic.suggestFullFileName()));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
		Document htmlDocument = wordToHtmlConverter.getDocument();
		ByteArrayOutputStream bOutStream = new ByteArrayOutputStream();
		DOMSource domSource = new DOMSource(htmlDocument);
		StreamResult streamResult = new StreamResult(bOutStream);
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer serializer = tf.newTransformer();
		serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
		serializer.setOutputProperty(OutputKeys.INDENT, "yes");
		serializer.setOutputProperty(OutputKeys.METHOD, "html");
		serializer.transform(domSource, streamResult);
		bOutStream.close();
		String content = new String(bOutStream.toByteArray());
		FileUtils.writeStringToFile(new File(ctxPath, filename1 + ".html"), content, "UTF-8");
		System.out.println(JSONUtils.toJSONString(map));
		return JSONUtils.toJSONString(map);
	}
}