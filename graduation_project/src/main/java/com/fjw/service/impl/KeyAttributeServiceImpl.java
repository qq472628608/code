package com.fjw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fjw.annotation.LogAnnotation;
import com.fjw.domain.KeyAttribute;
import com.fjw.domain.ProductKind;
import com.fjw.dto.KeyDto;
import com.fjw.mapper.KeyAttributeMapper;
import com.fjw.service.IKeyAttributeService;
import com.fjw.util.StringUtil;

import lombok.Setter;

@Service
public class KeyAttributeServiceImpl implements IKeyAttributeService {
	
	@Setter@Autowired@Lazy
	private KeyAttributeMapper keyMapper;

	@CacheEvict(value= {"keyAttribute,product"},allEntries=true)
	@Transactional(rollbackFor= {Exception.class})
	@LogAnnotation(description="添加商品")
	public Long save(KeyDto dto) {
		KeyAttribute key = new KeyAttribute();
		ProductKind kind = new ProductKind();
		kind.setId(dto.getKind_id());
		key.setBasePrice(dto.getBasePrice());
		key.setIntroduction(dto.getIntroduction());
		key.setSimpleIntroduction(dto.getSimpleIntroduction());
		key.setKind(kind);
		key.setName(dto.getName());
		if(dto.getId() != null && dto.getId() > 0) {
			key.setId(dto.getId());
			keyMapper.updateKeyAttribute(key);
		}else {
			keyMapper.saveKeyAttribute(key);
			return key.getId();
		}
		return null;
	}

	@CacheEvict(value= {"keyAttribute,product"},allEntries=true)
	@Transactional(rollbackFor= {Exception.class})
	@LogAnnotation(description="删除商品")
	public void delete(Long id) {
		keyMapper.deleteKeyAttribute(id);
	}

	@Cacheable(value="keyAttribute")
	@LogAnnotation(description="查询一个商品")
	public KeyAttribute getKeyAttribute(Long id) {
		return keyMapper.getKeyAttribute(id);
	}
	
	@CacheEvict(value= {"keyAttribute","product"},allEntries=true)
	@LogAnnotation(description="更新商品信息")
	public void updateKeyAttribute(KeyAttribute attribute) {
		keyMapper.updateKeyAttribute(attribute);
	}

	@Cacheable(value="keyAttribute")
	@LogAnnotation(description="查询所有商品")
	public List<KeyAttribute> list() {
		return keyMapper.list();
	}

	@Cacheable(value="keyAttribute")
	@LogAnnotation(description="根据种类查询商品")
	public List<KeyAttribute> listByKind(Long kind_id,Integer begin,Integer end) {
		return keyMapper.listByKind(kind_id,begin,end);
	}
	
	@Cacheable(value="keyAttribute")
	@LogAnnotation(description="根据种类查询最新商品")
	public List<KeyAttribute> listByKindDesc(Long kind_id,Integer begin,Integer end){
		return keyMapper.listByKindDesc(kind_id,begin,end);
	}
	
	@Cacheable(value="keyAttribute")
	@LogAnnotation(description="根据名称查询商品")
	public KeyAttribute queryByName(String name) {
		return keyMapper.queryByName(name);
	}
	
	@Cacheable(value="keyAttribute")
	@LogAnnotation(description="时间倒序查询商品")
	public List<KeyAttribute> listNewest(Integer number){
		if(number == null || number < 0) {
			number = 0;
		}
		return keyMapper.listNewest(number); 
	}

	@Cacheable(value="keyAttribute")
	@LogAnnotation(description="价格排序查询商品")
	public List<KeyAttribute> listByPrice(Long kind_id, Integer number, String tag) {
		return keyMapper.listOrderByPrice(kind_id, number, tag);
	}

	@Cacheable(value="keyAttribute")
	@LogAnnotation(description="名称模糊查询商品")
	public List<KeyAttribute> listByName(String name) {
		if(StringUtil.hasLength(name)) {
			return keyMapper.listByName("%"+name+"%");
		}
		return null;
	}

}
