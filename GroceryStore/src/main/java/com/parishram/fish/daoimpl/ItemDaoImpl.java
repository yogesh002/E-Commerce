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
import com.parishram.Queries.ItemQueries;
import com.parishram.dao.ItemDao;
import com.parishram.dto.ItemDto;

@Repository
public class ItemDaoImpl implements ItemDao {
	private static Logger LOGGER = Logger.getLogger(ItemDaoImpl.class);

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<ItemDto> getAllItem(String category) {
		String query = "";
		if (category.equalsIgnoreCase(CategoryQueries.FISH.getValue())) {
			query = ItemQueries.GET_ALL_FISH_DETAILS.getValue();
		}
		List<ItemDto> result = jdbcTemplate.query(query, new RowMapper<ItemDto>() {
			List<ItemDto> itemList = new ArrayList<ItemDto>();

			@Override
			public ItemDto mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
				ItemDto item = new ItemDto();
				item.setId(resultSet.getInt("ID"));
				item.setCategory(resultSet.getString("CATEGORY"));
				item.setName(resultSet.getString("NAME"));
				item.setPrice(resultSet.getBigDecimal("PRICE"));
				item.setAvailableQuantity(resultSet.getShort("AVAILABLE"));
				item.setDescription(resultSet.getString("DESCRIPTION"));
				item.setImage(resultSet.getString("THUMBNAIL"));
				itemList.add(item);
				return item;
			}
		});
		return result;
	}

	@Override
	public void addNewItem(ItemDto item) {
		String category = item.getCategory();
		if (category.toUpperCase().equals(CategoryQueries.FISH.getValue())) {
			jdbcTemplate.update(ItemQueries.INSERT_NEW_ITEM.getValue(), item.getCategory(), item.getName(),
					item.getPrice(), item.getAvailableQuantity(), item.getImage(), item.getDescription());
		}
	}

	@Override
	public void updateItemDetails(ItemDto itemDto) {
		jdbcTemplate.update(ItemQueries.UPDATE_ITEM.getValue(), itemDto.getImage(), itemDto.getPrice(),
				itemDto.getAvailableQuantity(), itemDto.getName(), itemDto.getCategory(), itemDto.getOriginalName());
	}

	@Override
	public void deleteItemDetails(ItemDto itemDto) {
		jdbcTemplate.update(ItemQueries.DELETE_ITEM.getValue(), itemDto.getCategory(), itemDto.getName());
	}
}
