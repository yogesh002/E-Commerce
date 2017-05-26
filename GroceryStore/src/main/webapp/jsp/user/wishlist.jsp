<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="./css/bootstrap.min.css">
<link rel="stylesheet" href="./css/wishlist.css">
<script src="./js/lib/jquery.min.js"></script>
<script src="./js/lib/bootstrap.min.js"></script>
<script src="./js/customer/wishlist.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WishLists</title>
</head>
<body class="container">
<div class="row">
	<div class="header">
		<h1>Welcome to WishList Section - ${user.userName}</h1>
	</div>
	<div>
		<a href="prepareCategoriesToView">View Items</a>
	</div>
	<div>
		<a href="logout">Logout</a>
	</div>
	<c:if test="${empty wishlist}">
		<h3>Your WishList is currently empty. Checkout the new items and add it to your wishlist.</h3>
		<div>
			<a href="prepareCategoriesToView">Click Here.</a>
		</div>
	</c:if>
		<c:forEach items="${wishlist}" var="wislistItem">
		<div class="wishListWrapper col-md-3">
			<div>
				<span class="wishlist_title_name">Item:</span> 
				<span class="wishlist_title_value">${wislistItem.wishListItem}</span>
				<span><input type="button" value="Remove" class="btn btn-sm btn-danger" onclick="removeItemFromWishList('${wislistItem.wishListItem}', '${wislistItem.wishListItemCategory}', this)"></span>
			</div>
			 <div>
			 	<span class="wishlist_img"><img src="${wislistItem.wishListImage}" alt="IMAGE"/></span>
			 </div>
			<div>
				<span class="wishlist_title_name">Category:</span>
				<span class="wishlist_title_value">${wislistItem.wishListItemCategory}</span>
			</div>
			<div>
				<span class="wishlist_title_name">Price :</span>
				<span class="wishlist_title_value">${wislistItem.wishListItemPrice}</span>
			</div>	
			<div>
				<input type="button" value="CheckOut" class="btn btn-info">
			</div>
			</div> 
		</c:forEach>
	</div>
	<div class="message"></div>
</body>
</html>