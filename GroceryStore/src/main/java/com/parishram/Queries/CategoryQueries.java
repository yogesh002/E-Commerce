package com.parishram.Queries;

public enum CategoryQueries {
	FISH("FISH"), DAIRY("DAIRY"), VEGETABLE("VEGETABLE"), MEAT("MEAT"),
	GET_CATEGORIES("SELECT * FROM CATEGORY");
	private String type;

	CategoryQueries(String category) {
		type = category.toString();
	}

	public String getValue() {
		return type;
	}
}
