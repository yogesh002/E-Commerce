package com.parishram.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import com.parishram.model.Item;
import com.parishram.model.User;
import com.parishram.model.WishList;
import com.parishram.service.ItemService;
import com.parishram.service.UserService;
import com.parishram.service.WishListService;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

	@InjectMocks
	private UserController userController;

	@Mock
	private HttpServletRequest request;

	@Mock
	private WishListService wishListService;

	@Mock
	private ItemService itemService;

	@Mock
	private UserService userService;

	/**
	 * Tests all items in the grocery store
	 */
	@Test
	public void testItemDetailsBasedOnCategoryHappyPath() {
		List<Item> items = buildListOfItems();
		List<WishList> wishLists = buildWishListedItems();
		when(request.getParameter("category")).thenReturn("TEST");
		when(itemService.getAllItemDetails(request.getParameter("category"))).thenReturn(items);
		when(wishListService.getAllWishListedItems()).thenReturn(wishLists);
		ModelAndView modelAndView = userController.displayAllItemDetailsBasedOnCategory(request);
		assertNotNull(modelAndView);
		List<Item> itemsFromResponse = (List<Item>) modelAndView.getModelMap().get("itemsList");
		assertEquals(items, itemsFromResponse);
	}

	/**
	 * Test to make sure items are present in user's wishlist
	 */
	@Test
	public void testItemsThatAreWishListed() {
		List<Item> items = buildListOfItems();
		List<WishList> wishLists = buildWishListedItems();
		when(request.getParameter("category")).thenReturn("TEST");
		when(itemService.getAllItemDetails(request.getParameter("category"))).thenReturn(items);
		when(wishListService.getAllWishListedItems()).thenReturn(wishLists);
		ModelAndView modelAndView = userController.displayAllItemDetailsBasedOnCategory(request);
		assertNotNull(modelAndView);
		List<Item> itemsFromResponse = (List<Item>) modelAndView.getModelMap().get("itemsList");
		assertEquals(items, itemsFromResponse);
		assertTrue(itemsFromResponse.get(0).getIsItemWishListed());
		assertFalse(itemsFromResponse.get(1).getIsItemWishListed());
		assertEquals(wishLists.get(0).getWishListItemCategory(), items.get(0).getCategory());
	}

	/**
	 * Testing the scenario when category is empty
	 */
	@Test
	public void testItemDetailsWhenCategoryIsEmpty() {
		when(request.getParameter("category")).thenReturn("");
		ModelAndView modelAndView = userController.displayAllItemDetailsBasedOnCategory(request);
		assertNotNull(modelAndView);
		assertEquals(null, modelAndView.getModel().get("itemsList"));
	}

	/**
	 * Testing the scenario when item is null
	 */
	@Test
	public void testItemDetailsWhenItemIsNull() {
		when(request.getParameter("category")).thenReturn("TEST");
		when(itemService.getAllItemDetails(request.getParameter("category"))).thenReturn(null);
		ModelAndView modelAndView = userController.displayAllItemDetailsBasedOnCategory(request);
		assertNotNull(modelAndView);
		assertEquals(null, modelAndView.getModel().get("itemsList"));
	}

	/**
	 * Testing the scenario when item list is empty.
	 */
	@Test
	public void testItemDetailsWhenItemListIsEmpty() {
		when(request.getParameter("category")).thenReturn("TEST");
		when(itemService.getAllItemDetails(request.getParameter("category"))).thenReturn(new ArrayList<Item>());
		ModelAndView modelAndView = userController.displayAllItemDetailsBasedOnCategory(request);
		assertNotNull(modelAndView);
		List<Item> item = (List<Item>) modelAndView.getModel().get("itemsList");
		assertEquals(0, item.size());
	}

	/**
	 * Testing the scenario when the user does not have any wishlist
	 */
	@Test
	public void testItemDetailsWhenNoWishListPresent() {
		List<Item> items = buildListOfItems();
		when(request.getParameter("category")).thenReturn("TEST");
		when(itemService.getAllItemDetails(request.getParameter("category"))).thenReturn(items);
		when(wishListService.getAllWishListedItems()).thenReturn(null);
		ModelAndView modelAndView = userController.displayAllItemDetailsBasedOnCategory(request);
		assertNotNull(modelAndView);
		List<Item> itemsFromResponse = (List<Item>) modelAndView.getModel().get("itemsList");
		assertNotNull(itemsFromResponse);
		assertFalse(itemsFromResponse.get(0).getIsItemWishListed());
		assertFalse(itemsFromResponse.get(1).getIsItemWishListed());

	}

	/**
	 * Testing the scenario when the wishlist of user is empty
	 */
	@Test
	public void testItemDetailsWhenWishListIsEmpty() {
		List<Item> items = buildListOfItems();
		when(request.getParameter("category")).thenReturn("TEST");
		when(itemService.getAllItemDetails(request.getParameter("category"))).thenReturn(items);
		when(wishListService.getAllWishListedItems()).thenReturn(new ArrayList<WishList>());
		ModelAndView modelAndView = userController.displayAllItemDetailsBasedOnCategory(request);
		assertNotNull(modelAndView);
		List<Item> itemsFromResponse = (List<Item>) modelAndView.getModel().get("itemsList");
		assertNotNull(itemsFromResponse);
		assertFalse(itemsFromResponse.get(0).getIsItemWishListed());

	}

	/**
	 * Testing the scenario to show all the wishlisted items of a user
	 */
	@Test
	public void testDisplayWishListItems() {
		when(request.getParameter("category")).thenReturn("TEST");
		List<WishList> wishListItems = buildWishListedItems();
		when(wishListService.getAllWishListedItems()).thenReturn(wishListItems);
		ModelAndView modelAndView = userController.displayWishListItems(request);
		List<WishList> wishLists = (List<WishList>) modelAndView.getModel().get("wishlist");
		assertNotNull(wishLists);
		assertEquals(wishLists.get(0).getWishListItem(), "Salmon");
		assertEquals(wishLists.get(0).getWishListItemCategory(), "Fish");
	}

	/**
	 * Testing the scenario which shows how to handle the empty wishlist
	 */
	@Test
	public void testDisplayEmptyWishListItems() {
		when(request.getParameter("category")).thenReturn("TEST");
		when(wishListService.getAllWishListedItems()).thenReturn(new ArrayList<WishList>());
		ModelAndView modelAndView = userController.displayWishListItems(request);
		List<WishList> wishLists = (List<WishList>) modelAndView.getModel().get("wishlist");
		assertNotNull(wishLists);
		assertEquals(modelAndView.getViewName(), "user/wishlist");
	}

	/**
	 * Testing the scenario which shows how to handle the null wishlist
	 */
	@Test
	public void testDisplayNullWishListItems() {
		when(request.getParameter("category")).thenReturn("TEST");
		when(wishListService.getAllWishListedItems()).thenReturn(null);
		ModelAndView modelAndView = userController.displayWishListItems(request);
		List<WishList> wishLists = (List<WishList>) modelAndView.getModel().get("wishlist");
		assertNull(wishLists);
	}

	/**
	 * Testing the scenario for successful user login
	 */
	@Test
	public void testLoginUserHappyPath() {
		when(request.getParameter("username")).thenReturn("USERNAME");
		when(request.getParameter("password")).thenReturn("PASSWORD");
		User user = getUser();
		HttpSession session = mock(HttpSession.class);
		doNothing().when(session).setAttribute("user", user);
		when(userService.getRole((request.getParameter("username")), request.getParameter("password")))
				.thenReturn(user);
		ModelAndView modelAndView = userController.loginUser(request, session);
		assertEquals(modelAndView.getViewName(), "user");
		verify(session, times(1)).setAttribute("user", user);
	}

	/**
	 * Testing the scenario for user login when username is missing
	 */
	@Test
	public void testLoginWhenUserNameIsMissing() {
		when(request.getParameter("username")).thenReturn("");
		when(request.getParameter("password")).thenReturn("PASSWORD");
		HttpSession session = mock(HttpSession.class);
		ModelAndView modelAndView = userController.loginUser(request, session);
		assertTrue(modelAndView.getViewName().equalsIgnoreCase("index"));
		String errorMessage = (String) modelAndView.getModel().get("errorMessage");
		assertNotNull(errorMessage);
		assertEquals(errorMessage, "Either username or password is empty.");
	}

	/**
	 * Testing the scenario for user login when password is missing
	 */
	@Test
	public void testLoginWhenPasswordIsMissing() {
		when(request.getParameter("username")).thenReturn("USERNAME");
		when(request.getParameter("password")).thenReturn("");
		HttpSession session = mock(HttpSession.class);
		ModelAndView modelAndView = userController.loginUser(request, session);
		assertTrue(modelAndView.getViewName().equalsIgnoreCase("index"));
		String errorMessage = (String) modelAndView.getModel().get("errorMessage");
		assertNotNull(errorMessage);
		assertEquals(errorMessage, "Either username or password is empty.");
	}

	/**
	 * Testing the scenario when user logs out
	 */
	@Test
	public void testLogoutUser() {
		HttpSession session = mock(HttpSession.class);
		doNothing().when(session).invalidate();
		ModelAndView modelAndView = userController.logoutUser(session);
		assertEquals(modelAndView.getViewName(), "index");
		assertNotNull(modelAndView);
		verify(session, times(1)).invalidate();
	}

	/**
	 * Testing the logout scenario when {@code session} is null
	 */
	@Test
	public void testLogoutUserIfSessionIsNull() {
		HttpSession session = mock(HttpSession.class);
		ModelAndView modelAndView = userController.logoutUser(null);
		assertEquals(modelAndView.getViewName(), "index");
		verify(session, never()).invalidate();
	}

	/**
	 * Testing the scenario to go to home page
	 */
	@Test
	public void testGoToMainPage() {
		ModelAndView modelAndView = userController.goToMainPage();
		assertNotNull(modelAndView);
		assertEquals(modelAndView.getViewName(), "user");

	}

	/**
	 * This method builds list of items available in the grocery store
	 * 
	 * @return items The {@code items} represent all the available items that
	 *         are present in the grocery store
	 */
	private List<Item> buildListOfItems() {
		List<Item> items = new ArrayList<Item>();
		Item itemSalmon = new Item();
		itemSalmon.setCategory("Fish");
		itemSalmon.setName("Salmon");
		Item itemTuna = new Item();
		itemTuna.setCategory("Fish");
		itemTuna.setName("Tuna");
		items.add(itemSalmon);
		items.add(itemTuna);
		return items;
	}

	/**
	 * This method builds list of items available in the user's wishlist
	 * 
	 * @return items The {@code items} represent all the wishlisted items of a
	 *         user
	 * 
	 */
	private List<WishList> buildWishListedItems() {
		List<WishList> items = new ArrayList<WishList>();
		WishList wishList = new WishList();
		wishList.setWishListItem("Salmon");
		wishList.setWishListItemCategory("Fish");
		items.add(wishList);
		return items;
	}

	/**
	 * This method builds user details
	 * 
	 * @return user The user who logs in to the system
	 * 
	 */
	private User getUser() {
		User user = new User();
		user.setUserName("USERNAME");
		user.setRole("USER");
		return user;
	}

}
