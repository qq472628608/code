package com.fjw.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.fjw.domain.Collect;

@Mapper
public interface CollectMapper {
	public void collectAdd(@Param("user_id")Long user_id,@Param("product_id")Long product_id);
	public void collectDelete(@Param("id")Long id,@Param("user_id")Long user_id);
	public List<Collect> list(Long user_id);
	public Collect get(@Param("user_id")Long user_id,@Param("product_id")Long product_id);
}
