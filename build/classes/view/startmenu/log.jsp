<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<%@page contentType="text/html;charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/loadDefaultLocaleTaglib.tld" prefix="locale"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<title>${dictionary["LOG_IN"]}</title>
</head>
<body>
	<locale:loadLocale country="" language="" />
	<div class="navbar navbar-inverse">
		<ul class="nav navbar-nav">
			<li>
				<form method="post" onchange="this.form.submit()">
					<input type="hidden" name="command" value="change-lang-command">
					<button class="btn btn-success">${dictionary["LANGUAGE"]}</button>
				</form>
			</li>
			<li><a href="index.jsp">${dictionary["WELCOME_PAGE"]}</a></li>
			<li class="active"><a href="log.jsp">${dictionary["LOG_IN"]}</a></li>
			<li><a href="sign-up.jsp">${dictionary["SIGN_UP"]}</a></li>
		</ul>
	</div>
	<div class="row">
		<div class="col-sm-4"></div>
		<div class="col-sm-4">
			<form method="post" action="controller">
				 <br> <br>
				<c:choose>
					<c:when test="${not empty errmsg}">
						<div class="alert alert-danger">
							<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
							${errmsg}
						</div>
					</c:when>
					<c:otherwise>
						<br> <br>
					</c:otherwise>
				</c:choose>
				<input type="hidden" name="command" value="log-in-command">
				<div class="row">
					<div class="col-sm-5">
						<span>${dictionary["LOGIN"]}</span>
					</div>
					<input type="text" name="login" autocomplete="off"><br>
					<br>
				</div>
				<div class="row">
					<div class="col-sm-5">
						<span>${dictionary["PASSWORD"]}</span>
					</div>
					<input type="password" name="pswd" autocomplete="off"><br>
					<br>
				</div>
				<button class="btn btn-primary">${dictionary["LOG_IN"]}</button>
			</form>
		</div>
	</div>
</body>
</html>