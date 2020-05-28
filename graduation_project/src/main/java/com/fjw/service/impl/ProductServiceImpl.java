package com.fjw.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fjw.annotation.LogAnnotation;
import com.fjw.domain.Product;
import com.fjw.domain.query.PageResult;
import com.fjw.domain.query.ProductQuery;
import com.fjw.mapper.ProductMapper;
import com.fjw.service.IProductService;
import com.fjw.util.StringUtil;
import com.fjw.vo.ProductVo;

import lombok.Setter;

@Service
public class ProductServiceImpl implements IProductService {
	
	@Setter@Autowired@Lazy
	private ProductMapper productMapper;
	
	@CacheEvict(value= {"product,collect","comment","car"},allEntries=true)
	@Transactional(rollbackFor= {Exception.class})
	@LogAnnotation(description="添加一个具体商品")
	public Long saveProduct(Product product) throws Exception {
		if(product.getCreateTime() == null) {
			product.setCreateTime(new Date());
		}
		productMapper.saveProduct(product);
		return product.getId();
	}

	@CacheEvict(value= {"product,collect","comment","car"},allEntries=true)
	@Transactional(rollbackFor= {Exception.class})
	@LogAnnotation(description="删除一个具体商品")
	public void deleteProduct(Long id) {
		productMapper.deleteProduct(id);
	}

	@Cacheable(value="product")
	@LogAnnotation(description="根据Id具体查询一个商品")
	public Product getProduct(Long id) {
		return productMapper.getProduct(id);
	}

	@Cacheable(value="product")
	@LogAnnotation(description="根据key和颜色查询商品")
	public Product getProduct(Long ka_id, Long color_id, String keyTag) {
		return productMapper.getProductByAttribute(ka_id, color_id, keyTag);
	}

	@CacheEvict(value= {"product,collect","comment","car"},allEntries=true)
	@Transactional(rollbackFor= {Exception.class})
	@LogAnnotation(description="更新商品信息")
	public void updateProduct(Product product) {
		productMapper.updateProduct(product);
	}

	@Cacheable(value="product")
	@LogAnnotation(description="查询所有商品")
	public List<Product> listAll(Integer current) {
		return null;
	}

	@Cacheable(value="product")
	@LogAnnotation(description="获取当前页数的商品")
	public List<Product> list(Integer current) {
		return null;
	}

	@LogAnnotation(description="商品模糊查询")
	public PageResult<ProductVo> query(ProductQuery query) {
		Integer begin = (query.getCurrent() - 1) * 11;
		if(StringUtil.hasLength(query.getName())) {
			query.setName("%"+query.getName()+"%");
		}
		List<Product> products = productMapper.query(query, new RowBounds(begin,11));
		Integer count = productMapper.getCount(query);
		List<ProductVo> vos = new ArrayList<>();
		for (Product product : products) {
			vos.add(new ProductVo(product));
		}
		return new PageResult<>(vos, count, 11, query.getCurrent());
	}

	@CacheEvict(value= {"product,collect","comment","car"},allEntries=true)
	@Transactional(rollbackFor= {Exception.class})
	@LogAnnotation(description="批量删除商品")
	public void batchDelete(Long[] ids) {
		productMapper.batchDelete(ids);
	}

	@Cacheable(value="product")
	@LogAnnotation(description="获取同一商品的第一个")
	public Product getFirst(Long ka_id) {
		return productMapper.getFirst(ka_id);
	}
	
	@Cacheable(value="product")
	@LogAnnotation(description="获取一种商品")
	public List<Product> listByKey(Long ka_id){
		return productMapper.listByKey(ka_id);
	}
	
}
