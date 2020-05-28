package com.fjw.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.fjw.domain.AttributeValue;

@Mapper
public interface AttributeValueMapper {
	public void saveAttributeValue(AttributeValue attributeValue);
	public void deleteAttributeValue(Long id);
	public AttributeValue getAttributeValue(Long id);
	public void updateAttributeValue(AttributeValue attributeValue);
	public List<AttributeValue> list(Long saleAttribute_id);
}
