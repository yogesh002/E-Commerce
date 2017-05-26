package com.parishram.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.parishram.model.Item;
import com.parishram.model.User;
import com.parishram.model.WishList;
import com.parishram.service.ItemService;
import com.parishram.service.UserService;
import com.parishram.service.WishListService;
import com.parishram.utils.ParishramUtils;

@Controller
public class UserController {
	private static Logger LOGGER = Logger.getLogger(UserController.class);

	@Autowired
	private ItemService itemService;

	@Autowired
	private WishListService wishListService;
	
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/showItemDetails", method = RequestMethod.POST)
	public ModelAndView displayAllItemDetailsBasedOnCategory(HttpServletRequest request) {
		List<Item> items = itemService.getAllItemDetails(request.getParameter("category"));
		List<WishList> wishLists = wishListService.getAllWishListedItems();
		for (Item item : items) {
			for (WishList wishList : wishLists) {
				if (item.getCategory().equalsIgnoreCase(wishList.getWishListItemCategory())
						&& (item.getName().equalsIgnoreCase(wishList.getWishListItem()))) {
					item.setIsItemWishListed(true);
				}
			}
		}
		LOGGER.debug("Currently we have following items available: " + items);
		ModelAndView modelAndView = ParishramUtils.getModelAndView("item");
		modelAndView.addObject("itemsList", items);
		return modelAndView;
	}
	
	@RequestMapping(value = "/displayWishList", method = RequestMethod.GET)
	public ModelAndView displayWishListItems(HttpServletRequest request) {
		List<WishList> wishLists = wishListService.getAllWishListedItems();
		ModelAndView modelAndView = ParishramUtils.getModelAndView("user/wishlist");
		modelAndView.addObject("wishlist", wishLists);
		return modelAndView;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView loginUser(@RequestParam("username") String userName, @RequestParam("password") String password, HttpSession session) {
		//TODO: validation
		User user = userService.getRole(userName, password);
		ModelAndView modelAndView = ParishramUtils.getModelAndView("user");
		session.setAttribute("user", user);
		return modelAndView;
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logoutUser(HttpSession session) {
		if(session != null){
			session.invalidate();
		}
		return new ModelAndView("index");
	}
	
	@RequestMapping(value = "/mainPage", method = RequestMethod.GET)
	public ModelAndView goToMainPage() {
		return new ModelAndView("user");
	}

	
}
