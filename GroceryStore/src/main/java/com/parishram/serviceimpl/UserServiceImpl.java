package com.parishram.serviceimpl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.parishram.dao.UserDao;
import com.parishram.dto.UserDTO;
import com.parishram.model.User;
import com.parishram.service.UserService;
@Component
public class UserServiceImpl implements UserService {
	private static Logger LOGGER = Logger.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDao userDao;

	@Override
	public User getRole(String userName, String password) {
		UserDTO userDto = userDao.getUserRole(userName, password);
		User user = new User();
		user.setUserName(userDto.getUserName());
		user.setRole(userDto.getRole());
		return user;

	}

}
