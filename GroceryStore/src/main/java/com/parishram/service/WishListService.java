package com.parishram.service;

import java.util.List;

import com.parishram.model.WishList;

public interface WishListService {
	public boolean isItemAddedToWishList(WishList wishList);
	public  List<WishList> getAllWishListedItems();
	public void removeFromWishList(WishList wishList);
}
