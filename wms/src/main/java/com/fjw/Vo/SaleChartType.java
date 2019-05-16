package com.fjw.Vo;

import lombok.Getter;

@Getter
public enum SaleChartType {
	//枚举对象
	EMPLOYEE("obj.saleMan.name","obj.saleMan","销售人员"),
	PRODUCT("obj.product.name","obj.product","货品名称"),
	BRAND("obj.product.brand.name","obj.product.brand","品牌名称"),
	CLIENT("obj.client.name","obj.client","客户名"),
	MONTH("DATE_FORMAT(obj.vdate,'%Y-%m')","DATE_FORMAT(obj.vdate,'%Y-%m')","销售时间(月)"),
	DAY("DATE_FORMAT(obj.vdate,'%Y-%m-%d')","DATE_FORMAT(obj.vdate,'%Y-%m-%d')","销售时间(日)");
	
	//枚举属性
	private String voGroup;
	private String group;
	private String groupName;
	
	//枚举构造方法
	private SaleChartType(String voGroup, String group, String groupName) {
		this.voGroup = voGroup;
		this.group = group;
		this.groupName = groupName;
	}
}
