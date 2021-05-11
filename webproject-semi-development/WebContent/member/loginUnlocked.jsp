<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 
	사용자가 작업 중 로그인 해제되었을 때
	로그인이 해제되었습니다. 다시 로그인 하시기 바랍니다. alert 후
	index 로 보내기
 -->
<script type="text/javascript">
	alert("로그인이 해제되었습니다. 다시 로그인 하시기 바랍니다.");
	location.href = "${pageContext.request.contextPath}/index.jsp";
</script>