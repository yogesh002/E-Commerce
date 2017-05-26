package com.parishram.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.parishram.service.CategoryService;
import com.parishram.utils.ParishramUtils;

@Controller
public class GroceryController {
	@Autowired
	private CategoryService categoryService;

	@RequestMapping(value = "/prepareCategoriesToAdd", method = RequestMethod.GET)
	public ModelAndView prepareCategoriesToAdd() {
		List<String> categories = categoryService.getAllCategories();
		ModelAndView modelAndView = ParishramUtils.getModelAndView("additem");
		modelAndView.addObject("categories", categories);
		return modelAndView;
	}
	
	@RequestMapping(value = "/prepareCategoriesToView", method = RequestMethod.GET)
	public ModelAndView prepareCategoriesToView() {
		List<String> categories = categoryService.getAllCategories();
		ModelAndView modelAndView = ParishramUtils.getModelAndView("selectviewitem");
		modelAndView.addObject("categories", categories);
		return modelAndView;
	}
}
