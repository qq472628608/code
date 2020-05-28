package com.fjw.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.fjw.domain.KeyValueProduct;

@Mapper
public interface KeyValueProductMapper {
	
	public void add(@Param("product_id")Long product_id,@Param("key_value_id")Long key_value_id);
	
	public List<KeyValueProduct> list(Long product_id);
	
	public KeyValueProduct get(Long id);
	
	public void delete(Long id);
	
}
