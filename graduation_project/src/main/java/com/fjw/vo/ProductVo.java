package com.fjw.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fjw.domain.Color;
import com.fjw.domain.KeyAttribute;
import com.fjw.domain.Product;
import com.fjw.util.StringUtil;

import lombok.Data;

@Data
@JsonIgnoreProperties(value = {"handler"})
public class ProductVo implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private BigDecimal price;
	private String createTime;
	private Integer number;
	private Integer state;
	private String name;
	private String introduction;
	private String keyTag;
	private Color color;
	
	public ProductVo(Product product) {
		this.id = product.getId();
		this.price = product.getPrice();
		this.createTime = StringUtil.getFormatDate(product.getCreateTime());
		this.number = product.getNumber();
		this.state = product.getState();
		this.name = product.getKeyAttribute().getName();
		this.introduction = product.getKeyAttribute().getIntroduction();
		this.keyTag = product.getKeyTag();
		this.color = product.getColor();
	}
}
