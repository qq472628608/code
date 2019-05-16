package com.fjw.Vo;

import lombok.Getter;

@Getter
public enum OrderChartType {
	//本枚举类所定义的对象(枚举类中的常量是本枚举类的对象)
	EMPLOYEE("obj.bill.inputUser.name","obj.bill.inputUser","订单人员"),
	PRODUCT("obj.product.name","obj.product","货品名称"),
	BRAND("obj.product.brand.name","obj.product.brand","品牌名称"),
	SUPPLIER("obj.bill.supplier.name","obj.bill.supplier","货品供应商"),
	MONTH("DATE_FORMAT(obj.bill.vdate,'%Y-%m')","DATE_FORMAT(obj.bill.vdate,'%Y-%m')","订单日期(月)"),
	DAY("DATE_FORMAT(obj.bill.vdate,'%Y-%m-%d')","DATE_FORMAT(obj.bill.vdate,'%Y-%m-%d')","订单日期(日)");
	
	//枚举类的属性，枚举对象用来携带
	private String voGroup;
	private String group;
	private String groupName;
	//枚举类的构造器，用来给枚举对象设置属性
	private OrderChartType(String voGroup,String group,String groupName) {
		this.voGroup = voGroup;
		this.group = group;
		this.groupName = groupName;
	}
}
