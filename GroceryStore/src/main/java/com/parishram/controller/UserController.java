package com.parishram.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.parishram.model.Item;
import com.parishram.model.User;
import com.parishram.model.WishList;
import com.parishram.service.ItemService;
import com.parishram.service.UserService;
import com.parishram.service.WishListService;

/**
 * The main aim of this class is to allow the user to log in and log out of the
 * system. Once the user logs in, he can view all the items that are available
 * in the grocery store. If the user has wishlisted some items of a particular
 * category, they will also be displayed as wishlisted items. In addition, the
 * user can go to his wishlist section to view only wishlisted items.
 * 
 * @author Yogesh Ghimire
 *
 */
@Controller
public class UserController {
	private static Logger LOGGER = Logger.getLogger(UserController.class);

	@Autowired
	private ItemService itemService;

	@Autowired
	private WishListService wishListService;

	@Autowired
	private UserService userService;

	/**
	 * The main aim of this method is to display all the items based on a
	 * particular category that user selected. If these items appear in the
	 * <bold>wishlist</bold> table, they are marked as wishlisted. All the items
	 * including wishlisted items are sent to the {@code JSP} file to display
	 * their details.
	 * 
	 * @param request
	 *            The {@code HttpServletRequest} instance
	 * @return modelAndView The {@code modelAndView} is used in {@code JSP} to
	 *         display all the item details. A user might wishlist several items
	 *         at a time.
	 */

	@RequestMapping(value = "/showItemDetails", method = RequestMethod.POST)
	public ModelAndView displayAllItemDetailsBasedOnCategory(HttpServletRequest request) {
		String category = request.getParameter("category");
		List<Item> items = null;
		if (StringUtils.isNotBlank(category)) {
			items = itemService.getAllItemDetails(category);
			if (items != null && items.size() > 0) {
				checkIfItemIsPresentInWishList(items);
			} else {
				LOGGER.debug("No item is currently present.");
			}
		} else {
			LOGGER.error("Category is missing. Please make sure you select a valid Category.");
		}
		ModelAndView modelAndView = new ModelAndView("item");
		modelAndView.addObject("itemsList", items);
		return modelAndView;
	}

	/**
	 * This method retrieves all the items that are present in the grocery
	 * store. The user's wishlisted items are then matched with those items. If
	 * the items are present in wishlist, it is marked as wishlisted items.
	 * 
	 * @param items
	 *            The list of items that are available in the grocery store
	 */
	private void checkIfItemIsPresentInWishList(List<Item> items) {
		List<WishList> wishLists = wishListService.getAllWishListedItems();
		if (wishLists != null && wishLists.size() > 0) {
			for (Item item : items) {
				for (WishList wishList : wishLists) {
					markItemAsWishListedForParticularCategory(item, wishList);
				}
			}
		} else {
			LOGGER.debug("User has no item in his wishlist.");
		}
	}

	/**
	 * This method makes sure that the category of {@code item} and the category
	 * of {@code wishList} is same. It also makes sure that the name of
	 * {@code item} and {@code WishList} is same. If the details match, the item
	 * is marked as a wishlisted item under a particular category for a user.
	 * 
	 * @param item
	 *            It represents an item of a particular category that is present
	 *            in the <bold>item</bold> table.
	 * @param wishList
	 *            It represents an item of a particular category that the user
	 *            wishlisted.
	 * 
	 */
	private void markItemAsWishListedForParticularCategory(Item item, WishList wishList) {
		if (item.getCategory().equalsIgnoreCase(wishList.getWishListItemCategory())
				&& (item.getName().equalsIgnoreCase(wishList.getWishListItem()))) {
			item.setIsItemWishListed(true);
			LOGGER.debug(String.format("Item %s is wishlisted in Category : %s", item, item.getCategory()));
		}
	}

	/**
	 * The main aim of this method is to show all the items of a particular
	 * category which user wishlisted. It does not display the items which are
	 * not wishlisted by the user.
	 * 
	 * @param request
	 *            The {@code HttpServletRequest} instance
	 * @return modelAndView The {@code modelAndView} contains all wishlisted
	 *         items of a particular category of a user. These items will be
	 *         displayed later in {@code JSP}
	 */
	@RequestMapping(value = "/displayWishList", method = RequestMethod.GET)
	public ModelAndView displayWishListItems(HttpServletRequest request) {
		List<WishList> wishLists = wishListService.getAllWishListedItems();
		ModelAndView modelAndView = new ModelAndView("user/wishlist");
		modelAndView.addObject("wishlist", wishLists);
		return modelAndView;
	}

	/**
	 * This method retrieves the role of a user based on username and password.
	 * 
	 * @param userName
	 *            The username that user entered during login.
	 * @param password
	 *            The password that user entered during login.
	 * @param session
	 *            The {@code HttpSession} that stores the user details in a
	 *            session. The details are later retrieved from the
	 *            {@code session} to display in {@code JSP}
	 * @return modelAndView
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView loginUser(HttpServletRequest request, HttpSession session) {
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		ModelAndView modelAndView = null;
		if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
			LOGGER.error("Either username or password is empty.");
			modelAndView = new ModelAndView("index");
			modelAndView.addObject("errorMessage", "Either username or password is empty.");
		} else {
			User user = userService.getRole(userName, password);
			session.setAttribute("user", user);
			modelAndView = new ModelAndView("user");
		}
		return modelAndView;
	}

	/**
	 * The main aim of this method is to log out the user.
	 * 
	 * @param session
	 *            The {@code HttpSession} is invalidated
	 * @return {@code ModelAndView}
	 * 
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logoutUser(HttpSession session) {
		if (session != null) {
			session.invalidate();
		}
		return new ModelAndView("index");
	}

	/**
	 * The main aim of this method take the user to the index page or the main
	 * page.
	 * 
	 * @return {@code ModelAndView}
	 * 
	 */
	@RequestMapping(value = "/mainPage", method = RequestMethod.GET)
	public ModelAndView goToMainPage() {
		return new ModelAndView("user");
	}

}
