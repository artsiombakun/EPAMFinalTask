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
<title>Clients</title>
</head>
<body>
	<checkAuth:checkAccess role="admin" />
	<div class="row">
		<div class="col-sm-12">
			<ul class="nav nav-pills">
				<li><a href="admin-get-info.jsp">${dictionary["INFO_ABOUT_ACCOUNTS"]}</a></li>
				<li><a href="admin-unlock-account.jsp">${dictionary["UNLOCK_ACCOUNT"]}</a></li>
				<li><a href="admin-create-account.jsp">${dictionary["CREATE_ACCOUNT"]}</a></li>
				<li class="active"><a href="admin-clients-list.jsp">Clients</a></li>
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
			<table class="table table-striped">
				<thead>
					<tr>
						<th>${dictionary["ID"]}</th>
						<th>${dictionary["FIRST_NAME"]}</th>
						<th>${dictionary["LAST_NAME"]}</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${clientlist}" begin="0"
						end="${clientlist.size()}" step="1" var="client">
						<tr>
							<td>${client.id}</td>
							<td>${client.name}</td>
							<td>${client.lastName}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<form method="get" action="controller">
				<input type="hidden" name="command"
					value="client-list-pagination-command">
				<ul class="pager">
					<li class="previous"><button class="btn btn-info" name="cl-l-page" value="prev">&larr;</button></li>
					<li class="next"><button class="btn btn-info" name="cl-l-page" value="next">&rarr;</button></li>
				</ul>
			</form>
		</div>
	</div>
</html>