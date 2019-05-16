package com.fjw.service;

import java.util.List;

import com.fjw.Vo.OrderChartVo;
import com.fjw.query.OrderChartQuery;

public interface IOrderChartService {
	public List<OrderChartVo> orderChartList(OrderChartQuery oqo);
}
