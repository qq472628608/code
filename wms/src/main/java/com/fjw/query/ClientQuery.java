package com.fjw.query;

import com.fjw.util.MyStringUtil;

public class ClientQuery extends BaseQuery{
	private String name;
	private String phone;
	
	protected void customized() {
		if(MyStringUtil.hasLength(name)) {
			super.addQuery("(obj.name=?)", name);
		}
		if(MyStringUtil.hasLength(phone)) {
			super.addQuery("(obj.phone=?)", phone);
		}
	}
}
