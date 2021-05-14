<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 
	member-list.jsp
	멤버 list 불러오기
 -->
<script>
	function message() {
		if (confirm("회원에게 쪽지를 보내시겠습니까?")) {
			document.deleteForm.submit();
		}
	}
</script>
<div class="card shadow mb-4">
	<div class="card-header py-3">
		<h6 class="m-0 font-weight-bold text-primary">회원관리</h6>
	</div>
	<div class="card-body">
		<div class="table-responsive">

			<form action="" onsubmit="return deleteMember()" method="post">

				<table class="table table-bordered table-hover">
					<colgroup>
						<col width="20%" />
						<col width="*" />
						<col width="15%" />
						<col width="15%" />
						<col width="15%" />
					</colgroup>
					<thead class="bg-gray-200">
						<tr>
							<!-- col : 7 개 -->
							<th>ID</th>
							<th>한마디</th>
							<th>여행스타일</th>
							<th>나이</th>
							<th>선택</th>

						</tr>
					</thead>
					<tbody>
						<c:forEach var="mvo" items="${requestScope.memberList}">
							<tr>
								<td>${mvo.id}</td>
								<td>${mvo.message}</td>
								<td>${travelType}</td>
								<td>${mvo.age}</td>
								<td><input type="checkbox" name="deleteId"
									value="${mvo.id}"> <%-- <form name="deleteForm"
								action="${pageContext.request.contextPath}/DeleteMemberController.do?id=${mvo.id}"
								method="post">
								<input type="hidden" name="id" value="${mvo.id}">
								<button type="button" class="btn btn-outline-primary btn-sm">
									<i class="fas fa-fw fa-times"></i> 삭제
								</button>
							</form> --%></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<hr>
				<div class="text-right">
					<input type="submit" class="btn btn-outline-primary btn-sm"
						value="메세지보내기">
				</div>
			</form>
		</div>
		<!-- Pagination -->
		<c:set var="pb" value="${requestScope.pagingBean}"></c:set>
		<ul class="pagination justify-content-center" style="margin-top: 50px">
			<c:if test="${pb.previousPageGroup}">
				<li class="page-item"><a class="page-link"
					href="${pageContext.request.contextPath}/MemberListController.do?pageNo=${pb.startPageOfPageGroup-1}">&laquo;</a></li>
			</c:if>
			<c:forEach var="page" begin="${pb.startPageOfPageGroup}"
				end="${pb.endPageOfPageGroup}">
				<c:choose>
					<c:when test="">
						<li class="page-item active"><a class="page-link"
							href="${pageContext.request.contextPath}/MemberListController.do?pageNo=${page}">${page}</a></li>
					</c:when>
					<c:otherwise>
						<li><a class="page-link"
							href="${pageContext.request.contextPath}/MemberListController.do?pageNo=${page}">${page}</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:if test="${pb.nextPageGroup}">
				<li><a class="page-link"
					href="${pageContext.request.contextPath}/MemberListController.do?pageNo=${pb.endPageOfPageGroup+1}">&raquo;</a></li>
			</c:if>
		</ul>
		<!-- /.Pagination -->
	</div>
</div>
