<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>404 ERROR</title>
</head>
<body>
	<h1>404 ERROR!!!</h1>
	<%String error = (String)request.getAttribute("error"); %>
	Error is: <%=error %>
</body>
</html>