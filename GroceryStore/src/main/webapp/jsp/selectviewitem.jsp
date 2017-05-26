<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Items</title>
</head>
<body>
<form action="showItemDetails" method="POST">
	<div>
		<select name="category">
			<c:forEach items="${categories}" var="category">
				<option>
					${category}
				</option>
			</c:forEach>
		</select>
	</div>
	<div>
		<input type="submit" value="Submit">
	</div>
	<div>
		<a href="mainPage">Main</a>
	</div>
	<div>
		<a href="logout">Logout</a>
	</div>
</form>	
</body>
</html>