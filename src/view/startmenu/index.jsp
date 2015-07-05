<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<%@page contentType="text/html;charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/loadDefaultLocaleTaglib.tld" prefix="locale"%>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<title>${dictionary["WELCOME_PAGE"]}</title>
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
			<li class="active"><a href="index.jsp">${dictionary["WELCOME_PAGE"]}</a></li>
			<li><a href="log.jsp">${dictionary["LOG_IN"]}</a></li>
			<li><a href="sign-up.jsp">${dictionary["SIGN_UP"]}</a></li>
		</ul>
	</div>
	<div class="wrapper">
		<img class="img-responsive" src="Perm'.jpg" alt="Chania">
	</div>
	<footer> ${dictionary["CONTACT_INFO"]} </footer>
</body>
</html>