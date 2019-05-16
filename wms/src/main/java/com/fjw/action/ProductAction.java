package com.fjw.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.fjw.domain.Product;
import com.fjw.query.ProductQuery;
import com.fjw.service.IBrandService;
import com.fjw.service.IProductService;
import com.fjw.util.MyStringUtil;
import com.fjw.util.PictureUtil;
import com.fjw.util.RequiredPermission;
import com.opensymphony.xwork2.ActionContext;

import lombok.Getter;
import lombok.Setter;

public class ProductAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	@Setter
	private IProductService productService; 
	@Setter
	private IBrandService brandService;
	@Setter@Getter
	private ProductQuery bq = new ProductQuery();
	@Setter@Getter
	private Product product = new Product();
	@Setter
	private List<Long> ids = new ArrayList<>();
	//=================================================================
	@Setter
	private File picture;
	@Setter
	private String pictureFileName;
	//=================================================================
	@RequiredPermission("商品列表")
	public String list(){
		try {
			ActionContext.getContext().put("pageQuery", productService.query(bq));
			ActionContext.getContext().put("brands", brandService.list());
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return "list";
	}
	@RequiredPermission("商品展示")
	public String showProduct() {
		try {
			ActionContext.getContext().put("pageQuery", productService.query(bq));
			ActionContext.getContext().put("brands", brandService.list());
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return "showProduct";
	}
	@RequiredPermission("商品编辑")
	public String edit() throws Exception{
		ActionContext.getContext().put("brands", brandService.list());
		if(product.getId() != null) {
			this.product = productService.get(product.getId());
		}
		return "edit";
	}
	@RequiredPermission("商品保存或更新")
	public String saveOrUpdate() {
		if(product.getId() != null && MyStringUtil.hasLength(product.getImagePath())) {
			PictureUtil.deletePicture(product.getImagePath());
		}
		try {
			String picturePath = PictureUtil.pictureUpload(picture, pictureFileName);
			product.setImagePath(picturePath);
			if(product.getId() == null){
				productService.save(product);
			}else {
				productService.update(product);
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return "saveOrUpdate";
	}
	@RequiredPermission("商品删除")
	public String delete() throws Exception{
		if(product.getId() != null) {
			this.product = productService.get(product.getId());
			PictureUtil.deletePicture(product.getImagePath());
		}
		productService.delete(product.getId());
		return NONE;
	}
	@RequiredPermission("商品批量删除")
	public String bathDelete() throws Exception{
		if(ids != null) {
			for (Long id : ids) {
				product = productService.get(id);
				PictureUtil.deletePicture(product.getImagePath());
			}
		}
		productService.bathDelete(ids);
		return NONE;
	}
	
	public void prepareSaveOrUpdate() throws Exception{
		if(product.getId() != null) {
			this.product = productService.get(product.getId());
			this.product.setBrand(null);
		}
	}
}
