package com.fjw.domain;


import java.util.Date;

import org.apache.ibatis.type.Alias;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter@Alias("Comment")
public class Comment {
	private Long id;
	private String text;
	@JSONField(format="yyyy-MM-dd")
	private Date time;
	private Comment parent;
	private User user;
	private Article article;
	@Override
	public String toString() {
		return "Comment [id=" + id + ", text=" + text + ", time=" + time + ", parent=" + parent + ", user=" + user
				+ ", article=" + article + "]";
	}
	
	/*public String getCommentJson() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String date = format.format(time);
		Map<String,Object> map = new HashMap<>();
		map.put("id", id);
		map.put("text", text);
		map.put("time", date);
		map.put("userid", user.getId());
		map.put("articleid", article.getId());
		return JSON.toJSONString(map);
	}*/
	
}
