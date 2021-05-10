<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="../TestUploadCotroller.do" enctype="multipart/form-data" method="post">
	title: <input type="text" name="title" required="required" placeholder="제목"><br>
	writer: <input type="text" name="writer" required="required" placeholder="작성자"><br>
	content: <textarea rows="10" cols="20" name="content"></textarea><br>
	file: <input type="file" name="filename"><br>
	<input type="submit" value="확인">
</form>
</body>
</html>