<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="./css/bootstrap.min.css">
<link rel="stylesheet" href="./css/bootstrap.min.css">
<link rel="stylesheet" href="./css/font-awesome.css">
<link rel="stylesheet" href="./css/item.css">
<script src="./js/lib/jquery.min.js"></script>
<script src="./js/edit.js"></script>
<script src="./js/customer/wishlist.js"></script>
<title>Item Page</title>
</head>
<body class="container">
<c:choose>
<c:when test="${user.role == 'ADMIN'}">
<div>
<form action="updateItemDetails" method="POST" id="itemForm" enctype="multipart/form-data">
	<div class="row heading">
		<div class="col-md-4">Image</div>
		<div class="col-md-2">Price</div>
		<div class = "col-md-1">Quantity</div>
		<div class ="col-md-2">Type</div>
	</div>
	<div>
		<a href="prepareCategoriesToView">Back</a>
	</div>
	<div>
		<c:forEach var="item" items="${itemsList}">
	<div class="row wrapper">
		<div class="col-md-4 image"><img class="img-circle image" alt="ABOUT_PIC" src="${item.thumbnail}"/>
			<div class="thumbnailupdatebtn">
				<input type="file" name="updatethumbnail" value="Upload Image">
			</div>
		</div>
		<div class="price col-md-2"><span class='currency'>$</span>${item.price}</div>
		<div class="quantity col-md-1">${item.availableQuantity}</div>
		<div class="type col-md-2">${item.name}</div>
		<div class="editButton col-md-1"><input type="button" value="Edit" class="editBtn btn btn-info" onclick="editSelectedItem('${item.category}',this)"></div>
		<div class="deleteButton col-md-1"><input type="button" value="Delete" class="deleteBtn btn btn-danger" onclick="deleteSelectedItem('${item.category}',this)"></div>
		<div class="cancelButton col-md-1"><input type="button" value="Cancel" class="cancelBtn btn btn-default" onclick="cancelSelectedAction(this)"></div>
	</div>
	</c:forEach>
	</div>
</form>
</div>
</c:when>
<c:when test="${user.role == 'USER'}">
<div class="item_header">
	<h1>Welcome to Ghimire Store</h1>
</div>
<div>
	<a href="prepareCategoriesToView">Back</a>
</div>
<section>
	<div class="row cust_wrapper">
		<div>
			<c:forEach var="item" items="${itemsList}">
				<div class="col-md-6 cust_item">
					<div class="itemName_cust">${item.name}</div>
					<div class="image_cust"><img class="" alt="ABOUT_PIC" src="${item.thumbnail}" /></div>
					<div class="price_cust"><span id="currency">$</span>${item.price}</div>
					<div class="desc_cust">${item.description}</div>
					<div class="icon">
						<span class="shoppingCartIcon"><i class="fa fa-shopping-cart" aria-hidden="true"></i></span>
					<c:choose>
					<c:when test="${item.isItemWishListed == true}">
						<span class="wishListIcon_taken"><i class="fa fa-heart" aria-hidden="true"></i></span>
					</c:when>
					<c:otherwise>
						<span class="wishListIcon"><i class="fa fa-heart" aria-hidden="true" onclick="addToWishList('${item.name}', '${item.price}', '${item.category}', this)"></i></span>
					</c:otherwise>
					</c:choose>
					<div class="message"></div>
					</div>
				</div>			
			</c:forEach>
		</div>
	</div>
</section>
</c:when>
</c:choose>
</body>
</html>