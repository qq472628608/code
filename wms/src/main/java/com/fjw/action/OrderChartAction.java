package com.fjw.action;


import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.fjw.Vo.ChartJson;
import com.fjw.Vo.OrderChartType;
import com.fjw.Vo.OrderChartVo;
import com.fjw.Vo.PieChartJson;
import com.fjw.query.OrderChartQuery;
import com.fjw.service.IBrandService;
import com.fjw.service.IOrderChartService;
import com.fjw.service.ISupplierService;
import com.fjw.util.RequiredPermission;
import com.opensymphony.xwork2.ActionContext;

import lombok.Getter;
import lombok.Setter;

public class OrderChartAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	@Setter
	private IOrderChartService orderChartService;
	@Setter
	private ISupplierService supplierService;
	@Setter
	private IBrandService brandService;
	@Setter@Getter
	private OrderChartQuery oqo = new OrderChartQuery();
	@RequiredPermission("采购报表")
	public String list() {
		try {
			ActionContext.getContext().put("orderCharts", orderChartService.orderChartList(oqo));
			ActionContext.getContext().put("types", OrderChartType.values());
			ActionContext.getContext().put("suppliers", supplierService.list());
			ActionContext.getContext().put("brands", brandService.list());
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return "list";
	}
	
	public String showChart() throws IOException {
		if("line".equals(oqo.getChartType())) {
			return "showLineChart";
		}
		if("bar".equals(oqo.getChartType())) {
			return "showBarChart";
		}
		if("pie".equals(oqo.getChartType())) {
			return "showPieChart";
		}
		return NONE;
	}
	
	public String getJson() throws IOException {
		List<OrderChartVo> orderChartVos = orderChartService.orderChartList(oqo);
		List<String> groupNames = new ArrayList<>(); 
		List<BigDecimal> amounts = new ArrayList<>();
		for (OrderChartVo orderChart : orderChartVos) {
			groupNames.add(orderChart.getGroup());
			amounts.add(orderChart.getAmount());
		}
		ChartJson json = new ChartJson(groupNames,amounts);
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(JSON.toJSONString(json));
		return NONE;
	}
	public String getPieJson() throws IOException {
		List<OrderChartVo> orderChartVos = orderChartService.orderChartList(oqo);
		List<PieChartJson> pieJson = new ArrayList<PieChartJson>();
		for (OrderChartVo orderChart : orderChartVos) {
			PieChartJson pie = new PieChartJson();
			pie.setName(orderChart.getGroup());
			pie.setY(orderChart.getAmount());
			pieJson.add(pie);
		}
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(JSON.toJSONString(pieJson));
		return NONE;
	}
}
