package com.fjw.query;

import java.util.Date;

import com.fjw.util.MyStringUtil;
import com.fjw.util.TimeUtil;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class SaleChartQuery extends BaseQuery{
	private Date beginTime;
	private Date endTime;
	private String keyword;
	private Long clientId = -1L;
	private Long brandId = -1L;
	private String groupType = "EMPLOYEE";
	private String chartType;
	
	protected void customized() {
		if(beginTime != null) {
			beginTime = TimeUtil.getBeginTime(beginTime);
			super.addQuery("(obj.vdate>?)", beginTime);
		}
		if(endTime != null) {
			endTime = TimeUtil.getEndTime(endTime);
			super.addQuery("(obj.vdate<?)", endTime);
		}
		if(MyStringUtil.hasLength(keyword)){
			String key = "%"+keyword+"%";
			super.addQuery("(obj.product.name LIKE ?)", key);
		}
		if(clientId > 0) {
			super.addQuery("(obj.client.id=?)", clientId);
		}
		if(brandId > 0) {
			super.addQuery("(obj.product.brand.id=?)", brandId);
		}
	}
}
