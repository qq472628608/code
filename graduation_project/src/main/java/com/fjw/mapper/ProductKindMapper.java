package com.fjw.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.fjw.domain.ProductKind;

@Mapper
public interface ProductKindMapper {
	public void saveProductKind(ProductKind productKind);
	public void deleteProductKind(Long id);
	public ProductKind getProductKind(Long id);
	public void updateProductKind(ProductKind productKind);
	public List<ProductKind> list(RowBounds rowBounds);
	public List<ProductKind> queryByName(@Param("name")String name,RowBounds rowBounds);
	public void batchDelete(Long[] ids);
	public List<ProductKind> listBase();
	public Integer getCount(@Param("name")String name);
}
