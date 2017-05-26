package com.parishram.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.parishram.Queries.CategoryQueries;
import com.parishram.json.data.ItemData;
import com.parishram.model.Item;
import com.parishram.service.ItemService;
import com.parishram.utils.ImageBuilder;
import com.parishram.utils.ParishramUtils;

@Controller
public class AdminController {
	private static Logger LOGGER = Logger.getLogger(AdminController.class);

	@Autowired
	private ItemService itemService;

	@RequestMapping(value = "/viewCategories", method = RequestMethod.GET)
	public ModelAndView addNewCategory() {
		ModelAndView modelAndView = ParishramUtils.getModelAndView("edititem");
		return modelAndView;
	}

	@RequestMapping(value = "/addNewItem/fish", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView addNewFish(HttpServletRequest request, @ModelAttribute("item") final Item item) {
		ModelAndView modelAndView = null;
		try {
			itemService.addNewItem(item);
			modelAndView = ParishramUtils.getModelAndView("success");
			modelAndView.addObject("fishStatus", "success");
		} catch (Exception exception) {
			LOGGER.error("There was a problem while adding new item. Please try again later." + exception.getMessage(),
					exception);
		}
		return modelAndView;
	}

	@RequestMapping(value = "/deleteItem", method = RequestMethod.POST)
	public String deleteItem(@RequestBody ItemData data) {
		Item item = new Item();
		String status = "";
		try {
			item.setCategory(data.getCategory());
			item.setName(data.getCategoryName());
			itemService.deleteItemDetails(item);
			status = "success";
		} catch (Exception exception) {
			status = "";
		}
		return status;

	}

	@RequestMapping(value = "/addNewItem", method = RequestMethod.POST)
	public String addNewItem(HttpServletRequest request, final RedirectAttributes redirectAttributes) {
		Item item = new Item();
		List<String> params = new ArrayList<String>();
		params.add("category");
		params.add("quantity");
		params.add("price");
		params.add("name");
		params.add("description");
		try {
			String category = "";
			ImageBuilder.handleMultipartFormUpload(request, params);
			List<String> images = ImageBuilder.getImages();
			item.setThumbnail(images.get(0));
			List<HashMap<String, String>> paramNameValues = ImageBuilder.getParamNameValue();
			for (HashMap<String, String> paramMap : paramNameValues) {
				if (paramMap.containsKey("category")) {
					category = paramMap.get("category");
					item.setCategory(category);
				}
				if (paramMap.containsKey("quantity")) {
					short quantity = Short.valueOf(paramMap.get("quantity"));
					item.setAvailableQuantity(quantity);
				}
				if (paramMap.containsKey("price")) {
					item.setPrice(new BigDecimal(paramMap.get("price")));
				}
				if (paramMap.containsKey("name")) {
					item.setName(paramMap.get("name"));
				}
				if (paramMap.containsKey("description")) {
					item.setDescription(paramMap.get("description"));
				}
			}
			redirectAttributes.addFlashAttribute("item", item);
			if (category.toUpperCase().equals(CategoryQueries.FISH.getValue())) {
				return ("redirect:/addNewItem/fish");
			} else if (category.toUpperCase().equals(CategoryQueries.VEGETABLE.getValue())) {
				return ("redirect:/addNewItem/vegetable");
			} else if (category.toUpperCase().equals(CategoryQueries.DAIRY.getValue())) {
				return ("redirect:/addNewItem/dairy");
			} else if (category.toUpperCase().equals(CategoryQueries.MEAT.getValue())) {
				return ("redirect:/addNewItem/meat");
			} else {
				return ("index");
			}

		} catch (Exception exception) {
			LOGGER.error("Unable to parse the uploaded form. Please try again later" + exception.getMessage(),
					exception);
		}
		return null;
	}

	@RequestMapping(value = "/updateItemDetails", method = RequestMethod.POST)
	public @ResponseBody Item updateItemDetails(HttpServletRequest request) {
		Item item = new Item();
		try {
			List<String> parameters = new ArrayList<String>();
			parameters.add("price");
			parameters.add("quantity");
			parameters.add("name");
			parameters.add("category");
			parameters.add("originalName");
			ImageBuilder.handleMultipartFormUpload(request, parameters);
			List<String> images = ImageBuilder.getImages();
			item.setThumbnail(images.get(0));
			List<HashMap<String, String>> paramNameValues = ImageBuilder.getParamNameValue();
			for (HashMap<String, String> paramMap : paramNameValues) {
				if (paramMap.containsKey("price")) {
					String price = paramMap.get("price");
					item.setPrice(new BigDecimal(price));
				}
				if (paramMap.containsKey("quantity")) {
					String quantity = paramMap.get("quantity");
					item.setAvailableQuantity(Short.valueOf(quantity));
				}
				if (paramMap.containsKey("name")) {
					String name = paramMap.get("name");
					item.setName(name);
				}
				if (paramMap.containsKey("category")) {
					String category = paramMap.get("category");
					item.setCategory(category);
				}
				if (paramMap.containsKey("originalName")) {
					String originalName = paramMap.get("originalName");
					item.setOriginalName(originalName);
				}
			}
			itemService.updateItemDetails(item);
			return item;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView goToHome(HttpServletRequest request) {
		ModelAndView modelAndView = ParishramUtils.getModelAndView("index");
		return modelAndView;
	}

	@RequestMapping(value = "/viewItems", method = RequestMethod.GET)
	public ModelAndView viewAllItems() {
		ModelAndView modelAndView = ParishramUtils.getModelAndView("index");
		return modelAndView;
	}

}
