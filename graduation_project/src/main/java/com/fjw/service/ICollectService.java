package com.fjw.service;

import java.util.List;

import com.fjw.domain.Collect;

public interface ICollectService {
	public void add(Long product_id) throws Exception;
	public List<Collect> delete(Long id);
	public List<Collect> list();
}
