package com.fjw.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data@Alias("Product")
@JsonIgnoreProperties(value = {"handler"})
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	@DecimalMin(value="0.00",inclusive=true,message="价格不合法(0~999999)")
	@DecimalMax(value="999999.00",inclusive=true,message="价格不合法(0~999999)")
	private BigDecimal price;
	@Past
	private Date createTime;
	@Range(min=0,max=999999,message="库存不合法(0~999999)")
	private Integer number;
	@NotNull(message="上架状态不存在")
	private Integer state;
	private KeyAttribute keyAttribute;
	private String keyTag;
	private Color color;
	private List<KeyValueProduct> keyValues;
}
