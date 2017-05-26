package com.parishram.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.parishram.dao.WishListDao;
import com.parishram.dto.WishListDTO;
import com.parishram.model.WishList;
import com.parishram.service.WishListService;

@Component
public class WishListServiceImpl implements WishListService {
	private static Logger LOGGER = Logger.getLogger(WishListServiceImpl.class);
	@Autowired
	private WishListDao wishListDao;

	@Override
	public boolean isItemAddedToWishList(WishList wishList) {
		boolean itemAdded = false;
		WishListDTO wishListDto = new WishListDTO();
		wishListDto.setWishListItem(wishList.getWishListItem());
		wishListDto.setWishListItemCategory(wishList.getWishListItemCategory());
		wishListDto.setWishListItemPrice(wishList.getWishListItemPrice());
		wishListDto.setWishListedDate(wishList.getWishListedDate());
		boolean isItemExist = wishListDao.isWishListItemAlreadyExists(wishList.getWishListItem());
		if (!isItemExist) {
			wishListDao.addToWishList(wishListDto);
			itemAdded = true;
		}
		return itemAdded;
	}

	@Override
	public List<WishList> getAllWishListedItems() {
		List<WishListDTO> wishListDto = wishListDao.getAllWishListedItems();
		List<WishList> wishLists = new ArrayList<WishList>();
		for (WishListDTO wishListItem : wishListDto) {
			WishList wishList = new WishList();
			wishList.setWishListItem(wishListItem.getWishListItem());
			wishList.setWishListItemCategory(wishListItem.getWishListItemCategory());
			wishList.setWishListItemPrice(wishListItem.getWishListItemPrice());
			wishList.setWishListImage(wishListItem.getWishListImage());
			wishLists.add(wishList);
		}
		return wishLists;
	}

	@Override
	public void removeFromWishList(WishList wishList) {
		WishListDTO dto = new WishListDTO();
		dto.setWishListItem(wishList.getWishListItem());
		dto.setWishListItemCategory(wishList.getWishListItemCategory());
		wishListDao.removeFromWishList(dto);
	}
}
