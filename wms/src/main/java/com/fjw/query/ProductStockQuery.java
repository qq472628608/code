package com.fjw.query;

import com.fjw.util.MyStringUtil;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class ProductStockQuery extends BaseQuery{
	private String keyword;
	private Long brandId = -1L;
	private Long depotId = -1L;
	
	protected void customized() {
		if(MyStringUtil.hasLength(keyword)) {
			String key = "%"+keyword+"%";
			super.addQuery("(obj.product.name LIKE ?)", key);
		}
		if(brandId > 0) {
			super.addQuery("(obj.product.brand.id=?)", brandId);
		}
		if(depotId > 0) {
			super.addQuery("(obj.depot.id=?)", depotId);
		}
	}
}
