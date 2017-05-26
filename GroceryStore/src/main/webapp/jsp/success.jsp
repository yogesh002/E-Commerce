<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Item Added</title>
</head>
<body>
	<c:if test="${fishStatus != null}">
		<h1>Fish added successfully!</h1>
	</c:if>
	<div>
		<a href="../prepareCategoriesToAdd">Add</a>
	</div>
	<div>
		<a href="../prepareCategoriesToView">Edit Items</a>
	</div>
	<div>
		<a href="../logout">Logout</a>
	</div>
</body>
</html>