<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
alert("포인트가 부족합니다.\n현재 포인트 : ${point}\n부족한 포인트 : ${10-point}\n");
if (confirm("결제창으로 이동하시겠습니까?")){
	location.href="payment.jsp";
}
else
	location.href="index.jsp";
</script>
</head>
<body>

</body>
</html>