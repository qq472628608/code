package com.fjw.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class IOUtil {
	
	
	public static void importAndExport(String source,String target) {
		BufferedReader reader = null;
		BufferedWriter writer = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(source)));
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(target)));
			String str = null;
			int count = 1;
			while((str = reader.readLine()) != null) {
				if(str.indexOf("'") != -1) {
					String text = str.substring(str.indexOf("'")+1, str.lastIndexOf("'"));
					String[] tags = text.split(",");
					for (String string : tags) {
						String sql = "insert into key_value_product (product_id,key_value_id) values ("+count+","+string+");";
						writer.write(sql+"\r\n");
					}
				}
				count++;
			}
			writer.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
