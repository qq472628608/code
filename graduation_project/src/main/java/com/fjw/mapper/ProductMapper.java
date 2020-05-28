package com.fjw.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.fjw.domain.Product;
import com.fjw.domain.query.ProductQuery;

@Mapper
public interface ProductMapper {
	public void saveProduct(Product product);
	public void deleteProduct(Long id);
	public Product getProduct(Long id);
	public Product getProductByAttribute(@Param("ka_id")Long ka_id,@Param("color_id")Long color_id,@Param("keyTag")String keyTag);
	public void updateProduct(Product product);
	public List<Product> list(RowBounds rowBounds);
	public List<Product> query(ProductQuery productQuery,RowBounds rowBounds);
	public Integer getCount(ProductQuery query);
	public void batchDelete(Long[] ids);
	public List<Product> listSale(RowBounds rowBounds);
	public Product getFirst(Long ka_id);
	public List<Product> listByKey(Long ka_id);
}
