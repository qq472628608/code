package com.fjw.query;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class ProductQuery extends BaseQuery{
	private String keyword;
	private BigDecimal min;
	private BigDecimal max;
	private Long brandId = -1L;
	
	public void customized() {
		if(keyword != null) {
			String key = "%"+keyword+"%";
			super.addQuery("(obj.name LIKE ? OR obj.intro LIKE ?)", key ,key);
		}
		if(brandId != -1) {
			super.addQuery("(obj.brand.id=?)", brandId);
		}
		if(min != null) {
			super.addQuery("(obj.salePrice>?)", min);
		}
		if(max != null) {
			super.addQuery("(obj.salePrice<?)", max);
		}
	}
}
