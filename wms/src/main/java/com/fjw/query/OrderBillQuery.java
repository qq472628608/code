package com.fjw.query;

import java.util.Date;

import com.fjw.util.TimeUtil;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class OrderBillQuery extends BaseQuery{
	private Date beginTime;
	private Date endTime;
	private Long supplierId = -1L;
	private Integer status = -1;

	protected void customized() {
		if(beginTime !=null) {
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
		if(status >= 0) {
			super.addQuery("(obj.status=?)", status);
		}
	}
}
