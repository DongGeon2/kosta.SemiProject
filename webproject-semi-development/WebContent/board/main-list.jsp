<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 
	main-list.jsp
	index의 전체 게시판 끌어오기
 -->
 <script>
	function deletePost() {
		var flag = false;
		if (confirm("글을 삭제하시겠습니까?")) {
			flag = true;
		}
		return flag;
	}
</script>
<div class="card shadow mb-4">
	<div class="card-header py-3">
		<h6 class="m-0 font-weight-bold text-primary">전체 나라별 게시판</h6>
	</div>
	<div class="card-body">
		<div class="table-responsive">
			<table class="table table-bordered table-hover">
				<colgroup>
					<col width="7%" />
					<col width="10%" />
					<col width="10%" />
					<col width="*" />
					<col width="15%" />
					<col width="15%" />
					<col width="10%" />
				</colgroup>
				<thead class="bg-gray-200">
					<tr>
						<!-- col : 7 개 -->
						<th>글번호</th>
						<th>국가</th>
						<th>분류</th>
						<!-- 분류는 카테고리 입니다. -->
						<th>제목</th>
						<th>작성자</th>
						<th>작성일</th>
						<th>조회수</th>
						<c:if test="${sessionScope.mgvo!=null}">
							<th>삭제</th>
						</c:if>
					</tr>
				</thead>
				<tbody>

					<c:forEach var="pvo" items="${requestScope.list}">
						<tr>
							<td>${pvo.postNo}</td>
							<td>${pvo.countryVO.countryName}</td>
							<td>${pvo.catergory}</td>
							<td><c:choose>
									<c:when test="${sessionScope.mvo!=null || sessionScope.mgvo!=null}">
										<a
											href="${pageContext.request.contextPath}/PostDetailController.do?postNo=${pvo.postNo}">${pvo.postTitle}
											<span style="color:DodgerBlue">[${pvo.comment_count}]</span>	
										</a>
									</c:when>
									<c:otherwise>
									${pvo.postTitle}
					   			</c:otherwise>
								</c:choose></td>
							<td>${pvo.memberVO.id}</td>
							<td>${pvo.postTime}</td>
							<td>${pvo.hits}</td>
							<c:if test="${sessionScope.mgvo!=null}">
								<form name="deleteForm" action="${pageContext.request.contextPath}/DeletePostController.do" method="post" onsubmit="return deletePost();">
									<td>
									<input type="hidden" name="postNo" value="${pvo.postNo}">
									<button type="submit" class="btn btn-outline-primary btn-sm">
										<i class="fas fa-fw fa-times"></i> 삭제
									</button>
									</td>
								</form>
							</c:if>
						</tr>
					</c:forEach>

				</tbody>
			</table>
		</div>
		<div class="btnWrap">
			<c:choose>
				<c:when test="${sessionScope.mvo!=null}">
					<form action="WritePostFormController.do" method="post">
						<button type="submit" class="btn btn btn-outline-secondary btn-sm" ><i class="fas fa-fw fa-pencil-alt"></i> 글쓰기</button>
					</form>
				</c:when>
			</c:choose>
		</div>
		<!-- Pagination -->
		<c:set var="pb" value="${requestScope.pagingBean}"></c:set>
		<ul class="pagination justify-content-center" style="margin-top: 50px">
			<c:if test="${pb.previousPageGroup}">
				<li class="page-item"><a
					href="${pageContext.request.contextPath}/${controller }.do?pageNo=${pb.startPageOfPageGroup-1}&${queryString}">&laquo;</a></li>
			</c:if>
			<c:forEach var="page" begin="${pb.startPageOfPageGroup}" end="${pb.endPageOfPageGroup}">
				<c:choose>
					<c:when test="${pb.nowPage==page}">
						<li class="page-item active"><a class="page-link"
							href="${pageContext.request.contextPath}/${controller }.do?pageNo=${page}&${queryString}">${page}</a></li>
					</c:when>
					<c:otherwise>
						<li><a class="page-link" href="${pageContext.request.contextPath}/${controller }.do?pageNo=${page}&${queryString}">${page}</a></li>
				</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:if test="${pb.nextPageGroup}">
				<li><a
					href="${pageContext.request.contextPath}/${controller}.do?pageNo=${pb.endPageOfPageGroup+1}&${queryString}">&raquo;</a></li>
			</c:if>
		</ul>
		<!-- /.Pagination -->
	</div>
</div>