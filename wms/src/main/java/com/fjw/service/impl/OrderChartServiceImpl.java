package com.fjw.service.impl;

import java.util.List;

import com.fjw.Vo.OrderChartVo;
import com.fjw.dao.IOrderChartDAO;
import com.fjw.query.OrderChartQuery;
import com.fjw.service.IOrderChartService;

import lombok.Setter;

public class OrderChartServiceImpl implements IOrderChartService{
	@Setter
	private IOrderChartDAO orderChartDAO;

	public List<OrderChartVo> orderChartList(OrderChartQuery oqo) {
		return orderChartDAO.orderChartList(oqo);
	}
}
