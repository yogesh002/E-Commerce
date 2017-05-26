<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin</title>
</head>
<body>
<div>Welcome ${user.userName}. You are currently logged in as <strong>${user.role}.</strong></div>
<c:choose>
<c:when test="${user.role == 'ADMIN' }">
	<div>
		<a href="prepareCategoriesToAdd">Add</a>
	</div>
	<div>
		<a href="prepareCategoriesToView">Edit Items</a>
	</div>
</c:when>
<c:otherwise>
	<div>
		<a href="prepareCategoriesToView">View Items</a>
	</div>
	<div>
		<a href="displayWishList">WishLists</a>
	</div>
</c:otherwise>
</c:choose>
	<div>
		<a href="logout">Logout</a>
	</div>
</body>
</html>