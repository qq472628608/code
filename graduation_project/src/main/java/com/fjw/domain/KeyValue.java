package com.fjw.domain;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data@Alias("KeyValue")
@JsonIgnoreProperties(value = {"handler"})
public class KeyValue implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long ka_id;
	private AttributeValue attributeValue;
}
