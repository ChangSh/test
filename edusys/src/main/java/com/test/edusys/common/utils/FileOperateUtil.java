package com.test.edusys.common.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileOperateUtil {

	public static boolean browserIsIE(String agent) {
		agent = agent.toLowerCase();
		if ((agent.indexOf("msie 7") > 0) || (agent.indexOf("msie 8") > 0) || (agent.indexOf("msie 9") > 0)
				|| (agent.indexOf("msie 10") > 0) || (agent.indexOf("msie") > 0)
				|| (agent.indexOf("gecko") > 0 && agent.indexOf("rv:11") > 0)) {
			return true;
		}
		return false;
	}

	/**
	 * 下载
	 * 
	 * @param request
	 * @param response
	 * @param storeName
	 * @param contentType
	 * @param realName
	 * @throws Exception
	 */
	public static void download(HttpServletRequest request, HttpServletResponse response, String downLoadPath,
			String realName) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String contentType = "application/octet-stream";
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;

		String agent = request.getHeader("User-Agent");
		if (browserIsIE(agent)) {
			realName = java.net.URLEncoder.encode(realName, "UTF-8");
		}

		// String ctxPath = request.getSession().getServletContext()
		// .getRealPath("/")
		// + FileOperateUtil.UPLOADDIR;
		// String downLoadPath = ctxPath + storeName;

		long fileLength = new File(downLoadPath).length();

		response.setContentType(contentType);
		response.setHeader("Content-disposition",
				"attachment; filename=" + new String(realName.getBytes("utf-8"), "ISO8859-1"));
		response.setHeader("Content-Length", String.valueOf(fileLength));

		bis = new BufferedInputStream(new FileInputStream(downLoadPath + realName + ".html"));
		bos = new BufferedOutputStream(response.getOutputStream());
		byte[] buff = new byte[2048];
		int bytesRead = 0;
		while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
			bos.write(buff, 0, bytesRead);
		}
		bis.close();
		bos.close();
	}
}
