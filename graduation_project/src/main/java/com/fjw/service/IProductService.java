package com.fjw.service;

import java.util.List;

import com.fjw.domain.Product;
import com.fjw.domain.query.PageResult;
import com.fjw.domain.query.ProductQuery;
import com.fjw.vo.ProductVo;

public interface IProductService {
	public Long saveProduct(Product product) throws Exception;
	public void deleteProduct(Long id);
	public Product getProduct(Long id);
	public Product getProduct(Long ka_id,Long color_id,String keyTag);
	public void updateProduct(Product product);
	public List<Product> listAll(Integer current);
	public List<Product> list(Integer current);
	public PageResult<ProductVo> query(ProductQuery query);
	public void batchDelete(Long[] ids);
	public Product getFirst(Long ka_id);
	public List<Product> listByKey(Long ka_id);
}
