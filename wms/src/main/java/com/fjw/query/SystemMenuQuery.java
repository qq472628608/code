package com.fjw.query;

import lombok.Getter;
import lombok.Setter;

public class SystemMenuQuery extends BaseQuery{
	@Setter@Getter
	private Long parentId = -1L; 
	@Setter@Getter
	private String parentSn;
	protected void customized() {
		if(parentId == -1L) {
			super.addCondition("obj.parent.id IS NULL");
		}else {
			super.addQuery("obj.parent.id=?", parentId);
		}
	}
}
