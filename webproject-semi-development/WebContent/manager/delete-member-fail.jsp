<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript">
	alert("${RequestScope.num}명 강제 탈퇴 실패");
	location.href = "${pageContext.request.contextPath}/MemberListController.do";
</script>