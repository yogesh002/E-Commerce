package com.parishram.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.parishram.dao.ItemDao;
import com.parishram.dto.ItemDto;
import com.parishram.model.Item;
import com.parishram.service.ItemService;

@Component
public class ItemServiceImpl implements ItemService {
	private static Logger LOGGER = Logger.getLogger(ItemServiceImpl.class);

	@Autowired
	private ItemDao itemDao;

	@Override
	public Item getItemDetailsUsingId(int id) {
		return null;
	}

	@Override
	public List<Item> getAllItemDetails(String category) throws SecurityException {
		List<ItemDto> itemDto = itemDao.getAllItem(category);
		List<Item> itemModelList = new ArrayList<Item>();
		for (ItemDto item : itemDto) {
			Item itemModel = new Item();
			itemModel.setCategory(item.getCategory());
			itemModel.setAvailableQuantity(item.getAvailableQuantity());
			itemModel.setId(item.getId());
			itemModel.setPrice(item.getPrice());
			itemModel.setThumbnail(item.getImage());
			itemModel.setDescription(item.getDescription());
			itemModel.setName(item.getName());
			itemModelList.add(itemModel);
		}
		return itemModelList;
	}

	@Override
	public void addNewItem(Item item) {
		ItemDto itemDto = new ItemDto();
		itemDto.setCategory(item.getCategory());
		itemDto.setName(item.getName());
		itemDto.setImage(item.getThumbnail());
		itemDto.setPrice(item.getPrice());
		itemDto.setDescription(item.getDescription());
		itemDto.setAvailableQuantity(item.getAvailableQuantity());
		itemDao.addNewItem(itemDto);
	}

	@Override
	public void updateItemDetails(Item item) {
		ItemDto itemDto = new ItemDto();
		itemDto.setImage(item.getThumbnail());
		itemDto.setPrice(item.getPrice());
		itemDto.setAvailableQuantity(item.getAvailableQuantity());
		itemDto.setName(item.getName());
		itemDto.setCategory(item.getCategory());
		itemDto.setOriginalName(item.getOriginalName());
		itemDao.updateItemDetails(itemDto);
	}

	@Override
	public void deleteItemDetails(Item item) {
		ItemDto itemDto = new ItemDto();
		itemDto.setName(item.getName());
		itemDto.setCategory(item.getCategory());
		itemDao.deleteItemDetails(itemDto);
	}
}
