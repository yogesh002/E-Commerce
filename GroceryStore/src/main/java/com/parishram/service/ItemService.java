package com.parishram.service;

import java.util.List;

import com.parishram.model.Item;

public interface ItemService {
	public Item getItemDetailsUsingId(int id);
	public List<Item> getAllItemDetails(String category);
	public void addNewItem(Item item);
	public void updateItemDetails(Item item);
	public void deleteItemDetails(Item item);
}
