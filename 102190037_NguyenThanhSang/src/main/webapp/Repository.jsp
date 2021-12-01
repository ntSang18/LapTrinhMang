<%@page import="model.bean.DBFile"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="Style.css">
<meta charset="UTF-8">
<title>Repository</title>
</head>
<body>
	<h1 class="title" >Repository</h1>
	<div class="contain-btn"><a href="DirectionServlet?mod=2">Refresh</a><a href="DirectionServlet?mod=4">Back Home</a></div>
	<table class="table-content">
		<thead><tr><th>File Name</th><th>Video</th><th>Audio</th><th>Status</th><th>Delete</th></tr></thead>
	<%
		List<DBFile> l = (List<DBFile>)request.getAttribute("ListFile");
		for (DBFile f : l) {
			String status = "";
			if(f.getStatus() == 0){
				status = "Uploading...";
			}
			else if(f.getStatus() == 1){
				status = "Complete!";
			}
			else {
				status = "Fail!";
			}
	%>
		<tr><td><%=f.getVideoname() %></td>
	<%
		if(f.getStatus() == 1){
	%>
			<td><video width="320" height="240" controls>
  			<source src="./video/<%=f.getVideoname() %>" type="video/mp4">
			</video></td>
			
			<td><audio controls="controls">
                <source src="./audio/<%=f.getAudioname() %>" type="audio/mp3" />
            </audio></td>
	<%
		}
		else{
	%>
			<td>Video doesn't exist</td>
			<td>Audio doesn't exist</td>
	<%
		}
	%>
			
			<td><%=status %></td>
			<td><a href="DeleteFileServlet?id=<%=f.getId()%>">Delete</a></td>
	<%
		}
		
	%>
	</table>
</body>
</html>