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
<title>Info</title>
</head>
<body>
	<checkAuth:checkAccess role="client" />
	<div class="row">
		<div class="col-sm-12">
			<ul class="nav nav-pills">
				<li><a href="client-payment.jsp">${dictionary["TRANSFER"]}</a></li>
				<li><a href="client-close-account.jsp">${dictionary["CLOSE_ACCOUNT"]}</a></li>
				<li><a href="client-block-account.jsp">${dictionary["BLOCK_ACCOUNT"]}</a></li>
				<li><a href="client-fill-account.jsp">${dictionary["FILL_ACCOUNT"]}</a></li>
				<li class="active"><a href="client-accounts-list.jsp">${dictionary["YOUR_ACCOUNTS"]}</a></li>
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
		<div class="col-sm-4"></div>
		<div class="col-sm-4">
			<h2>${dictionary["YOUR_ACCOUNTS"]}</h2>
			<table class="table table-striped">
				<thead>
					<tr>
						<th>${dictionary["ID"]}</th>
						<th>${dictionary["BALANCE"]}</th>
						<th>${dictionary["STATEMENT"]}</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${acclist}" begin="0" end="${acclist.size()}"
						step="1" var="acc">
						<tr>
							<td>${acc.id}</td>
							<td>${acc.balance}</td>
							<td>${acc.state}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>