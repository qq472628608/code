package com.fjw.mapper;

import java.util.List;

import com.fjw.domain.User;

public interface UserMapper {
	public void save(User user);
	public void delete(Long id);
	public User get(Long id);
	public void update(User user);
	public List<User> list();
	public User query(String username);
}
