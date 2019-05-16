package com.fjw.service;

import java.util.List;

import com.fjw.domain.Depot;
import com.fjw.query.BaseQuery;
import com.fjw.query.PageQuery;

public interface IDepotService {
	public void save(Depot depot);
	public void update(Depot depot);
	public void delete(Long id);
	public Depot get(Long id);
	public List<Depot> list();
	public PageQuery query(BaseQuery bq);
}
