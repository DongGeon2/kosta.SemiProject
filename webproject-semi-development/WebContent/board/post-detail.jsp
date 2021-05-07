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
	function MoveList() {
		alert("MoveForm");
	}
	
	function deletePost() {
		if (confirm("게시글을 삭제하시겠습니까?")) {
			document.deleteForm.submit();
		}
	}
	
	function updatePost() {
		if (confirm("게시글을 수정하시겠습니까?")) {
			document.updateForm.submit();
		}
	}
</script>
<div class="card shadow mb-4">
	<div class="card-header py-3">
		<h6 class="m-0 font-weight-bold text-primary">${requestScope.countryName}
			게시판</h6>
	</div>
	<div class="card-body">
		<div class="table-responsive">
			<table class="table mytable table-bordered">
				<colgroup>
					<col width="13%" />
					<col width="17%" />
					<col width="13%" />
					<col width="17%" />
					<col width="13%" />
					<col width="17%" />
				</colgroup>
				<tr>
					<th class="table-active">글번호</th>
					<td>${pvo.postNo}</td>
					<th class="table-active">조회수</th>
					<td>${pvo.hits}</td>
					<th class="table-active">작성자</th>
					<td>${pvo.memberVO.id}</td>
				</tr>
				<tr>
					<th class="table-active">분류</th>
					<td colspan="3">${pvo.catergory}</td>
					<th class="table-active">작성일</th>
					<td>${pvo.postTime}</td>
				</tr>
				<tr>
					<th class="table-active">제목</th>
					<td colspan="5">${pvo.postTitle}</td>
				</tr>
				<tr>
					<td colspan="6" class="cotentWrap"><pre>${pvo.postContent}</pre></td>
				</tr>
			</table>
			<div class="btnWrap">
				<!-- submit 을 위한 form -->
				<form name="MoveForm"
					action=""
					method="post">
					<input type="hidden" name="pageNo"
						value="${requestScope.pvo.postNo}">
				</form>
				<form name="deleteForm"
					action="${pageContext.request.contextPath}/DeletePostController.do"
					method="post">
					<input type="hidden" name="pageNo"
						value="${requestScope.pvo.postNo}">
				</form>
				<form name="updateForm"
					action="${pageContext.request.contextPath}/UpdateMemberController.do"
					method="post">
					<input type="hidden" name="pageNo"
						value="${requestScope.pvo.postNo}">
				</form>
				<!-- 모든 사용자는 목록 btn을 볼 수 있다. -->
				<button type="button" class="btn btn-outline-primary" onclick="MoveList()">
					<i class="fas fa-fw fa-pencil-alt"></i> 목록
				</button>
				<!-- 내가 쓴 글만 수정 삭제 가능 -->
				<c:if test="${requestScope.pvo.memberVO.id==sessionScope.mvo.id}">
<<<<<<< HEAD
					<button type="button" class="btn btn-outline-primary" onclick="updatePost()">
						<i class="fas fa-fw fa-pencil-alt"></i> 수정
					</button>
					<button type="button" class="btn btn-outline-primary" onclick="deletePost()">
						<i class="fas fa-fw fa-times"></i> 삭제
					</button>
=======
				<	<form name="deleteForm"
						action="${pageContext.request.contextPath}/UpdateMemberController.do" method="post">
						<input 	type="hidden" name="pageNo" value="${requestScope.pvo.postNo}">
					</form>				
					<form name="updateForm"
						action="${pageContext.request.contextPath}/UpdateMemberController.do" method="post">
						<input 	type="hidden" name="pageNo" value="${requestScope.pvo.postNo}">
					</form>
					<button type="button" class="btn btn-outline-primary">
					<i class="fas fa-fw fa-pencil-alt"></i> 수정</button>
					<button type="button" class="btn btn-outline-primary">
					<i class="fas fa-fw fa-times"></i> 삭제</button>
>>>>>>> branch 'main' of https://github.com/DongGeon2/kosta.SemiProject.git
				</c:if>

			</div>
		</div>
	</div>
</div>