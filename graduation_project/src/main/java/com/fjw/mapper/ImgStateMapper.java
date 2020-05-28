package com.fjw.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.fjw.domain.ImgState;

@Mapper
public interface ImgStateMapper {
	public void saveImgState(ImgState imgState);
	public ImgState getImgState(Long id);
	public ImgState getByName(String name);
	public void updateImgState(ImgState imgState);
	public List<ImgState> list();
}
