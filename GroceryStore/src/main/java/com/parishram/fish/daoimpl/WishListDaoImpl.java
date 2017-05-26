package com.parishram.fish.daoimpl;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.parishram.Queries.WishListQueries;
import com.parishram.dao.WishListDao;
import com.parishram.dto.WishListDTO;

@Repository
public class WishListDaoImpl implements WishListDao {
	private static Logger LOGGER = Logger.getLogger(WishListDaoImpl.class);

	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParamJdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public void addToWishList(WishListDTO wishListDTO) {
		jdbcTemplate.update(WishListQueries.ADD_ITEM_TO_WISHLIST.getValue(), wishListDTO.getWishListItemCategory(),
				wishListDTO.getWishListItem(), wishListDTO.getWishListItemPrice(), wishListDTO.getWishListedDate());

	}

	@Override
	public boolean isWishListItemAlreadyExists(String itemName) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", "%" + itemName + "%");
		List<String> results = namedParamJdbcTemplate.query(WishListQueries.CHECK_IF_ITEM_EXISTS_IN_WISHLIST.getValue(),
				params, new RowMapper<String>() {
					@Override
					public String mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
						String itemName = resultSet.getString("CATEGORY_NAME");
						return itemName;
					}
				});
		return results.size() != 0 ? true : false;
	}

	@Override
	public List<WishListDTO> getAllWishListedItems() {
		List<WishListDTO> wishLists = jdbcTemplate.query(WishListQueries.GET_ALL_WISHLIST_ITEMS.getValue(),
				new Object[] {}, new RowMapper<WishListDTO>() {
					@Override
					public WishListDTO mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
						WishListDTO wishListDto = new WishListDTO();
						wishListDto.setWishListItem(resultSet.getString("CATEGORY_NAME"));
						wishListDto.setWishListItemCategory(resultSet.getString("CATEGORY"));
						wishListDto.setWishListItemPrice(new BigDecimal(resultSet.getString("PRICE")));
						wishListDto.setWishListItem(resultSet.getString("CATEGORY_NAME"));
						wishListDto.setWishListImage(resultSet.getString("THUMBNAIL"));
						return wishListDto;
					}

				});
		return wishLists;
	}

	@Override
	public void removeFromWishList(WishListDTO wishListDTO) {
		jdbcTemplate.update(WishListQueries.REMOVE_ITEM_FROM_WISHLIST.getValue(), wishListDTO.getWishListItem(), wishListDTO.getWishListItemCategory());

	}
}
