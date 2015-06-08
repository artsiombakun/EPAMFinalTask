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
<title>Close account</title>
</head>
<body>
	<checkAuth:checkAccess role="client" />
	<div class="row">
		<div class="col-sm-12">
			<ul class="nav nav-pills">
				<li><a href="client-payment.jsp">${dictionary["TRANSFER"]}</a></li>
				<li class="active"><a href="client-close-account.jsp">${dictionary["CLOSE_ACCOUNT"]}</a></li>
				<li><a href="client-block-account.jsp">${dictionary["BLOCK_ACCOUNT"]}</a></li>
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
	</div>
	<div class="row">
		<div class="col-sm-3"></div>
		<div class="col-sm-6">
			<c:if test="${not empty errmsg}">
				<div class="alert alert-danger" style="clear: both; width: 450px">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					${errmsg}
				</div>
			</c:if>
			<br/> <br/>
			<form method="post" action="controller">
				<input type="hidden" name="command" value="close-account-command"/>
				<div class="row">
					<div class="col-sm-5">
						<span>${dictionary["CLOSE_ACCOUNT"]}</span>
					</div>
					<input type="text" name="from">
				</div>
				<br>
				<div class="row">
					<div class="col-sm-5">
						<span>${dictionary["TRANSFER_REST_TO"]}</span>
					</div>
					<input type="text" name="to">
				</div>
				<br>
				<button>${dictionary["CLOSE_ACCOUNT"]}</button>
			</form>
		</div>
	</div>
</body>
</html>