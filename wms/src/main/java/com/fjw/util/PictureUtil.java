package com.fjw.util;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import net.coobird.thumbnailator.Thumbnails;

public class PictureUtil {
	public static String pictureUpload(File file,String fileName) throws IOException {
		String uuid = UUID.randomUUID().toString();
		String extend_name = fileName.substring(fileName.lastIndexOf('.'));
		String path = ServletActionContext.getServletContext().getRealPath("/upload");
		fileName = uuid+extend_name;
		File targetFile = new File(path,fileName);
		FileUtils.copyFile(file, targetFile);
		String smallPictureName = uuid+"_small"+extend_name;
		File smallFile = new File(path,smallPictureName);
		Thumbnails.of(targetFile).scale(0.1f).toFile(smallFile);//把原图缩小0.4倍转换为小图
		return "/upload/"+fileName;
	}
	
	public static void deletePicture(String savePath) {
		//删除大图
		String bigPath = ServletActionContext.getServletContext().getRealPath("/")+savePath;
		File bigFile = new File(bigPath);
		if(bigFile.exists()) {
			bigFile.delete();
		}
		//删除小图
		String smallPath = ServletActionContext.getServletContext().getRealPath("/")
				+savePath.substring(0, savePath.lastIndexOf('.'))+"_small"+
						savePath.substring(savePath.lastIndexOf('.'));
		File smallFile = new File(smallPath);
		if(smallFile.exists()) {
			smallFile.delete();
		}
	}
}
