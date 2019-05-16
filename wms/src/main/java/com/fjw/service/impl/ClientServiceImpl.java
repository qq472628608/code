package com.fjw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fjw.dao.IClientDAO;
import com.fjw.domain.Client;
import com.fjw.query.ClientQuery;
import com.fjw.query.PageQuery;
import com.fjw.service.IClientService;

import lombok.Setter;

public class ClientServiceImpl implements IClientService{
	@Setter@Autowired
	private IClientDAO clientDAO;
	public void save(Client client) {
		clientDAO.save(client);
	}

	public void update(Client client) {
		clientDAO.update(client);
	}

	public void delete(Long id) {
		clientDAO.delete(id);
	}

	public Client get(Long id) {
		return clientDAO.get(id);
	}

	public List<Client> list() {
		return clientDAO.list();
	}
	
	public PageQuery query(ClientQuery bq) {
		return clientDAO.query(bq);
	}

}
