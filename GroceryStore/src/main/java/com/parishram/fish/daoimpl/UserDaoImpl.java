package com.parishram.fish.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.parishram.Queries.UserQueries;
import com.parishram.dao.UserDao;
import com.parishram.dto.UserDTO;

@Repository
public class UserDaoImpl implements UserDao {
	private static Logger LOGGER = Logger.getLogger(UserDaoImpl.class);

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public UserDTO getUserRole(String userName, String password) {
		UserDTO user = jdbcTemplate.queryForObject(UserQueries.GET_USER_DETAIL.getValue(),
				new Object[] { userName, password }, new RowMapper<UserDTO>() {
					@Override
					public UserDTO mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
						UserDTO userDTO = new UserDTO();
						String role = resultSet.getString("ROLE");
						userDTO.setRole(role);
						String userName = resultSet.getString("USERNAME");
						userDTO.setUserName(userName);
						return userDTO;
					}

				});
		return user;

	}

}
