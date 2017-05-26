package com.parishram.dao;

import com.parishram.dto.UserDTO;

public interface UserDao {
	public UserDTO getUserRole( String userName, String password);
}
