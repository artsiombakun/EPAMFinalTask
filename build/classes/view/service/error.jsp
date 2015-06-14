<!DOCTYPE html>
<html>
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
<title>${dictionary["ERROR_OCCURED"]}</title>
</head>

<body>
	<div class="row">
		<div class="col-sm-3"></div>
		<div class="col-sm-6">
			<h1>${dictionary["ERROR_OCCURED"]}:</h1>
			<h3>
				<span style="color: red">${error}</span>
			</h3>
			<h4>${dictionary["SEE_LOG"]}</h4>
			<br />
			<br />
			<br />
			<form method="post" action="controller">
				<input type="hidden" name="command" value="log-out-command">
				<button class="btn btn-warning">${dictionary["LOG_IN_AGAIN"]}</button>
			</form>
		</div>
	</div>
</body>
</html>