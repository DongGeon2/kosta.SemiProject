<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="d-sm-flex align-items-center justify-content-between mb-4">
<h4 class="h4 mb-0 text-gray-800" style="text-align: center">
비밀번호 확인
</h4>
 <form action="${pageContext.request.contextPath}/CheckPasswordController.do" method="post" class="user">
	<div class="form-group">
	<input type="password" class="form-control form-control-user" name="password" placeholder="Password">
	</div>
	<div class="text-center">
	<input type="submit" class="btn btn-primary" value="확인">
	</div>
</form>