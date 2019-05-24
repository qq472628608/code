package com.fjw.domain;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter@Alias("Article")
public class Article {
	private Long id;
	private String title;
	private String summary;
	private String text;
	private Integer love;
	private String image;
	private String tag1 = "fa fa-tags";
	//mang to one
	private Kind kind;
	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", summay=" + summary + ", text=" + text + ", love=" + love
				+ ", image=" + image + ", kind=" + kind + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Article other = (Article) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (image == null) {
			if (other.image != null)
				return false;
		} else if (!image.equals(other.image))
			return false;
		if (love == null) {
			if (other.love != null)
				return false;
		} else if (!love.equals(other.love))
			return false;
		if (summary == null) {
			if (other.summary != null)
				return false;
		} else if (!summary.equals(other.summary))
			return false;
		if (tag1 == null) {
			if (other.tag1 != null)
				return false;
		} else if (!tag1.equals(other.tag1))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
}
