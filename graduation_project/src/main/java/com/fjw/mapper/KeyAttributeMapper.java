package com.fjw.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.fjw.domain.KeyAttribute;

@Mapper
public interface KeyAttributeMapper {
	public void saveKeyAttribute(KeyAttribute keyAttribute);
	public void deleteKeyAttribute(Long id);
	public KeyAttribute getKeyAttribute(Long id);
	public KeyAttribute queryByName(String name);
	public void updateKeyAttribute(KeyAttribute keyAttribute);
	public List<KeyAttribute> list();
	public List<KeyAttribute> listByKind(@Param("kind_id")Long kind_id,@Param("begin")Integer begin,@Param("end")Integer end);
	public List<KeyAttribute> listByKindDesc(@Param("kind_id")Long kind_id,@Param("begin")Integer begin,@Param("end")Integer end);
	public List<KeyAttribute> listNewest(Integer number);
	public List<KeyAttribute> listOrderByPrice(@Param("kind_id")Long kind_id,@Param("number")Integer number,@Param("tag")String tag);
	public List<KeyAttribute> listByName(String name);
}
