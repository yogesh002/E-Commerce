package com.parishram.fish.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.parishram.Queries.CategoryQueries;
import com.parishram.dao.CategoryDao;

@Repository
public class CategoryDaoImpl implements CategoryDao {
	private static Logger LOGGER = Logger.getLogger(CategoryDaoImpl.class);

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<String> getAllCategories() {
		List<String> categories = jdbcTemplate.query(CategoryQueries.GET_CATEGORIES.getValue(), new RowMapper<String>() {
			List<String> categoriesList = new ArrayList<String>();

			@Override
			public String mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
				String category = resultSet.getString("CATEGORY_NAME");
				categoriesList.add(category);
				return category;
			}
		});
		return categories;

	}

}
