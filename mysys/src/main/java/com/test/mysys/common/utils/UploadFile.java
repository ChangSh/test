package com.test.mysys.common.utils;

import java.io.File;

import org.apache.commons.io.FileUtils;

public class UploadFile {

	public static File Upload(File uploadFile, String uploadFileFileName, String targetDirectory) throws Exception {
		File target = new File(targetDirectory, uploadFileFileName);
		// 如果文件父目录不存在则创建
		if (!target.getParentFile().exists())
			target.getParentFile().mkdirs();
		// 如果文件已经存在，则删除原有文件
		if (target.exists()) {
			target.delete();
		}
		// 复制file对象,上传
		FileUtils.copyFile(uploadFile, target);
		return target;
	}
}
