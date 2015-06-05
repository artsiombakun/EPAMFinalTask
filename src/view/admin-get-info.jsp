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
	<checkAuth:checkAccess role="admin" />
	<div class="row">
		<div class="col-sm-12">
			<ul class="nav nav-pills">
				<li class="active"><a href="admin-get-info.jsp">${dictionary["INFO_ABOUT_ACCOUNTS"]}</a></li>
				<li><a href="admin-unlock-account.jsp">${dictionary["UNLOCK_ACCOUNT"]}</a></li>
				<li><a href="admin-create-account.jsp">${dictionary["CREATE_ACCOUNT"]}</a></li>
				<li><a href="admin-clients-list.jsp">Clients</a></li>
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
			<br><br>
			<form method="get" action="controller">
				<input type="hidden" name="command" value="get-info-command">
				<span>${dictionary["INPUT_ID_OF_CLIENT"]}</span> <input type="text"
					name="clID"><br><br>
				<button>${dictionary["GET_INFO"]}</button>
			</form>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-3"></div>
		<div class="col-sm-6">
			<c:if test="${not empty acclist}">
				<h2>${dictionary["ACCOUNTS_OF_CLIENT"]}</h2>
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
			</c:if>
		</div>
	</div>
</body>
</html>