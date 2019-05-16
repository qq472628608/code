package com.fjw.service;

import java.util.List;

import com.fjw.domain.Client;
import com.fjw.query.ClientQuery;
import com.fjw.query.PageQuery;

public interface IClientService {
	public void save(Client client);
	public void update(Client client);
	public void delete(Long id);
	public Client get(Long id);
	public List<Client> list();
	public PageQuery query(ClientQuery bq);
}
