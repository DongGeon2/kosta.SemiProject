<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>id사용불가팝업</title>
<script type="text/javascript">
	window.opener.registerForm.id.value="";
	window.opener.registerForm.flag.value="";
	function closePopup(){
		window.opener.registerForm.id.focus();
		self.close();
	}
</script>
</head>
<%--나중에 이미지로 교체 id fail --%>
<body bgcolor="blue" onunload="closePopup()">
<%=request.getParameter("id") %>아이디는 사용불가<br><br>
<input type="button" value="확인" onclick="closePopup()">
</body>
</html>




