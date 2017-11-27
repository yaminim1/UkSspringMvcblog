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

	<nav class="navbar navbar-default">
		<div class="col-md-4 clearfix kad-header-left">

			<div id="logo" class="logocase">
				<a class="brand logofont" href="3" title="UK College of Education">

					<img src="<c:url value="static/img/UK.jpg"/>"
					alt="UK College of Education" class="kad-standard-logo">
				</a>
			</div>
		</div>

		<div class="col-md-8 kad-header-right">
			<nav id="nav-main" class="clearfix">

				<span class="navbar-brand mb-9 h1 ">College of Education
					Professional Information Systems</span>

			</nav>
		</div>
		<!-- <span class="navbar-brand mb-0 h1 ">University of Kentucky College of Education</span> -->

	</nav>
	<div class="container">

		<form class="form-signin" method="post"
			action="${pageContext.request.contextPath}/login">
			<h2 class="text-center">Login</h2>
			<c:if test="${not empty error}">
				<div class="error">${error}</div>
			</c:if>
			<c:if test="${not empty msg}">
				<div class="msg">${msg}</div>
			</c:if>

			<c:if test="${param.logout}">
				<div class="alert alert-info">You have been logged out.</div>
			</c:if>

			<div class="form-group">
				<label class="sr-only" for="username">User Name</label> <input
					type="text" class="form-control" id="username"
					placeholder="Enter UserName" name="username" required autofocus>
				<div class="help-block with-errors"></div>
			</div>
			<div class="form-group">
				<label class="sr-only" for="pwd">Password</label> <input
					type="password" class="form-control" id="pwd"
					placeholder="Enter password" name="pwd" required>
				<div class="help-block with-errors"></div>
			</div>

			<div class="form-group">

				<button type="submit" class="btn btn-primary btn-block">Login</button>
			</div>
		</form>
	</div>



	<footer class="container" id="footer">
		<div class="row">
			<div class="navbar navbar-default navbar-fixed-bottom">
				<p class="m-0 text-center text-white copy">Copyright &copy;
					University of Kentucky 2017</p>
			</div>
		</div>
	</footer>

	<script src="static/js/jquery-1.11.1.min.js"></script>
	<script src="static/js/bootstrap.min.js"></script>
</body>
</html>