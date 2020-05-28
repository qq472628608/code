package com.fjw.service;

import com.fjw.domain.ShoppingCar;
import com.fjw.dto.ProductDto;

public interface ICarService {
	public void save(ShoppingCar car);
	public ShoppingCar get();
	public void addProduct(ProductDto dto);
	public void deleteProduct(Long product_id);
	public void updateProduct(ProductDto dto);
	public Integer getNumber();
	public void deleteProducts(Long[] product_ids);
}
