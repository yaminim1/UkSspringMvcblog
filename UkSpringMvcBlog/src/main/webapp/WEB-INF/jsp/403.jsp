<!DOCTYPE HTML>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Expires" content="Sat, 01 Dec 2001 00:00:00 GMT">

<title>UK Blog | Home</title>

<link href="static/css/bootstrap.min.css" rel="stylesheet">
<link href="static/css/login.css" rel="stylesheet">

</head>
<body>

	<nav class="navbar navbar-dark bg-primary">
		<span class="navbar-brand mb-0 h1 ">University of Kentucky Blog</span>
	</nav>
	<div class="container">
		<c:choose>
			<c:when test="${empty username}">
				<h2>You do not have permission to access this page!</h2>
			</c:when>
			<c:otherwise>
				<h2>
					Username : ${username} <br />You do not have permission to access
					this page!
				</h2>
			</c:otherwise>
		</c:choose>
	</div>





	<script src="static/js/jquery-1.11.1.min.js"></script>
	<script src="static/js/bootstrap.min.js"></script>
</body>
</html>