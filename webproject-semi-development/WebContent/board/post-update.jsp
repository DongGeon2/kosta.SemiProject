<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 
	post-update.jsp
	나라별 게시판 글 수정 jsp
	필요한것 : 
		카테고리(분류), 글제목, 글내용
 -->
<script>
	function checkCatergory() {
		var flag = true;
		var numSelect = document.getElementById("catergory");
		var text = numSelect.options[document.getElementById("catergory").selectedIndex].text;
		var value = numSelect.options[document.getElementById("catergory").selectedIndex].value;
		if(text == '분류를 선택해주세요'){
			alert("분류를 선택해 주세요");
			numSelect.focus();
			flag = false;
		}
		return flag;
	}
</script>
<div class="card shadow mb-4">
	<div class="card-header py-3">
		<h6 class="m-0 font-weight-bold text-primary">${requestScope.pvo.countryVO.countryName} 게시판</h6>
	</div>
	<div class="card-body">
		<form method="post" action="${pageContext.request.contextPath}/UpdatePostController.do" method="post" onsubmit="return checkCatergory();">
		<input type="hidden" name="postNo" value="${pvo.postNo}"></input>		
			<div class="form-group">
				<label for="catergory">분류</label> 
				<select class="form-control" id="catergory" name="catergory">
					<option value="분류를 선택해주세요" selected>분류를 선택해주세요</option>
					<option value="정보">정보</option>
					<option value="동행">동행</option>
					<option value="후기">후기</option>
				</select>
			</div>
			<div class="form-group">
				<label for="postTitle">제목</label> 
				 <input type="text" class="form-control" name="postTitle" value="${pvo.postTitle}" required="required">
				<!-- <input type="text"
					class="form-control" placeholder="제목을 입력해 주세요" id="postTitle"> -->
			</div>
			<div class="form-group">
				<textarea class="form-control" rows="10" name="postContent" required="required">${pvo.postContent}</textarea>
				<!-- <label for="postContent">내용</label>
				<textarea class="form-control" rows="10" id="postContent" placeholder="내용을 입력해 주세요"></textarea> -->
			</div>
		
		<div class="btnWrap">
			<button type="submit" class="btn btn-outline-primary">
			<i class="fas fa-fw fa-pencil-alt"></i> 수정</button>
			<button type="reset" class="btn btn-outline-primary">
			<i class="fas fa-fw fa-times"></i> 취소</button>
		</div>
		</form>
	</div>
</div>
