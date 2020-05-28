package com.fjw.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.fjw.domain.ShoppingCar;

@Mapper
public interface ShoppingCarMapper {
	public void saveCar(ShoppingCar car);
	public ShoppingCar getCar(Long id);
	public ShoppingCar getByUser(Long user_id);
	public List<ShoppingCar> list();
}
