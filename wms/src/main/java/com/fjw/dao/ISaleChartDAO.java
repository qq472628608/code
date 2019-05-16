package com.fjw.dao;

import java.util.List;

import com.fjw.Vo.SaleChartVo;
import com.fjw.query.SaleChartQuery;

public interface ISaleChartDAO extends IGeneratorDAO<SaleChartVo>{
	public List<SaleChartVo> listSaleChart(SaleChartQuery sqo);
}
