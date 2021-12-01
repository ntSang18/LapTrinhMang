<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="Style.css">
	<title>Register</title>
</head>
<body>
	<h1 class="title">REGISTER</h1>
	<form action="RegisterServlet" method="post">
		<div class="data_form">
			<label for="username">Username</label>
			<input type="text" name="username" id="username" required="required" placeholder="User name" class="ip">
			<label for="password">Password</label>
			<input type="password" name="password" id="password" required="required" placeholder="Password" class="ip">
			<input type="submit" value="Register" class="btn">
		</div>
	</form>
	<div class="link">
		<a href="DirectionServlet?mod=5">Login here</a>
	</div>
</body>
</html>