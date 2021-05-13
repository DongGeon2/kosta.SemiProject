<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 
	board-list.jsp
	나라별 게시판 list 불러오기
 -->
<script>
	function deletePost() {
		if (confirm("게시글을 삭제하시겠습니까?")) {
			document.deleteForm.submit();
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
			<table class="table table-bordered table-hover">
				<colgroup>
					<col width="7%" />
					<col width="10%" />
					<col width="*" />
					<col width="15%" />
					<col width="15%" />
					<col width="10%" />
					<c:if test="${requestScope.pvo.memberVO.id==sessionScope.mvo.id}">
						<col width="10%" />
					</c:if>
				</colgroup>
				<thead class="bg-gray-200">
					<tr>
						<!-- col : 7 개 -->
						<th>글번호</th>
						<th>분류</th>
						<!-- 분류는 카테고리 입니다. -->
						<th>제목</th>
						<th>작성자</th>
						<th>작성일</th>
						<th>조회수</th>
						<c:if test="${requestScope.pvo.memberVO.id==sessionScope.mvo.id}">
							<th>삭제</th>
						</c:if>

					</tr>
				</thead>
				<tbody>
					<c:forEach var="pvo" items="${requestScope.list}">
						<tr>
							<td>${pvo.postNo}</td>
							<td>${pvo.catergory}</td>
							<td><c:choose>
									<c:when test="${sessionScope.mvo!=null || sessionScope.mgvo!=null}">
										<a
											href="${pageContext.request.contextPath}/PostDetailController.do?postNo=${pvo.postNo}">${pvo.postTitle}
										</a>
									</c:when>
									<c:otherwise>
									${pvo.postTitle}
					   			</c:otherwise>
								</c:choose></td>
							<td>${pvo.memberVO.id}</td>
							<td>${pvo.postTime}</td>
							<td>${pvo.hits}</td>
							<c:if test="${requestScope.pvo.memberVO.id==sessionScope.mvo.id}">
								<td>
									<button type="button" class="btn btn-outline-primary btn-sm"
										onclick="deletePost()">
										<i class="fas fa-fw fa-times"></i> 삭제
									</button>
								</td>
								<form name="deleteForm"
										action="${pageContext.request.contextPath}/DeletePostController.do"
										method="post">
										<input type="hidden" name="pageNo" value="${pvo.postNo}">
									</form>
							</c:if>


						</tr>
					</c:forEach>
				</tbody>
			</table>
			<c:choose>
				<c:when test="${sessionScope.mvo!=null}">
					<form action="WritePostFormController.do" method="post">
						<div class="btnWrap">
							<button type="submit" class="btn btn btn-outline-secondary btn-sm">
							<i class="fas fa-fw fa-pencil-alt"></i> 글쓰기</button>
						</div>
					</form>
				</c:when>
			</c:choose>
		</div>
		<!-- Pagination -->
		<c:set var="pb" value="${requestScope.pagingBean}"></c:set>
		<ul class="pagination justify-content-center" style="margin-top: 50px">
			<c:if test="${pb.previousPageGroup}">
				<li class="page-item"><a class="page-link"
					href="${pageContext.request.contextPath}/AllListController.do?pageNo=${pb.startPageOfPageGroup-1}">&laquo;</a></li>
			</c:if>
			<c:forEach var="page" begin="${pb.startPageOfPageGroup}"
				end="${pb.endPageOfPageGroup}">
				<c:choose>
					<c:when test="${pb.nowPage==page}">
						<li class="page-item active"><a class="page-link"
							href="${pageContext.request.contextPath}/AllListController.do?pageNo=${page}">${page}</a></li>
					</c:when>
					<c:otherwise>
						<li><a class="page-link"
							href="${pageContext.request.contextPath}/AllListController.do?pageNo=${page}">${page}</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:if test="${pb.nextPageGroup}">
				<li><a class="page-link"
					href="${pageContext.request.contextPath}/AllListController.do?pageNo=${pb.endPageOfPageGroup+1}">&raquo;</a></li>
			</c:if>
		</ul>
		<!-- /.Pagination -->
	</div>
</div>
