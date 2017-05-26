<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit</title>
</head>
<body class="container">
	<section>
	<h1>Add Items</h1>
	<form method="POST" action="addNewItem" enctype="multipart/form-data">
	<div>
	<select name = "category">
		<c:forEach var="categoryItem" items="${categories}">
			<option>${categoryItem}</option>
		</c:forEach>
	</select>
	</div>
	<div class="row categoryAdd">
		<div class="col-md-3">
			<input type="text" name="name" placeholder="Enter item name..." class="form-control">
		</div>
		<div class="col-md-2">
			<input type="text" name="price" placeholder="Enter price.." class="form-control">
		</div>
		<div class="col-md-3">
			<input type="file" name="image_item" value="Upload Image">
		</div>
		<div class="col-md-2">
			<input type="text" name="quantity" placeholder="Enter availability.." class="form-control">
		</div>
		<div class="col-md-2">
			<input type="submit" value="Submit">
		</div>
	</div>
	<div class="row description">
		<textarea name="description" placeholder = "Enter Description..."></textarea>
	</div>	
	</form>
</section>
</body>

</html>