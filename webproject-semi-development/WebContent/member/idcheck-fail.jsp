<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:import url="/template/headerLink.jsp"></c:import>
<title>id 사용불가</title>
<script type="text/javascript">
	window.opener.registerForm.id.value="";
	window.opener.registerForm.flagId.value="";
	function closePopup(){
		window.opener.registerForm.id.focus();
		self.close();
	}
</script>
</head>
<%--나중에 이미지로 교체 id fail --%>
<body onunload="closePopup()">
<div style="text-align:center; margin-top:15%">
<b><%=request.getParameter("id") %></b><br>아이디 사용불가<br><br>
<button type="button" class="btn btn-warning" onclick="closePopup()">확인</button>
</div>
</body>
</html>




