<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
					</tr>
				</thead>
				<tbody>
				 <c:forEach var="pvo" items="${requestScope.list}">
				   <td>${pvo.postNo}</td>
				   <td>${pvo.countryVO.countryName}</td>
				   <td>${pvo.postcategory}</td>
				   		<td><c:choose>
				   			<c:when test="${sessionScope.mvo!=null}">
				   				<a href="${pageContext.request.contextPath}/PostDetailController.do?countryId=${pvo.countryVO.countryId}">${pvo.postTitle}
				   			</c:when>
				   			<c:otherwise>
								${pvo.postTitle}
				   			</c:otherwise>
				   			</c:choose></td>
						<td>${pvo.memberVO.name}</td>
						<td>${pvo.timePosted}</td>
						<td>${pvo.hits}</td>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<!-- Pagination -->
		<ul class="pagination justify-content-center" style="margin-top: 50px">
			<li class="page-item"><a class="page-link" href="#">Previous</a></li>
			<li class="page-item"><a class="page-link" href="#">1</a></li>
			<li class="page-item active"><a class="page-link" href="#">2</a></li>
			<li class="page-item"><a class="page-link" href="#">3</a></li>
			<li class="page-item"><a class="page-link" href="#">Next</a></li>
		</ul>
		<!-- /.Pagination -->
	</div>
</div>