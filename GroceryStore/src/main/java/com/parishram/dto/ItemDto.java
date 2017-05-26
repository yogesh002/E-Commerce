package com.parishram.dto;

import java.math.BigDecimal;

public class ItemDto {
	private int id;
	private String category;
	private String name;
	private String originalName;
	private BigDecimal price;
	private short availableQuantity;
	private String image;
	private String description;

	public String getOriginalName() {
		return originalName;
	}

	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}

	public String getName() {
		return name;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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
