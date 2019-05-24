package com.fjw.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.type.Alias;

import com.alibaba.fastjson.JSON;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter@Alias("User")
public class User {
	private Long id;
	private String username;
	private String password;
	private String name;
	private Integer age;
	private String school;
	private Integer gender;
	private String image;
	private List<Article> list;
	
	/*public String getUserJson() {
		Map<String,Object> map = new HashMap<>();
		map.put("image", image);
		map.put("name", name);
		map.put("age", age);
		map.put("school", school);
		map.put("gender", gender);
		return JSON.toJSONString(map);
	}
	public String getCollectArticle() {
		return JSON.toJSONString(list);
	}*/
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", name=" + name + ", age="
				+ age + ", school=" + school + ", gender=" + gender + ", image=" + image + "]";
	}
}
