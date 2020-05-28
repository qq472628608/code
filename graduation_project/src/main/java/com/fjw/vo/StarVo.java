package com.fjw.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(value = {"handler"})
public class StarVo implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer count;
	private Float total;
	private Float general;
	private Float fit;
	private Float sellerService;
	private Float sellerSpeed;

	public StarVo(Integer count,Float total,Float general,Float fit,Float sellerService,Float sellerSpeed) {
		this.count = count;
		this.total = total == 0?5:total;
		this.general = general == 0?5:total;
		this.fit = fit == 0?5:total;
		this.sellerService = sellerService == 0?5:total;
		this.sellerSpeed = sellerSpeed == 0?5:total;
	}
}
