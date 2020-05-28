package com.fjw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Description;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fjw.annotation.LogAnnotation;
import com.fjw.domain.Address;
import com.fjw.domain.User;
import com.fjw.dto.AddressDto;
import com.fjw.mapper.AddressMapper;
import com.fjw.service.IAddressService;
import com.fjw.util.ContextUserUtil;
import com.fjw.util.ThreadLocalHandler;

import lombok.Setter;

@Service
public class AddressServiceImpl implements IAddressService {
	
	@Setter@Autowired@Lazy
	private AddressMapper addressMapper;

	@CacheEvict(value="address",allEntries=true)
	@Transactional(rollbackFor= {Exception.class})
	@LogAnnotation(description="添加地址")
	public List<Address> save(AddressDto dto) {
		User user = ContextUserUtil.getCurrentUser(ThreadLocalHandler.getLocalRequest());
		dto.setUser_id(user.getId());
		List<Address> addresses = addressMapper.queryByUser(user.getId());
		if(addresses.size() == 0) {
			dto.setIsDefault(1);
		}
		if(dto.getIsDefault() != null && dto.getIsDefault() == 1) {
			addressMapper.setAllNotDefault(user.getId());
		}
		addressMapper.saveAddress(dto);
		return addressMapper.queryByUser(user.getId());
	}

	@CacheEvict(value="address",allEntries=true)
	@Transactional(rollbackFor= {Exception.class})
	@LogAnnotation(description="删除地址")
	public List<Address> delete(Long id) {
		User user = ContextUserUtil.getCurrentUser(ThreadLocalHandler.getLocalRequest());
		addressMapper.deleteAddress(id,user.getId());
		List<Address> addresses = addressMapper.queryByUser(user.getId());
		if(addresses.size() > 0) {
			Address address = addressMapper.getDefaultAddress(user.getId());
			if(address == null) {
				Address newest = addresses.get(addresses.size() - 1);
				newest.setIsDefault(1);
				addressMapper.update(address);
			}
		}
		
		return addressMapper.queryByUser(user.getId());
	}

	@Cacheable(value="address")
	@LogAnnotation(description="查询地址")
	public Address getAddress(Long id) {
		User user = ContextUserUtil.getCurrentUser(ThreadLocalHandler.getLocalRequest());
		return addressMapper.getAddress(id,user.getId());
	}

	@CacheEvict(value="address",allEntries=true)
	@Transactional(rollbackFor= {Exception.class})
	@Description("更新地址信息")
	public List<Address> updateAddress(AddressDto dto) {
		User user = ContextUserUtil.getCurrentUser(ThreadLocalHandler.getLocalRequest());
		dto.setUser_id(user.getId());
		if(dto.getIsDefault() == 1) {
			addressMapper.setAllNotDefault(user.getId());
		}
		addressMapper.updateAddress(dto);
		Address address = addressMapper.getDefaultAddress(user.getId());
		if(address == null) {
			List<Address> addresses = addressMapper.queryByUser(user.getId()); 
			Address newest = addresses.get(addresses.size() - 1);
			newest.setIsDefault(1);
			addressMapper.update(newest);
		}
		return addressMapper.queryByUser(user.getId());
	}
	
	@Cacheable(value="address")
	@LogAnnotation(description="查询地址")
	public List<Address> listByUser() {
		Long user_id = ContextUserUtil.getCurrentUser(ThreadLocalHandler.getLocalRequest()).getId();
		return addressMapper.queryByUser(user_id);
	}

	@CacheEvict(value="address",allEntries=true)
	@Transactional(rollbackFor= {Exception.class})
	@LogAnnotation(description="设置地址为默认地址")
	public List<Address> setDefault(Long id) {
		User user = ContextUserUtil.getCurrentUser(ThreadLocalHandler.getLocalRequest());
		addressMapper.setAllNotDefault(user.getId());
		addressMapper.setDefault(id,user.getId());
		return addressMapper.queryByUser(user.getId());
	}

}
