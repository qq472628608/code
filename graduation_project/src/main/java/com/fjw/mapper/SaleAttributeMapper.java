package com.fjw.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.fjw.domain.SaleAttribute;

@Mapper
public interface SaleAttributeMapper {
	public void saveSaleAttribute(SaleAttribute saleAttribute);
	public void deleteSaleAttribute(Long id);
	public SaleAttribute getSaleAttribute(Long id);
	public void updateSaleAttirbute(SaleAttribute saleAttribute);
	public List<SaleAttribute> list(@Param("kind_id")Long kind_id);
	public List<SaleAttribute> listAll();
}
