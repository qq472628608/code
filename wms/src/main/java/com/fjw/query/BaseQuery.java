package com.fjw.query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import lombok.Getter;
import lombok.Setter;

public class BaseQuery {
	@Getter@Setter
	private Integer currentPage = 1;
	@Getter@Setter
	private Integer pageSize = 7;
	
	private List<Object> params = new ArrayList<>();
	private List<String> conditions = new ArrayList<>();
	private boolean init = false;
	
	public void init() {
		if(init == false) {
			customized();
			this.init = true;
		}
	}
	public String getQuery() {
		init();
		if(conditions.size() == 0) {
			return "";
		}
		return " WHERE "+StringUtils.join(conditions, " AND ");
	}
	
	protected void customized() {}
	protected void addQuery(String condition,Object... param) {
		this.params.addAll(Arrays.asList(param));
		this.conditions.add(condition);
	}
	protected void addCondition(String condition) {
		this.conditions.add(condition);
	}
	@Override
	public String toString() {
		return "BaseQuery [currentPage=" + currentPage + ", pageSize=" + pageSize + "]";
	}
	public List<Object> getParams() {
		return params;
	}
	public void setParams(List<Object> params) {
		this.params = params;
	}
	public List<String> getConditions() {
		return conditions;
	}
	public void setConditions(List<String> conditions) {
		this.conditions = conditions;
	}
	
}
