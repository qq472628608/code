package com.fjw.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.fjw.domain.Address;
import com.fjw.dto.AddressDto;

@Mapper
public interface AddressMapper {
	public void saveAddress(AddressDto address);
	public void deleteAddress(@Param("id")Long id,@Param("user_id")Long user_id);
	public Address getAddress(@Param("id")Long id,@Param("user_id")Long user_id);
	public Address getSingleAddress(@Param("id")Long id);
	public Address getDefaultAddress(@Param("user_id")Long user_id);
	public void update(Address address);
	public void updateAddress(AddressDto address);
	public List<Address> list(RowBounds rowBounds);
	public List<Address> queryByUser(Long user_id);
	public void batchDelete(Long ids[]);
	public void setAllNotDefault(Long user_id);
	public void setDefault(@Param("id")Long id,@Param("user_id")Long user_id) ;
}
