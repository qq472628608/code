package com.fjw.domain;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data@Alias("ShoppingCar")
@JsonIgnoreProperties(value = {"handler"})
public class ShoppingCar implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long user_id;
	private List<ShoppingcarProduct> carProducts;
}
