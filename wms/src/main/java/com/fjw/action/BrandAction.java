package com.fjw.action;

import java.util.ArrayList;
import java.util.List;

import com.fjw.domain.Brand;
import com.fjw.query.BaseQuery;
import com.fjw.service.IBrandService;
import com.fjw.util.RequiredPermission;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

import lombok.Getter;
import lombok.Setter;

public class BrandAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	@Setter
	private IBrandService brandService;
	@Setter@Getter
	private BaseQuery bq = new BaseQuery();
	@Setter@Getter
	private Brand brand = new Brand();
	@Setter
	private List<Long> ids = new ArrayList<>();
	
	@InputConfig(methodName="edit")
	@RequiredPermission("品牌列表")
	public String list() {
		try {
			ActionContext.getContext().put("pageQuery",brandService.query(bq));
			
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return "list";
	}
	@RequiredPermission("品牌编辑")
	public String edit() throws Exception{
		if(brand.getId() != null) {
			this.brand = brandService.get(brand.getId());
		}
		return "edit";
	}
	@RequiredPermission("品牌保存或更新")
	public String saveOrUpdate() {
		try {
			if(brand.getId() == null) {
				brandService.save(brand);
			}else {
				brandService.update(brand);
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return "saveOrUpdate";
	}
	@RequiredPermission("品牌删除")
	public String delete() throws Exception{
		brandService.delete(brand.getId());
		return NONE;
	}
	@RequiredPermission("品牌批量删除")
	public String bathDelete() throws Exception{
		brandService.bathDelete(ids);
		return NONE;
	}
}
