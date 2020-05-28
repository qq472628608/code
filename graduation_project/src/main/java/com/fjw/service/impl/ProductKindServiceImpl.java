package com.fjw.service.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fjw.annotation.LogAnnotation;
import com.fjw.domain.ProductKind;
import com.fjw.domain.query.PageResult;
import com.fjw.mapper.ProductKindMapper;
import com.fjw.service.IProductKindService;

import lombok.Setter;

@Service
public class ProductKindServiceImpl implements IProductKindService {
	
	@Setter@Autowired@Lazy
	private ProductKindMapper kindMapper;

	@CacheEvict(value="kind,keyAttribute",allEntries=true)
	@Transactional(rollbackFor= {Exception.class})
	@LogAnnotation(description="添加商品种类")
	public void save(ProductKind productKind) {
		kindMapper.saveProductKind(productKind);
	}

	@CacheEvict(value="kind,keyAttribute",allEntries=true)
	@Transactional(rollbackFor= {Exception.class})
	@LogAnnotation(description="删除商品种类")
	public void delete(Long id) {
		kindMapper.deleteProductKind(id);
	}

	@Cacheable(value="kind")
	@LogAnnotation(description="获取所有的商品种类")
	public List<ProductKind> list() {
		return kindMapper.listBase();
	}

	@CacheEvict(value="kind,keyAttribute",allEntries=true)
	@Transactional(rollbackFor= {Exception.class})
	@LogAnnotation(description="更新商品种类")
	public void update(ProductKind productKind) {
		kindMapper.updateProductKind(productKind);
	}

	@CacheEvict(value="kind,keyAttribute",allEntries=true)
	@Transactional(rollbackFor= {Exception.class})
	@LogAnnotation(description="批量删除商品种类")
	public void batchDelete(Long[] ids) {
		kindMapper.batchDelete(ids);
	}

	@Transactional(rollbackFor= {Exception.class})
	@LogAnnotation(description="查询商品种类")
	public PageResult<ProductKind> list(String name, Integer current) {
		Integer begin = (current - 1) * 11;
		List<ProductKind> result = kindMapper.queryByName(name, new RowBounds(begin,11));
		Integer count = kindMapper.getCount(name);
		return new PageResult<>(result, count, 11, current);
	}

	@Cacheable(value="kind")
	@LogAnnotation(description="查询一个商品种类")
	public ProductKind get(Long id) {
		return kindMapper.getProductKind(id);
	}

}
