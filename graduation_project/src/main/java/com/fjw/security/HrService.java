/*package com.fjw.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fjw.mapper.UserMapper;

import lombok.Setter;

@Service
public class HrService implements UserDetailsService{
	
	@Setter@Autowired@Lazy
	private UserMapper userMapper;
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userMapper.getUserByUsername(username);
	}

}
*/