package com.fjw.util;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;

public class FileUtil {
	public static void upload(InputStream in,String oldName,HttpServletRequest request) {
		HttpSession session = request.getSession();
		String name = UUID.randomUUID().toString();
		String suffix = oldName.substring(oldName.lastIndexOf("."));
		String fileName = name+suffix;
		String path = session.getServletContext().getRealPath("/upload/"+fileName);
		try {
			OutputStream out = new FileOutputStream(path);
			IOUtils.copy(in, out);
			session.setAttribute("IMAGE_NAME", fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
