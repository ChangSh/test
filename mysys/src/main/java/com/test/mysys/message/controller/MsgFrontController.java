package com.test.mysys.message.controller;






import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.mysys.message.service.MessageService;


@Controller
@RequestMapping(value="/msgFront")
public class MsgFrontController {
	@Autowired
	private MessageService service;

	
	
	/*
	 * 留言
	 */
	@RequestMapping("/message")
	@ResponseBody
	public String msg(@RequestParam(value="msg") String ln,
			HttpServletRequest request){
		String yzcode = request.getParameter("code");
		if (!(yzcode.equalsIgnoreCase(request.getSession().getAttribute("code").toString()))) {
			return "codeFail";
		}else{
			service.msg(ln);
			return "ok";
		}
	}
	
	
}