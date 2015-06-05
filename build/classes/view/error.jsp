<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="style.css">
<title>ERROR</title>
</head>

<body bgcolor="#dd0000">
	<div class="wrapper">
	<h1>An error has occurred:</h1>
	<h3><span style="color:red">${error}</span></h3>
	<h4>For details, see the log.</h4>
	<br/><br/><br/>
	<h2>You can:</h2>
	<ul>
		<li>	<form method="post" action="controller">
		<input type="hidden" name="command" value="log-out-command">
		<input type="submit" value="Try log in again..." >
	</form></li>
		<li><h3>...or leave this resource.</h3></li>
	</ul>
	</div>
</body>
</html>