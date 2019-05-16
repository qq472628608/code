package com.fjw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fjw.dao.IDepotDAO;
import com.fjw.domain.Depot;
import com.fjw.query.BaseQuery;
import com.fjw.query.PageQuery;
import com.fjw.service.IDepotService;

import lombok.Setter;

public class DepotServiceImpl implements IDepotService{
	@Setter@Autowired
	private IDepotDAO depotDAO;
	public void save(Depot depot) {
		depotDAO.save(depot);
	}

	public void update(Depot depot) {
		depotDAO.update(depot);
	}

	public void delete(Long id) {
		depotDAO.delete(id);
	}

	public Depot get(Long id) {
		return depotDAO.get(id);
	}

	public List<Depot> list() {
		return depotDAO.list();
	}

	public PageQuery query(BaseQuery bq) {
		return depotDAO.query(bq);
	}

}
