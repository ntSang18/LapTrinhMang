<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="Style.css">
<title>Upload</title>
</head>
<body>
	<div>
  	<h1 class="title">File Upload</h1>
  	<form action="UploadServlet" method="post" enctype="multipart/form-data">
  		<div class="upload-container">
  			<input type="file" name="file" required="required">
  			<input type="submit" value="Upload" class="btn">
  		</div>
  		<div class="contain-btn"><a href="DirectionServlet?mod=1">Refresh</a><a href="DirectionServlet?mod=4">Back Home</a></div>
  		<div class="upload-note">
  			<h3>Chú ý:</h3>
  			<ul>
  				<li>File đưa vào phải là file mp4</li>
  				<li>Đường dẫn của file cần ở định dạng tiếng Việt không dấu</li>
  			</ul>
  		</div>
  	</form>
</div>
</body>
</html>