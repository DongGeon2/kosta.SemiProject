<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:import url="/template/headerLink.jsp"></c:import>
<title>email사용가능팝업</title>
<script type="text/javascript">
	let of=window.opener.registerForm;
	of.flagEmail.value="<%=request.getParameter("email")%>";
	function openPopup(){
		self.close();
	}	
</script>
</head>
<%--나중에 이미지로 교체 id ok --%>

<body onunload="openPopup()">
<div style="text-align:center; margin-top:15%">
<b><%=request.getParameter("email")%></b><br> 이메일 사용가능 <br><br>
<button type="button" class="btn btn-warning" onclick="openPopup()">확인</button>
</div>
</body>
</html>










