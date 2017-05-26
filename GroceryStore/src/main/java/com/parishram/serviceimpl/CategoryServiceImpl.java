package com.parishram.serviceimpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.parishram.dao.CategoryDao;
import com.parishram.service.CategoryService;

@Component
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryDao categoryDao;

	private static Logger LOGGER = Logger.getLogger(CategoryServiceImpl.class);

	@Override
	public List<String> getAllCategories() {
		return categoryDao.getAllCategories();
	}

}
