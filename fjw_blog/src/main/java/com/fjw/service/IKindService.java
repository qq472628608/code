package com.fjw.service;

import java.util.List;

import com.fjw.domain.Kind;

public interface IKindService {
	public Kind getKind(Integer id);
	public void addKind(Kind kind);
	public void deleteKind(Long id);
	public void updateKind(Kind kind);
	public List<Kind> list();
}
