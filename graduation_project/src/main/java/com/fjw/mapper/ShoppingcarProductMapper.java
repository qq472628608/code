package com.fjw.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.fjw.domain.ShoppingcarProduct;

@Mapper
public interface ShoppingcarProductMapper {
	public void saveCarProduct(ShoppingcarProduct cp);
	public void deleteCarProduct(@Param("car_id")Long car_id,@Param("product_id")Long product_id);
	public ShoppingcarProduct getCarProduct(@Param("car_id")Long car_id,@Param("product_id")Long product_id);
	public void updateCarProduct(ShoppingcarProduct cp);
	public List<ShoppingcarProduct> list(Long car_id);
	public void batchDelete(@Param("ids")Long[] ids,@Param("car_id")Long car_id);
}
