<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:import url="/template/headerLink.jsp"></c:import>
<title>email사용불가팝업</title>
<script type="text/javascript">
	window.opener.registerForm.email.value="";
	window.opener.registerForm.flagEmail.value="";
	function closePopup(){
		window.opener.registerForm.email.focus();
		self.close();
	}
</script>
</head>
<%--나중에 이미지로 교체 id fail --%>
<body onunload="closePopup()">
<div style="text-align:center; margin-top:15%">
<b><%=request.getParameter("email") %></b><br> 이메일은 사용불가<br><br>
<button type="button" class="btn btn-warning" onclick="closePopup()">확인</button>
</div>
</body>
</html>




