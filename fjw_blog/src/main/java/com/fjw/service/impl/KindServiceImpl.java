package com.fjw.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fjw.domain.Kind;
import com.fjw.log.LogAnnotation;
import com.fjw.mapper.KindMapper;
import com.fjw.service.IKindService;

import lombok.Setter;

@Service
public class KindServiceImpl implements IKindService{
	@Setter@Autowired
	private KindMapper kindDAO;
	
	public Kind getKind(Integer id) {
		return kindDAO.get(id);
	}

	public void addKind(Kind kind) {
		kindDAO.save(kind);
	}

	public void deleteKind(Long id) {
		kindDAO.delete(id);
	}

	public void updateKind(Kind kind) {
		kindDAO.update(kind);
	}

	public List<Kind> list() {
		return kindDAO.list();
	}

}
