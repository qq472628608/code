package com.fjw.service;

import java.util.List;

import com.fjw.domain.KeyValue;

public interface IKeyValueService {
	public void add(Long ka_id,Long av_id);
	public List<KeyValue> list(Long ka_id);
	public List<KeyValue> query(Long ka_id,Long av_id);
	public KeyValue get(Long id);
	public Long get(Long ka_id,Long av_id);
}
