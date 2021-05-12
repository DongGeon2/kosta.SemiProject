<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 
	member-detail-info
	회원 활동 기록 불러오기
 -->
<div class="card shadow mb-4">
	<div class="card-header py-3">
		<h6 class="m-0 font-weight-bold text-primary">${requestScope.id}님
			활동 내역</h6>
	</div>
	<div class="card-body">
		<div class="table-responsive">
			<table class="table table-bordered table-hover">
				<colgroup>
					<col width="20%" />
					<col width="*" />
					<col width="15%" />
				</colgroup>
				<thead class="bg-gray-200">
					<tr>
						<!-- col : 7 개 -->
						<th>날짜</th>
						<th>활동 내역</th>
						<!-- 분류는 카테고리 입니다. -->
						<th>포인트</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="acted" items="${requestScope.list}">
						<tr>
							<td>${acted.acted_time}</td>
							<td>${acted.message}</td>
							<td>${acted.point}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<!-- Pagination -->
		<c:set var="pb" value="${requestScope.pagingBean}"></c:set>
		<ul class="pagination justify-content-center" style="margin-top: 50px">
			<c:if test="${pb.previousPageGroup}">
				<li class="page-item"><a class="page-link"
					href="${pageContext.request.contextPath}/MyDetailInfoController.do?pageNo=${pb.startPageOfPageGroup-1}">&laquo;</a></li>
			</c:if>
			<c:forEach var="page" begin="${pb.startPageOfPageGroup}"
				end="${pb.endPageOfPageGroup}">
				<c:choose>
					<c:when test="">
						<li class="page-item active"><a class="page-link"
							href="${pageContext.request.contextPath}/MyDetailInfoController.do?pageNo=${page}">${page}</a></li>
					</c:when>
					<c:otherwise>
						<li><a class="page-link"
							href="${pageContext.request.contextPath}/MyDetailInfoController.do?pageNo=${page}">${page}</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:if test="${pb.nextPageGroup}">
				<li><a class="page-link"
					href="${pageContext.request.contextPath}/MyDetailInfoController.do?pageNo=${pb.endPageOfPageGroup+1}">&raquo;</a></li>
			</c:if>
		</ul>
		<!-- /.Pagination -->
	</div>
</div>
