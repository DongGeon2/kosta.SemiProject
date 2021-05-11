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
		<h6 class="m-0 font-weight-bold text-primary">전체 게시판</h6>
	</div>
	<div class="card-body">
		<form method="post" action="WritePostController.do" enctype="multipart/form-data">
			<div class="form-group">
				<%-- <c:choose>
					<c:when test="${sessionScope.mvo!=null }">
						현재 글을쓰면 ${sessionScope.mvo.countryVO.countryName } 게시판으로 글이 올라갑니다.<br><br>
					</c:when>
				</c:choose>  --%>
				<label for="catergory">분류</label> 
				<select class="form-control" id="catergory" name="catergory">
					<option selected>정보</option>
					<option>동행</option>
					<option>후기</option>
				</select>
				<label for="catergory">나라</label>
					<select class="form-control" name="countryId">
						<option value="44"<c:if test="${sessionScope.mvo.countryVO.countryId==44 }">selected</c:if>>영국</option>
						<option value="39"<c:if test="${sessionScope.mvo.countryVO.countryId==39 }">selected</c:if>>이탈리아</option>
						<option value="49"<c:if test="${sessionScope.mvo.countryVO.countryId==49 }">selected</c:if>>독일</option>
						<option value="33"<c:if test="${sessionScope.mvo.countryVO.countryId==33 }">selected</c:if>>프랑스</option>
					</select>
			</div>
			<div class="form-group">
				<label for="postTitle">제목</label>
				<input type="text" class="form-control" placeholder="제목을 입력해 주세요" name="postTitle">
			</div>
			<div class="form-group">
				<label for="postContent">내용</label>
				<textarea class="form-control" rows="10" name="postContent" placeholder="내용을 입력해 주세요"></textarea>
			</div>
			<input type="file" name="filename">
			<hr>
			<div class="btnWrap">
				<button type="submit" class="btn btn-outline-primary">
				<i class="fas fa-fw fa-pencil-alt"></i>등록</button>
				<button type="reset" class="btn btn-outline-primary">
				<i class="fas fa-fw fa-times"></i> 취소</button>
			</div>
		</form>
	</div>
</div>