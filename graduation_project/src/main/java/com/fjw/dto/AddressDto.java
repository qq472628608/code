package com.fjw.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Alias("AddressDto")
@JsonIgnoreProperties(value = {"handler"})
public class AddressDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	@NotNull(message="收件人不合法")
	private String name;
	@Pattern(regexp="^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$",message="手机号不合法")
	private String phone;
	private Long province_id;
	private Long city_id;
	private Long county_id;
	@NotNull(message="详细地址不能为空")
	private String detail;
	private Integer isDefault;
	private Long user_id;
}
