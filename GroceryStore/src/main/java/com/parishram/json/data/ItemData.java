package com.parishram.json.data;

import java.io.Serializable;
import java.math.BigDecimal;

public class ItemData implements Serializable{
	private String categoryName;
	private String originalCategoryName;
	private String category;
	private BigDecimal price;
	private String image;
	private int availableQuantity;
	
	public String getOriginalCategoryName() {
		return originalCategoryName;
	}

	public void setOriginalCategoryName(String originalCategoryName) {
		this.originalCategoryName = originalCategoryName;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getAvailableQuantity() {
		return availableQuantity;
	}

	public void setAvailableQuantity(int availableQuantity) {
		this.availableQuantity = availableQuantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
