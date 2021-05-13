<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<c:import url="/template/headerLink.jsp"></c:import>
<meta charset="UTF-8">
<title>id 사용가능</title>
<script type="text/javascript">
	let of=window.opener.registerForm;
	of.flagId.value="<%=request.getParameter("id")%>";
	function openPopup(){
		of.password.focus();
		self.close();
	}	
</script>
</head>
<%--나중에 이미지로 교체 id ok --%>
<body onunload="openPopup()">
<div style="text-align:center; margin-top:15%">
<b><%=request.getParameter("id") %></b><br>아이디 사용가능<br><br>
<button type="button" class="btn btn-warning" onclick="openPopup()">확인</button>
<!-- <input type="button" value="확인" onclick="openPopup()"> -->
</div>

</body>
</html>










