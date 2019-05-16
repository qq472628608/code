package com.fjw.domain;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class Product {
	private Long id;
	private String name;
	private String sn;
	private BigDecimal costPrice;
	private BigDecimal salePrice;
	private String imagePath;
	private String intro;
	private Brand brand;
	
	
	public String getProductJson() {
		Map<String,Object> json = new HashMap<>();
		json.put("id", id);
		json.put("name", name);
		json.put("costPrice", costPrice);
		json.put("salePrice", salePrice);
		json.put("brand", brand!=null?brand:"");
		return JSON.toJSONString(json);
	}
	
	public String getSmallImagePath() {
		return imagePath.substring(0, imagePath.lastIndexOf('.'))+"_small"+imagePath.substring(imagePath.lastIndexOf('.'));
	}
}
