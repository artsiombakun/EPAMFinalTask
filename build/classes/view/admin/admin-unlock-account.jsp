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
<title>${dictionary["UNLOCK_ACCOUNT"]}</title>
</head>
<body>
	<checkAuth:checkAccess role="admin" />
	<div class="navbar navbar-inverse">
		<ul class="nav navbar-nav">
			<li><a href="admin-get-info.jsp">${dictionary["INFO_ABOUT_ACCOUNTS"]}</a></li>
			<li class="active"><a href="admin-unlock-account.jsp">${dictionary["UNLOCK_ACCOUNT"]}</a></li>
			<li><a href="admin-create-account.jsp">${dictionary["CREATE_ACCOUNT"]}</a></li>
			<li><a href="admin-clients-list.jsp">${dictionary["CLIENTS_LIST"]}</a></li>
			<li>
				<form method="post" action="controller">
					<input type="hidden" name="command" value="log-out-command">
					<button class="btn btn-danger">${dictionary["LOG_OUT"]}</button>
				</form>
			</li>
		</ul>
	</div>
	<div class="row">
		<div class="col-sm-4"></div>
		<div class="col-sm-6">
			<c:if test="${not empty errmsg}">
				<div class="alert alert-danger" style="clear: both; width: 450px">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					${errmsg}
				</div>
			</c:if>
			<c:if test="${empty blockedAccs}">
				<div class="alert alert-danger" style="clear: both; width: 450px">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					Bank has no locked accounts!
				</div>
			</c:if>
			<br /> <br />
			<form method="post" action="controller">
				<input type="hidden" name="command" value="unlock-account-command">
				<span>${dictionary["ACCOUNT_TO_UNLOCK"]}</span> <input type="text"
					name="unlock"><br /> <br />
				<button class="btn btn-primary">${dictionary["UNLOCK_ACCOUNT"]}</button>
			</form>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-4"></div>
		<div class="col-sm-4">
			<c:if test="${not empty blockedAccs}">
				<h2>${dictionary["BLOCKED_ACCS"]}</h2>
				<table class="table table-striped">
					<thead>
						<tr>
							<th>${dictionary["ID"]}</th>
							<th>${dictionary["BALANCE"]}</th>
							<th>${dictionary["STATEMENT"]}</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${blockedAccs}" var="acc">
							<tr>
								<td>${acc.id}</td>
								<td>${acc.balance}</td>
								<td>${acc.state}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<form method="post" onchange="this.form.submit()">
					<input type="hidden" name="command" value="pagination-command">
					<input type="hidden" name="list-name" value="blockedAccs">
					<ul class="pager">
						<c:if test="${blockedAccsPageNo gt 0}">
							<li class="previous"><button class="btn btn-info"
									name="page-direction" value="prev">&larr;</button></li>
						</c:if>
						<c:if test="${blockedAccsPageNo lt blockedAccsTotalPage}">
							<li class="next"><button class="btn btn-info"
									name="page-direction" value="next">&rarr;</button></li>
						</c:if>
					</ul>
				</form>
			</c:if>
		</div>
	</div>
</body>
</html>