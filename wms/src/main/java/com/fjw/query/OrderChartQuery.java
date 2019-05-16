package com.fjw.query;

import java.util.Date;

import com.fjw.util.MyStringUtil;
import com.fjw.util.TimeUtil;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class OrderChartQuery extends BaseQuery{
	private Date beginTime;
	private Date endTime;
	private String keyword;
	private Long supplierId = -1L;
	private Long brandId = -1L;
	private String groupType = "EMPLOYEE";
	private String chartType;
	
	protected void customized() {
		super.addQuery("(obj.bill.status=?)", 1);
		if(beginTime != null) {
			beginTime = TimeUtil.getBeginTime(beginTime);
			super.addQuery("(obj.bill.vdate>?)", beginTime);
		}
		if(endTime != null) {
			endTime = TimeUtil.getEndTime(endTime);
			super.addQuery("(obj.bill.vdate<?)", endTime);
		}
		if(MyStringUtil.hasLength(keyword)) {
			String key = "%"+keyword+"%";
			super.addQuery("(obj.product.name LIKE ?)", key);
		}
		if(supplierId > 0) {
			super.addQuery("(obj.bill.supplier.id=?)", supplierId);
		}
		if(brandId > 0) {
			super.addQuery("(obj.product.brand.id=?)", brandId);
		}
	}
}
