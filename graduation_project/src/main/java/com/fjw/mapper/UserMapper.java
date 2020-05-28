package com.fjw.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.fjw.domain.User;
import com.fjw.domain.query.UserQuery;

@Mapper
public interface UserMapper {
	public void saveUser(User user);
	public User getUser(Long id);
	public List<User> listUser(RowBounds rowBounds);
	public List<User> queryUser(UserQuery query,RowBounds rowBounds);
	public void deleteUser(Long id);
	public void updateUser(User user);
	public User getUserByUsername(String username);
	public void bathDelete(Long[] ids);
	public void addRole(@Param("user_id")Long user_id,@Param("role_id")Long role_id);
	public void deleteRole(@Param("user_id")Long user_id,@Param("role_id")Long role_id);
	public Integer getCount(UserQuery query);
}
