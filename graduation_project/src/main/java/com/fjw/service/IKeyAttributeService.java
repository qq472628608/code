package com.fjw.service;

import java.util.List;

import com.fjw.domain.KeyAttribute;
import com.fjw.dto.KeyDto;

public interface IKeyAttributeService {
	public Long save(KeyDto dto);
	public void delete(Long id);
	public KeyAttribute getKeyAttribute(Long id);
	public void updateKeyAttribute(KeyAttribute attribute);
	public List<KeyAttribute> list();
	public List<KeyAttribute> listByKind(Long kind_id,Integer begin,Integer end);
	public List<KeyAttribute> listByKindDesc(Long kind_id,Integer begin,Integer end);
	public KeyAttribute queryByName(String name);
	public List<KeyAttribute> listNewest(Integer number);
	public List<KeyAttribute> listByPrice(Long kind_id,Integer number,String tag);
	public List<KeyAttribute> listByName(String name);
}