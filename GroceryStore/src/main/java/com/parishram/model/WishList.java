package com.parishram.model;

import java.math.BigDecimal;
import java.util.Date;

public class WishList {

	private String wishListItem;
	private String wishListItemCategory;
	private BigDecimal wishListItemPrice;
	private Date wishListedDate;
	private String wishListImage;

	public String getWishListImage() {
		return wishListImage;
	}

	public void setWishListImage(String wishListImage) {
		this.wishListImage = wishListImage;
	}

	public String getWishListItem() {
		return wishListItem;
	}

	public void setWishListItem(String wishListItem) {
		this.wishListItem = wishListItem;
	}

	public String getWishListItemCategory() {
		return wishListItemCategory;
	}

	public void setWishListItemCategory(String wishListItemCategory) {
		this.wishListItemCategory = wishListItemCategory;
	}

	public BigDecimal getWishListItemPrice() {
		return wishListItemPrice;
	}

	public void setWishListItemPrice(BigDecimal wishListItemPrice) {
		this.wishListItemPrice = wishListItemPrice;
	}

	public Date getWishListedDate() {
		return wishListedDate;
	}

	public void setWishListedDate(Date wishListedDate) {
		this.wishListedDate = wishListedDate;
	}

}
