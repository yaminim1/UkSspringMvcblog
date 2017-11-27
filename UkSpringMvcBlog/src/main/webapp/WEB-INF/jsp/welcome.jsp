<!DOCTYPE HTML>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Expires" content="Sat, 01 Dec 2001 00:00:00 GMT">

<title>UK Blog | Home</title>

<link href="static/css/bootstrap.min.css" rel="stylesheet">
<link href="static/css/style.css" rel="stylesheet">


</head>
<body>
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class=navbar-header>
				<button class="navbar-toggler" type="button" data-toggle="collapse"
					data-target="#Content" aria-expanded="false">
					<span class="sr-only">Toggle menu navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">UK Blog</a>
			</div>
			<div class="navbar-collapse collapse" id="Content">
				<ul class="nav navbar-nav">

					<li><a href="${pageContext.request.contextPath}/"
						class="navbar-brand"><span class="glyphicon glyphicon-Home"></span>
							Home </a></li>
					<li><a href="new-article" class="navbar-brand">Create New
							Article</a></li>
					<li><a href="all-articles" class="navbar-brand">All
							Articles</a></li>
					<li><a href="all-articles-verify" class="navbar-brand">Verified
							Articles</a></li>
					<li><a href="my-articles" class="navbar-brand">My Articles</a></li>
				</ul>
				<form class="navbar-form navbar-left"
					action="${pageContext.request.contextPath}/find-article"
					method="GET">
					<div class="form-group">
						<input type="text" name="title" class="form-control"
							placeholder="Search the Articles">
					</div>
					<button type="submit" class="btn btn-secondary">Go!</button>
				</form>
				<c:if test="${pageContext.request.userPrincipal.name != null}">
					<ul class="nav navbar-nav navbar-right">
						<li><a href="${pageContext.request.contextPath}/logout"
							class="navbar-brand">Logout</a></li>
					</ul>
				</c:if>
				<c:if test="${pageContext.request.userPrincipal.name == null}">
					<ul class="nav navbar-nav navbar-right">
						<li><a href="${pageContext.request.contextPath}/login"
							class="navbar-brand">Login</a></li>
					</ul>
				</c:if>
			</div>
		</div>

	</nav>
	<div class="container">
		<div class="row">

			<c:if test="${pageContext.request.userPrincipal.name == null}">
				<h2>No User logged in.Please Login to continue</h2>
				<a href="${pageContext.request.contextPath}/login"
					class="navbar-brand">Login</a>
			</c:if>
			<c:if test="${pageContext.request.userPrincipal.name != null}">
				<h1>
					Hello
					<c:out value="${pageContext.request.remoteUser}" />
					!!<br>
				</h1>

				<!--		</aside>
			</div>
			 <div class="col=md-8"> -->

				<c:choose>
					<c:when test="${mode == 'MODE_HOME'}">
						<div class="container" id="homeDiv">
							<div class="jumbotron text-center">
								<h1>Welcome To UKy CEPIS Blog</h1>
								<img src="<c:url value="static/img/WelcomeScreen.jpg"/>"
									alt="UK College of Education">

							</div>
						</div>
					</c:when>
					<c:when test="${mode == 'MODE_ARTICLES'}">
						<article>

							<c:if test="${articleSize =='0'}">
								<h2>No records found</h2>
							</c:if>
							<c:forEach var="article" items="${articles}">
								<header>

									<c:if test="${article.verifyFlag == true}">
										<h2>${article.title}</h2>
										<a href="#"><span class="glyphicon glyphicon-check "></span>Approved
											by Admin</a>
									</c:if>
									<c:if test="${article.verifyFlag == false}">

										<h2>${article.title}</h2>
										<a href="#"><span class="glyphicon glyphicon-unchecked "></span>Not
											yet Verified</a>
									</c:if>

								</header>
								<div class="more lead">${article.description}</div>

								<footer>
									<c:if
										test="${article.createdBy== pageContext.request.remoteUser}">
										<a
											href="${pageContext.request.contextPath}/update-article?id=${article.id}">
											<span class="glyphicon glyphicon-pencil"></span> Edit
										</a>


										<a href="delete-article?id=${article.id}"
											onclick="return confirm('Are you sure you want to delete?')">
											<span class="glyphicon glyphicon-trash"></span> Delete
										</a>
									</c:if>
									<a
										href="${pageContext.request.contextPath}/read-more?id=${article.id}"><span
										class="glyphicon glyphicon-comment "></span>
										${article.replyCount} Comments </a>

									<div>
										<span class="badge badge-success">Posted on
											${article.createdDate}</span>by <span
											class="glyphicon glyphicon-user">${article.createdBy}</span>
										<p></p>
										<div class="pull-right"></div>
									</div>
								</footer>
								<hr>
							</c:forEach>
						</article>
					</c:when>
					<c:when test="${mode == 'MODE_NEW'|| mode == 'MODE_UPDATE'}">
						<div class="container text-center form-group required">
							<h2>Manage Articles</h2>
							<hr>
							<form class="form-horizontal" method="POST" action="save-article"
								name="myForm" onsubmit="return validateForm()">
								<input type="hidden" name="id" value="${article.id}" />
								<div class="form-group">
									<label class="control-label col-md-3">Title</label>
									<div class="col-md-7">
										<input type="text" class="form-control" name="title"
											maxlength=45 value="${article.title}"
											placeholder="Enter Article Title" required />
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-3">Description</label>
									<div class="col-md-7">
										<textarea id="desc" class="form-control" rows="10"
											maxlength=10000 name="description" contenteditable="true"
											spellcheck="true" placeholder="${article.description}"
											required>${article.description}</textarea>
									</div>
								</div>
								<div class="form-group">
									<input type="submit" class="btn btn-primary" value="Submit" />
								</div>
							</form>
						</div>
					</c:when>
				</c:choose>
			</c:if>
		</div>
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
	<script>
		$(document)
				.ready(
						function() {
							
							var showChar = 100;
							var ellipsestext = "...";
							var moretext = "Read more >";
							var lesstext = "Read less";

							$('.more')
									.each(
											function() {
												var content = $(this).html();

												if (content.length > showChar) {

													var c = content.substr(0,
															showChar);
													var h = content.substr(
															showChar,
															content.length
																	- showChar);

													var html = c
															+ '<span class="moreellipses">'
															+ ellipsestext
															+ '&nbsp;</span><span class="morecontent"><span>'
															+ h
															+ '</span>&nbsp;&nbsp;<a href="" class="morelink">'
															+ moretext
															+ '</a></span>';

													$(this).html(html);
												}

											});

							$(".morelink").click(function() {
								if ($(this).hasClass("less")) {
									$(this).removeClass("less");
									$(this).html(moretext);
								} else {
									$(this).addClass("less");
									$(this).html(lesstext);
								}
								$(this).parent().prev().toggle();
								$(this).prev().toggle();
								return false;
							});
						});
	</script>
</body>
</html>