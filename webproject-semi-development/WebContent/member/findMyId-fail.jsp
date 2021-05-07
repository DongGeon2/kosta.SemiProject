<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript">
	alert("해당하는 이메일로 가입된 계정이 없습니다");
	location.href = "${pageContext.request.contextPath}/member/findMyId.jsp";
	
</script>