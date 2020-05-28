package com.fjw.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.fjw.domain.KeyValue;

@Mapper
public interface KeyValueMapper {
	
	public void add(@Param("ka_id")Long ka_id,@Param("av_id")Long av_id);
	public List<KeyValue> list(Long ka_id);
	public List<KeyValue> query(@Param("ka_id")Long ka_id,@Param("av_id")Long av_id);
	public KeyValue get(Long id);
	public Long getByKa(@Param("ka_id")Long ka_id,@Param("av_id")Long av_id);
}
