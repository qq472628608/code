package com.fjw.service;

import java.util.List;

import com.fjw.Vo.SaleChartVo;
import com.fjw.query.SaleChartQuery;

public interface ISaleChartService {
	public List<SaleChartVo> listSaleChart(SaleChartQuery sqo);
}
