package com.parishram.dao;

import java.util.List;

import com.parishram.dto.WishListDTO;

public interface WishListDao {
	public void addToWishList(WishListDTO wishListDTO);
	public boolean isWishListItemAlreadyExists(String itemName);
	public List<WishListDTO> getAllWishListedItems();
	public void removeFromWishList(WishListDTO wishListDTO);
}
