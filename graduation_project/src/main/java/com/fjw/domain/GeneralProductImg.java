package com.fjw.domain;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.Range;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Alias("GeneralProductImg")
@JsonIgnoreProperties(value = {"handler"})
public class GeneralProductImg implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long state;
	private String url;
	private Color color;
	private Long key_attribute_id;
}
