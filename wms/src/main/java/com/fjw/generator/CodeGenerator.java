package com.fjw.generator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.MessageFormat;

import com.fjw.domain.Department;
import com.fjw.domain.Employee;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class CodeGenerator {
	private static Configuration config;
	static {
		config = new Configuration(config.VERSION_2_3_28);
		try {
			config.setDirectoryForTemplateLoading(new File("template"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		/*createCode(Employee.class, "DaoTemplate", "src/main/java/{0}/dao/I{1}DAO");//自动生成IEmployeeDAO
		createCode(Employee.class, "DaoTemplate", "src/main/java/{0}/dao/impl/{1}DAOImpl");//自动生成EmployeeDAOImpl*/
		
	}
	
	private static void createCode(Class<?> clz,String templateName,String path) {
		try {
			Template template = config.getTemplate(templateName);
			ClassInfo classInfo = new ClassInfo(clz);
			MessageFormat.format(path, clz.getPackage().getName().replace('.', '/'),clz.getSimpleName());
			template.process(classInfo, new FileWriter(path));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
