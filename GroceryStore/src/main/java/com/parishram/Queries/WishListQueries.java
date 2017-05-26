package com.parishram.Queries;

public enum WishListQueries {
	ADD_ITEM_TO_WISHLIST("INSERT INTO WISHLIST VALUES(?,?,?,?)"),
	REMOVE_ITEM_FROM_WISHLIST("DELETE FROM WISHLIST WHERE CATEGORY_NAME = ? AND CATEGORY = ?"),
	CHECK_IF_ITEM_EXISTS_IN_WISHLIST("SELECT CATEGORY_NAME FROM WISHLIST WHERE CATEGORY_NAME LIKE(:name)"),
	GET_ALL_WISHLIST_ITEMS("SELECT w.*, i.THUMBNAIL FROM WISHLIST w INNER JOIN ITEM i ON w.CATEGORY_NAME = i.NAME");

	private String query;

	WishListQueries(String query) {
		this.query = query;
	}

	public String getValue() {
		return query;
	}
}
