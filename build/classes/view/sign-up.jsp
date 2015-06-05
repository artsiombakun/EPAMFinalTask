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
<title>Sign up</title>
</head>
<body>
	<locale:loadLocale country="" language="" />
	<div class="row">
		<div class="col-sm-12">
			<ul class="nav nav-pills">
				<li>
					<form method="post" action="controller">
						<input type="hidden" name="command" value="change-lang-command">
						<button class="btn btn-success">${dictionary["LANGUAGE"]}</button>
					</form>
				</li>
				<li><a href="welcome.jsp">${dictionary["WELCOME_PAGE"]}</a></li>
				<li><a href="log.jsp">${dictionary["LOG_IN"]}</a></li>
				<li class="active"><a href="sign-up.jsp">${dictionary["SIGN_UP"]}</a></li>
			</ul>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-4"></div>
		<div class="col-sm-6">
		<br><br><br><br>
			<form method="post" action="controller">
				<span style="color: red">${errmsg}</span> 
				<input type="hidden" name="command" value="sign-up-command">
				<div class="row"><div class="col-sm-3">
				<span>${dictionary["FIRST_NAME"]}</span>
				</div><div class="col-sm-3">
				<input type="text" name="fname" autocomplete="off"><br><br>
				</div></div>
				<div class="row"><div class="col-sm-3">
				<span>${dictionary["LAST_NAME"]}</span>
				</div><div class="col-sm-3">
				<input type="text" name="lname" autocomplete="off"> <br><br>
				</div></div>
				<div class="row"><div class="col-sm-3">
				<span>${dictionary["LOGIN"]}</span>
				</div><div class="col-sm-3">
				<input type="text" name="login" autocomplete="off"> <br><br>
				</div></div>
				<div class="row"><div class="col-sm-3">
				<span>${dictionary["PASSWORD"]}</span>
				</div><div class="col-sm-3">
				<input type="password" name="pswd" autocomplete="off"> <br><br>
				</div></div>
				<div class="row"><div class="col-sm-3">
				<span>${dictionary["CONFIRM_PASSWORD"]}</span>
				</div><div class="col-sm-3">
				<input type="password" name="confirmpswd" autocomplete="off"><br><br>
				<br>
				</div></div>
				<button>${dictionary["SIGN_UP"]}</button>
			</form>
		</div>
	</div>
</body>
</html>