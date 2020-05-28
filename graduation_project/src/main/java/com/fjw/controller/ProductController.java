package com.fjw.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fjw.domain.Product;
import com.fjw.domain.query.PageResult;
import com.fjw.domain.query.ProductQuery;
import com.fjw.service.IProductService;
import com.fjw.vo.ProductVo;

import lombok.Setter;

@RestController
public class ProductController {
	
	@Setter@Autowired
	private IProductService productService;
	
	@RequestMapping(value="/product/{ka_id}",method=RequestMethod.GET)
	public ProductVo getFirst(@PathVariable("ka_id")Long ka_id) {
		return new ProductVo(productService.getFirst(ka_id));
	} 
	
	@RequestMapping(value="/products/{ka_id}",method=RequestMethod.GET)
	public List<ProductVo> listByKey(@PathVariable("ka_id")Long ka_id){
		List<Product> products = productService.listByKey(ka_id);
		List<ProductVo> vos = new ArrayList<>();
		for (Product product : products) {
			vos.add(new ProductVo(product));
		}
		return vos;
	}
	
	@RequestMapping(value="/products",method=RequestMethod.PUT)
	public PageResult<ProductVo> query(ProductQuery query){
		return productService.query(query);
	}
	
	@RequestMapping(value="/product/{id}",method=RequestMethod.DELETE)
	public void delete(@PathVariable("id")Long id) {
		productService.deleteProduct(id);
	}
	
	@RequestMapping(value="/product",method=RequestMethod.GET)
	public Product getProduct(Long id) {
		return productService.getProduct(id);
	}
	
	@RequestMapping(value="/product",method=RequestMethod.POST)
	public Long save(@RequestBody Product product) throws Exception {
		return productService.saveProduct(product);
	}
}
