package com.fjw.query;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class EmployeeQuery extends BaseQuery{
	private String keyword;
	private Long dept_id = -1L;
	
	public void customized() {
		if(hasLength(keyword)) {
			String condition = "(obj.name LIKE ? OR obj.email LIKE ?)";
			String key = "%"+keyword+"%";
			super.addQuery(condition,key,key);
		}
		if(dept_id > 0) {
			super.addQuery("(obj.dept.id=?)", dept_id);
		}
	}
	
	private boolean hasLength(String str) {
		return str!=null&&!"".equals(str.trim());
	}

	@Override
	public String toString() {
		return "EmployeeQuery [keyword=" + keyword + ", dept_id=" + dept_id + "]";
	}
	
}
