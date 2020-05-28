package com.fjw.domain.query;

import java.io.Serializable;
import java.util.List;


import lombok.Data;

@Data
public class PageResult<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer count;
	private Integer totalPage;
	private List<T> result;
	private Integer current;
	
	public PageResult (List<T> result,Integer count,Integer size,Integer current){
		this.count = count;
		this.totalPage = count % size == 0?(int)(count/size):(int)(count/size+1);
		this.result = result;
		this.current = current;
	}
}
