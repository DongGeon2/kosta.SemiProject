<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>id사용가능팝업</title>
<script type="text/javascript">
	let of=window.opener.registerForm;
	of.flag.value="<%=request.getParameter("id")%>";
	function openPopup(){
		of.password.focus();
		self.close();
	}	
</script>
</head>
<%--나중에 이미지로 교체 id ok --%>
<body bgcolor="blue" onunload="openPopup()">
<%=request.getParameter("id") %> 아이디 사용가능 <br><br>
<input type="button" value="확인" onclick="openPopup()">
</body>
</html>










