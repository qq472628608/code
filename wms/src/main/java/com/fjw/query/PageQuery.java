package com.fjw.query;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
@Getter@Setter
public class PageQuery {
	private Integer currentPage = 1;
	private Integer pageSize = 7;
	
	private Integer prePage;
	private Integer nextPage;
	private Integer totalPage;
	
	private Long totalCount;
	private List queryResult;
	
	public PageQuery(Long totalCount,List queryResult,Integer currentPage,Integer pageSize) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		this.queryResult = queryResult;
		
		totalPage = (int) (totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1);
		prePage = currentPage-1==0?1:currentPage-1;
		nextPage = currentPage+1==totalPage?totalPage:currentPage+1;
	}
}
