package com.fjw.service;

import java.util.List;

import com.fjw.domain.Address;
import com.fjw.dto.AddressDto;

public interface IAddressService {
	public List<Address> save(AddressDto dto);
	public List<Address> delete(Long id);
	public Address getAddress(Long id);
	public List<Address> updateAddress(AddressDto dto);
	public List<Address> listByUser();
	public List<Address> setDefault(Long id);
}
