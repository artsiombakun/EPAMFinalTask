<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/checkAuthTaglib.tld" prefix="checkAuth"%>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<title>Block account</title>
</head>
<body>
	<checkAuth:checkAccess role="client" />
	<div class="navbar navbar-inverse">
		<ul class="nav navbar-nav">
			<li><a href="client-payment.jsp">${dictionary["TRANSFER"]}</a></li>
			<li><a href="client-close-account.jsp">${dictionary["CLOSE_ACCOUNT"]}</a></li>
			<li class="active"><a href="client-block-account.jsp">${dictionary["BLOCK_ACCOUNT"]}</a></li>
			<li><a href="client-fill-account.jsp">${dictionary["FILL_ACCOUNT"]}</a></li>
			<li><a href="client-accounts-list.jsp">${dictionary["YOUR_ACCOUNTS"]}</a></li>
			<li>
				<form method="post" action="controller">
					<input type="hidden" name="command" value="log-out-command">
					<button class="btn btn-danger">${dictionary["LOG_OUT"]}</button>
				</form>
			</li>
		</ul>
	</div>
	<div class="row">
		<div class="col-sm-3"></div>
		<div class="col-sm-6">
			<c:if test="${not empty errmsg}">
				<div class="alert alert-danger">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					${errmsg}
				</div>
			</c:if>
			<br>
			<form method="post" action="controller">
				<input type="hidden" name="command" value="block-account-command">
				<span>${dictionary["ACCOUNT_TO_BLOCK"]}</span> <input type="text"
					name="block"> <br>
				<br>
				<button class="btn btn-primary">${dictionary["BLOCK_ACCOUNT"]}</button>
			</form>
		</div>
	</div>
</body>
</html>