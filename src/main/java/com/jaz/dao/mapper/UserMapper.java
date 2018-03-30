package com.jaz.dao.mapper;

import com.jaz.dao.po.User;

public interface UserMapper {
	
	public void createUser(User user) throws Exception;
	public String checkUser(User user) throws Exception;
	public User findUser(String username) throws Exception;
	//public Integer isVip(String user_name)throws Exception;
	
}

