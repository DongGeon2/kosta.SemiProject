<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript">
	alert("비밀번호가 일치하지 않습니다");
	location.href = "${pageContext.request.contextPath}/UpdateMemberPasswordController.do";
</script>