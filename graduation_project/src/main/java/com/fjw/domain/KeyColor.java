package com.fjw.domain;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data@Alias("KeyColor")
@JsonIgnoreProperties(value = {"handler"})
public class KeyColor {
	private Long id;
	private Long ka_id;
	private Color color;
}
