package com.fjw.domain;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data@Alias("KeyValueProduct")
@JsonIgnoreProperties(value = {"handler"})
public class KeyValueProduct implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long product_id;
	private KeyValue keyValue;
}
