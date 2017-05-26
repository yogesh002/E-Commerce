package com.parishram.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.parishram.json.data.ItemData;
import com.parishram.model.WishList;
import com.parishram.service.WishListService;

@Controller
public class WishListController {
	@Autowired
	private WishListService wishListService;

	@RequestMapping(value = "/addToWishList", method = RequestMethod.POST)
	public @ResponseBody boolean addItemToWishList(@RequestBody ItemData itemRequestData) {
		WishList wishList = new WishList();
		wishList.setWishListItem(itemRequestData.getCategoryName());
		wishList.setWishListItemCategory(itemRequestData.getCategory());
		wishList.setWishListItemPrice(itemRequestData.getPrice());
		wishList.setWishListedDate(new Date());
		return wishListService.isItemAddedToWishList(wishList);
	}
	
	@RequestMapping(value = "/removeFromWishList", method = RequestMethod.POST)
	public @ResponseBody String removeWishListItem(@RequestBody ItemData itemRequestData) {
		WishList wishList = new WishList();
		wishList.setWishListItem(itemRequestData.getCategoryName());
		wishList.setWishListItemCategory(itemRequestData.getCategory());
		wishListService.removeFromWishList(wishList);
		return "success";
	}
}
