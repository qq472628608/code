package com.fjw.query;

import java.util.Date;

import com.fjw.util.TimeUtil;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class StockIncomeBillQuery extends BaseQuery{
	private Date beginTime;
	private Date endTime;
	private Long supplierId = -1L;
	private Long depotId = -1L;
	private Integer status = -1;
	
	protected void customized() {
		if(beginTime != null) {
			beginTime = TimeUtil.getBeginTime(beginTime);
			super.addQuery("(obj.vdate>?)", beginTime);
		}
		if(endTime != null) {
			endTime = TimeUtil.getEndTime(endTime);
			super.addQuery("(obj.vdate<?)", endTime);
		}
		if(supplierId > 0) {
			super.addQuery("(obj.supplier.id=?)", supplierId);
		}
		if(depotId > 0) {
			super.addQuery("(obj.depot.id=?)", depotId);
		}
		if(status >= 0) {
			super.addQuery("(obj.status=?)", status);
		}
	}
}
