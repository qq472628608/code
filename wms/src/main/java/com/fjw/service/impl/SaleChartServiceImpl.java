package com.fjw.service.impl;

import java.util.List;

import com.fjw.Vo.SaleChartVo;
import com.fjw.dao.ISaleChartDAO;
import com.fjw.query.SaleChartQuery;
import com.fjw.service.ISaleChartService;

import lombok.Setter;

public class SaleChartServiceImpl implements ISaleChartService{
	@Setter
	private ISaleChartDAO saleChartDAO;
	public List<SaleChartVo> listSaleChart(SaleChartQuery sqo) {
		return saleChartDAO.listSaleChart(sqo);
	}
}
