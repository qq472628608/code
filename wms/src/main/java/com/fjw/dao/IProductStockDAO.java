package com.fjw.dao;

import com.fjw.domain.ProductStock;

public interface IProductStockDAO extends IGeneratorDAO<ProductStock>{
	public ProductStock getProductStockByProductAndDepot(Long productId,Long depotId);
}
