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


			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="navbar-collapse collapse" id="navbarSupportedContent">
				<ul class="nav navbar-nav">
					<li><a class="navbar-brand" href="#">UK Blog</a></li>
					<li><a href="/" class="navbar-brand"><span
							class="glyphicon glyphicon-Home"></span> Home </a></li>
					<li><a href="new-article" class="navbar-brand">Create New Article</a></li>
					<li><a href="all-articles?page=0&size=2" class="navbar-brand">All Articles</a></li>
					<li><a href="all-articles-verify" class="navbar-brand">Verified
							Articles</a></li>
					<li><a href="my-articles" class="navbar-brand">My Articles</a></li>
				</ul>
				<form class="navbar-form navbar-left" action="/find-article"
					method="GET">
					<div class="form-group">
						<input type="text" name="title" class="form-control"
							placeholder="Search by topic title">
					</div>
					<button type="submit" class="btn btn-secondary">Go!</button>
				</form>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="/logout">Logout</a></li>
				</ul>
			</div>
		</div>

	</nav>
	<div class="container">
		<div class="row">
			<!-- <div class="col-md-3 profile">
			<aside> -->
						<c:if test="${pageContext.request.userPrincipal.name == null}">
				<h2>No User logged in.Please Login to continue</h2>
				<a href="/login" class="navbar-brand">Login</a>
			</c:if>
			<c:if test="${pageContext.request.userPrincipal.name != null}">
			<h1>
				Hello<b> <c:out value="${pageContext.request.remoteUser}" />!!
				</b><br>
			</h1>
			<!--		</aside>
			</div>
			 <div class="col=md-8"> -->

			<article>
				<header>
					<h2>${article.title}</h2>
				</header>
				<div class="more lead">${article.description}</div>
				<sec:authorize access="hasRole('ADMIN')">
				<c:if test="${article.verifyFlag == false}">
					<a  class="btn btn-primary" href="verify-article?id=${article.id}" role="button" aria-pressed="true"> Approve </a></c:if>
					<c:if test="${article.verifyFlag == true}">
					<button type="button"class="btn btn-lg btn-primary" disabled>Approve</button></c:if>
					<c:if test="${article.markAsDeleted == false}">
					<a class="btn btn-primary" href="reject-article?id=${article.id}" role="button" aria-pressed="true"> Reject </a></c:if>
					<c:if test="${article.markAsDeleted == true}">
					<button type="button"class="btn btn-lg btn-primary" disabled>Reject</button></c:if>
				</sec:authorize>
				<hr>
				<div id="comments">
					<h2>Comments</h2>
					<div class="comments-list">
						<c:forEach var="reply" items="${replies}">
							<header>
							<c:if test="${reply.markAsDeleted == true}">
								<strike>${reply.description}</strike>
								<a href="#"><span class=".glyphicon-glyphicon-remove-circle "></span>Rejected by Admin</a>
							</c:if>
							<c:if test="${reply.verifyFlag == true}">
								<p>${reply.description}</p>
								<a href="#"><span class="glyphicon glyphicon-check "></span>Approved by Admin</a>
							</c:if>
								<c:if test="${reply.markAsDeleted == false}">
								<p>${reply.description}</p></c:if>
							</header>
								<sec:authorize access="hasRole('ADMIN')">
				<c:if test="${reply.verifyFlag == false && reply.markAsDeleted == false}">
					<a  class="btn btn-primary" href="verify-reply?id=${article.id}&replyId=${reply.replyId}" role="button" aria-pressed="true"> Approve Reply </a>
					<a class="btn btn-primary" href="reject-reply?id=${article.id}&replyId=${reply.replyId}" role="button" aria-pressed="true"> Reject Reply </a>
					</c:if>
					<c:if test="${reply.verifyFlag == true||reply.markAsDeleted == true}">
					
					<button type="button"class="btn btn-lg btn-primary" disabled>Approve Reply</button>
					<button type="button"class="btn btn-lg btn-primary" disabled>Reject Reply</button>
					</c:if>
				</sec:authorize>
							<footer>
								<small><b>${reply.createdBy}</b><b>${reply.createdDate}</b></small>
							</footer>
							<hr>
						</c:forEach>
					</div>
				</div>
				<div class="container text-center">
					<h1>Add Comment</h1>
					<form class="form-horizontal" method="POST" action="save-reply">
						<input type="hidden" name="id" value="${article.id}" /> <input
							type="hidden" name="id" value="${reply.id}" /><input
							type="hidden" name="replyid" value="${reply.replyId}" />

						<div class="form-group">
							<label class="control-label col-md-3 sr-only">Description</label>
							<div class="col-md-7">
								<textarea id="desc" class="form-control" rows="10" maxlength=10000
									name="description" contenteditable="true" spellcheck="true"
									placeholder="${reply.description}" required>${reply.description}</textarea>
							</div>
						</div>
						<div class="form-group">
							<input type="submit" class="btn btn-primary" value="Post" />
						</div>
					</form>
				</div>

			</article>
</c:if>

		</div>
	</div>


	<script src="static/js/jquery-1.11.1.min.js"></script>
	<script src="static/js/bootstrap.min.js"></script>
	<script>
$(document).ready(function() {
    // Configure/customize these variables.
    var showChar = 200;  
    var ellipsestext = "...";
    var moretext = "Read more >";
    var lesstext = "Read less";
    

    $('.more').each(function() {
        var content = $(this).html();
 
        if(content.length > showChar) {
 
            var c = content.substr(0, showChar);
            var h = content.substr(showChar, content.length - showChar);
 
            var html = c + '<span class="moreellipses">' + ellipsestext+ '&nbsp;</span><span class="morecontent"><span>' + h + '</span>&nbsp;&nbsp;<a href="" class="morelink">' + moretext + '</a></span>';
 
            $(this).html(html);
        }
 
    });
 
    $(".morelink").click(function(){
        if($(this).hasClass("less")) {
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