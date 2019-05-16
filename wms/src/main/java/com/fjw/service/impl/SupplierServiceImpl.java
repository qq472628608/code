package com.fjw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fjw.dao.ISupplierDAO;
import com.fjw.domain.Supplier;
import com.fjw.query.BaseQuery;
import com.fjw.query.PageQuery;
import com.fjw.service.ISupplierService;

import lombok.Setter;

public class SupplierServiceImpl implements ISupplierService{
	@Setter@Autowired
	private ISupplierDAO supplierDAO;
	public void save(Supplier supplier) {
		supplierDAO.save(supplier);
	}

	public void update(Supplier supplier) {
		supplierDAO.update(supplier);
	}

	public void delete(Long id) {
		supplierDAO.delete(id);
	}

	public Supplier get(Long id) {
		return supplierDAO.get(id);
	}

	public List<Supplier> list() {
		return supplierDAO.list();
	}

	public PageQuery query(BaseQuery bq) {
		return supplierDAO.query(bq);
	}

	public void bathDelete(List<Long> ids) {
		supplierDAO.bathDelete(ids);
	}

}
