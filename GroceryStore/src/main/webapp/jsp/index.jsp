<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome to Grocery Store | Parishram Grocery Application</title>
<link
	href="https://fonts.googleapis.com/css?family=Abril+Fatface|EB+Garamond|Libre+Baskerville|Special+Elite"
	rel="stylesheet">
<link rel="stylesheet" href="./css/index.css">
<link rel="stylesheet" href="./css/bootstrap.min.css">
<link rel="stylesheet" href="./css/font-awesome.css">
<script src="./js/lib/jquery.min.js"></script>
<script src="./js/lib/bootstrap.min.js"></script>
<script src="./js/index.js"></script>
</head>
<body class="container">
	<div class="indexHeader">
		<%@include file="/html/header.html"%>
	</div>
	<div>
		<%@include file="/html/login.html"%>
	</div>
	<div>
		<span id="loginLink">Login</span>
	</div>
</body>
</html>