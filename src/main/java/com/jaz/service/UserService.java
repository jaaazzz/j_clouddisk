package com.jaz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.jaz.dao.mapper.UserMapper;
import com.jaz.dao.po.User;

@Service(value="userService")
public class UserService {
	
	@Autowired
	private UserMapper dao;

	public void createUser(User user) throws Exception{
		User real_user = findUser(user.getUsername());
		if(real_user == null)
		   dao.createUser(user);
		else
			throw new RuntimeException();
	}
	
	public String checkUser(User user ) throws Exception{
		return dao.checkUser(user);
	}
	
	public User findUser(String username) throws Exception{
		User found = dao.findUser(username);
		if(found==null)  return null;
		return found;
	}

//	public int isVip(String user_name)throws Exception {
//		return dao.isVip(user_name);
//	}
}
