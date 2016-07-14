<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>上传图片</title>
	</head>
	<body>
		${errors}
			<form action="upload.form" method="post" enctype="multipart/form-data">
				<input type="file" name="file" />
				<input type="submit" value="上传"/>
			</form>
			<hr/>
			<form action="uploads.form" method="post" enctype="multipart/form-data">
				<input type="file" name="file" /><br/>
				<input type="file" name="file" />
				<input type="submit" value="批量上传" />
			</form>
	</body>
</html>