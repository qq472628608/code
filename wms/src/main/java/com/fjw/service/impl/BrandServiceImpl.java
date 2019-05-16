package com.fjw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fjw.dao.IBrandDAO;
import com.fjw.domain.Brand;
import com.fjw.query.BaseQuery;
import com.fjw.query.PageQuery;
import com.fjw.service.IBrandService;

import lombok.Setter;

public class BrandServiceImpl implements IBrandService{
	@Setter@Autowired
	private IBrandDAO brandDAO;
	public void save(Brand brand) {
		brandDAO.save(brand);
	}

	public void update(Brand brand) {
		brandDAO.update(brand);
	}

	public void delete(Long id) {
		brandDAO.delete(id);
	}

	public Brand get(Long id) {
		return brandDAO.get(id);
	}

	public List<Brand> list() {
		return brandDAO.list();
	}

	public PageQuery query(BaseQuery bq) {
		return brandDAO.query(bq);
	}

	public void bathDelete(List<Long> ids) {
		brandDAO.bathDelete(ids);
	}

}
