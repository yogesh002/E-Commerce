
package com.parishram.Queries;

public enum ItemQueries {

	GET_ALL_FISH_DETAILS("SELECT * FROM ITEM"),
	UPDATE_ITEM("UPDATE ITEM SET THUMBNAIL = ?, PRICE = ?, AVAILABLE = ?, NAME = ? WHERE CATEGORY =? and NAME =?"),
	DELETE_ITEM("DELETE FROM ITEM WHERE CATEGORY = ? AND NAME = ?"),
	INSERT_NEW_ITEM("INSERT INTO ITEM VALUES(DEFAULT, ?,?,?,?,?,?)");

	private String query;

	ItemQueries(String query) {
		this.query = query;
	}

	public String getValue() {
		return query;
	}
}
