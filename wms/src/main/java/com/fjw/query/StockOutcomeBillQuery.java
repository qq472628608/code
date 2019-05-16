package com.fjw.query;

import java.util.Date;

import com.fjw.util.TimeUtil;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class StockOutcomeBillQuery extends BaseQuery{
	private Date beginTime;
	private Date endTime;
	private Long clientId = -1L;
	private Long depotId = -1L;
	private Integer status = -1;
	
	protected void customized() {
		if(beginTime != null) {
			super.addQuery("(obj.vdate>?)", TimeUtil.getBeginTime(beginTime));
		}
		if(endTime != null) {
			super.addQuery("(obj.vdate<?)", TimeUtil.getEndTime(endTime));
		}
		if(clientId > 0) {
			super.addQuery("(obj.client.id=?)", clientId);
		}
		if(depotId > 0) {
			super.addQuery("(obj.depot.id=?)", depotId);
		}
		if(status >= 0) {
			super.addQuery("(obj.status=?)", status);
		}
	}
}
