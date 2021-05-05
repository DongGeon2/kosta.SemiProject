<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 
	post write.jsp
	나라별 게시판 글 작성 jsp
	필요한것 : 
		카테고리(분류), 글제목, 글내용
 -->
<div class="card shadow mb-4">
	<div class="card-header py-3">
		<h6 class="m-0 font-weight-bold text-primary">프랑스 게시판</h6>
	</div>
	<div class="card-body">
		<form action="post" action="">
			<div class="form-group">
				<label for="catergory">분류</label> 
				<select class="form-control" id="catergory" name="catergory">
					<option selected>정보</option>
					<option>동행</option>
					<option>후기</option>
				</select>
			</div>
			<div class="form-group">
				<label for="postTitle">제목</label> <input type="text"
					class="form-control" placeholder="제목을 입력해 주세요" id="postTitle">
			</div>
			<div class="form-group">
				<label for="postContent">내용</label>
				<textarea class="form-control" rows="10" id="postContent" placeholder="내용을 입력해 주세요"></textarea>
			</div>
		</form>
		<div class="btnWrap">
			<button type="button" class="btn btn-outline-primary">
			<i class="fas fa-fw fa-pencil-alt"></i>등록</button>
			<button type="button" class="btn btn-outline-primary">
			<i class="fas fa-fw fa-times"></i> 취소</button>
		</div>
	</div>
</div>