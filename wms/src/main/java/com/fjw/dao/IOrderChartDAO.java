package com.fjw.dao;

import java.util.List;

import com.fjw.Vo.OrderChartVo;
import com.fjw.query.OrderChartQuery;

public interface IOrderChartDAO {
	public List<OrderChartVo> orderChartList(OrderChartQuery oqo);
}
