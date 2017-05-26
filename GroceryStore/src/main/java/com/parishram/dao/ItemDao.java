package com.parishram.dao;

import java.util.List;

import com.parishram.dto.ItemDto;

public interface ItemDao {
	public List<ItemDto> getAllItem(String category);
	public void addNewItem(ItemDto itemDto);
	public void updateItemDetails(ItemDto itemDto);
	public void deleteItemDetails(ItemDto itemDto);
}
