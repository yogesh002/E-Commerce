package com.parishram.model;

import java.math.BigDecimal;

public class Item {
	private int id;
	private String name;
	private String originalName;
	private String category;
	private BigDecimal price;
	private short availableQuantity;
	private String thumbnail;
	private String description;
	private boolean isItemWishListed;
	// GETTERS AND SETTERS

	public String getName() {
		return name;
	}

	public String getOriginalName() {
		return originalName;
	}

	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}

	public void setItemWishListed(boolean isItemWishListed) {
		this.isItemWishListed = isItemWishListed;
	}

	public boolean getIsItemWishListed() {
		return isItemWishListed;
	}

	public void setIsItemWishListed(boolean isItemWishListed) {
		this.isItemWishListed = isItemWishListed;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public short getAvailableQuantity() {
		return availableQuantity;
	}

	public void setAvailableQuantity(short availableQuantity) {
		this.availableQuantity = availableQuantity;
	}

}
