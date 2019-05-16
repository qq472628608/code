package com.fjw.action;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.fjw.Vo.ChartJson;
import com.fjw.Vo.OrderChartVo;
import com.fjw.Vo.PieChartJson;
import com.fjw.Vo.SaleChartType;
import com.fjw.Vo.SaleChartVo;
import com.fjw.query.SaleChartQuery;
import com.fjw.service.IBrandService;
import com.fjw.service.IClientService;
import com.fjw.service.ISaleChartService;
import com.fjw.util.RequiredPermission;
import com.opensymphony.xwork2.ActionContext;

import lombok.Getter;
import lombok.Setter;

public class SaleChartAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	@Setter
	private ISaleChartService saleChartService;
	@Setter
	private IClientService clientService;
	@Setter
	private IBrandService brandService;
	@Setter@Getter
	private SaleChartQuery sqo = new SaleChartQuery();
	
	@RequiredPermission("销售报表")
	public String list() {
		try {
			ActionContext.getContext().put("saleCharts", saleChartService.listSaleChart(sqo));
			ActionContext.getContext().put("types", SaleChartType.values());
			ActionContext.getContext().put("clients", clientService.list());
			ActionContext.getContext().put("brands", brandService.list());
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return "list";
	}
	
	public String showChart() throws IOException {
		if("line".equals(sqo.getChartType())) {
			return "showLineChart";
		}
		if("bar".equals(sqo.getChartType())) {
			return "showBarChart";
		}
		if("pie".equals(sqo.getChartType())) {
			return "showPieChart";
		}
		return NONE;
	}
	
	public String getJson() throws IOException {
		List<SaleChartVo> saleChartVos = saleChartService.listSaleChart(sqo);
		List<String> groupNames = new ArrayList<>(); 
		List<BigDecimal> amounts = new ArrayList<>();
		for (SaleChartVo saleChart : saleChartVos) {
			groupNames.add(saleChart.getGroup());
			amounts.add(saleChart.getAmount());
		}
		ChartJson json = new ChartJson(groupNames,amounts);
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(JSON.toJSONString(json));
		return NONE;
	}
	public String getPieJson() throws IOException {
		List<SaleChartVo> saleChartVos = saleChartService.listSaleChart(sqo);
		List<PieChartJson> pieJson = new ArrayList<PieChartJson>();
		for (SaleChartVo saleChart : saleChartVos) {
			PieChartJson pie = new PieChartJson();
			pie.setName(saleChart.getGroup());
			pie.setY(saleChart.getAmount());
			pieJson.add(pie);
		}
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(JSON.toJSONString(pieJson));
		return NONE;
	}
}
