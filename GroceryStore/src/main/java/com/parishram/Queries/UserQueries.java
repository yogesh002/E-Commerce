package com.parishram.Queries;

public enum UserQueries {
	GET_USER_DETAIL("SELECT * FROM LOGIN WHERE USERNAME = ? AND PASSWORD = ?");

	private String query;

	UserQueries(String query) {
		this.query = query;
	}

	public String getValue() {
		return query;
	}
}
