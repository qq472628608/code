package com.fjw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fjw.annotation.LogAnnotation;
import com.fjw.domain.ShoppingCar;
import com.fjw.domain.ShoppingcarProduct;
import com.fjw.domain.User;
import com.fjw.dto.ProductDto;
import com.fjw.mapper.ShoppingCarMapper;
import com.fjw.mapper.ShoppingcarProductMapper;
import com.fjw.service.ICarService;
import com.fjw.service.IProductService;
import com.fjw.util.ContextUserUtil;
import com.fjw.util.ThreadLocalHandler;

import lombok.Setter;

@Service
public class CarServiceImpl implements ICarService {

	@Setter@Autowired@Lazy
	private ShoppingCarMapper carMapper;
	@Setter@Autowired@Lazy
	private ShoppingcarProductMapper cpMapper;
	@Setter@Autowired@Lazy
	private IProductService productService;
	
	@CacheEvict(value="car",allEntries=true)
	@Transactional(rollbackFor= {Exception.class})
	@LogAnnotation(description="添加购物车")
	public void save(ShoppingCar car) {
		carMapper.saveCar(car);
	}
	
	@Cacheable(value="car")
	@LogAnnotation(description="查询购物车信息")
	public ShoppingCar get() {
		User user = ContextUserUtil.getCurrentUser(ThreadLocalHandler.getLocalRequest());
		return carMapper.getByUser(user.getId());
	}
	
	@CacheEvict(value="car",allEntries=true)
	@Transactional(rollbackFor= {Exception.class})
	@LogAnnotation(description="购物车添加商品")
	public void addProduct(ProductDto dto) {
		ShoppingcarProduct sqlCp = cpMapper.getCarProduct(get().getId(), dto.getProduct_id());
		if(sqlCp == null) {
			ShoppingcarProduct cp = new ShoppingcarProduct();
			cp.setBuyNumber(dto.getNumber());
			cp.setProduct(productService.getProduct(dto.getProduct_id()));
			cp.setCar_id(get().getId());
			cpMapper.saveCarProduct(cp);
		}else {
			Integer number = sqlCp.getBuyNumber();
			sqlCp.setBuyNumber(++number);
			cpMapper.updateCarProduct(sqlCp);
		}
	}
	
	@CacheEvict(value="car",allEntries=true)
	@Transactional(rollbackFor= {Exception.class})
	@LogAnnotation(description="购物车删除商品")
	public void deleteProduct(Long product_id) {
		cpMapper.deleteCarProduct(get().getId(),product_id);
	}
	
	@CacheEvict(value="car",allEntries=true)
	@Transactional(rollbackFor= {Exception.class})
	@LogAnnotation(description="购物车删除多个商品")
	public void deleteProducts(Long[] product_ids) {
		cpMapper.batchDelete(product_ids, get().getId());
	}
	
	@CacheEvict(value="car",allEntries=true)
	@Transactional(rollbackFor= {Exception.class})
	@LogAnnotation(description="更新购物车商品个数")
	public void updateProduct(ProductDto dto) {
		ShoppingcarProduct cp = cpMapper.getCarProduct(get().getId(), dto.getProduct_id());
		cp.setBuyNumber(dto.getNumber());
		cpMapper.updateCarProduct(cp);
	}
	
	@Cacheable(value="car")
	@LogAnnotation(description="查询省区")
	public Integer getNumber() {
		List<ShoppingcarProduct> products = get().getCarProducts();
		Integer number = 0;
		for (ShoppingcarProduct product : products) {
			number += product.getBuyNumber();
		}
		return number;
	}
}
