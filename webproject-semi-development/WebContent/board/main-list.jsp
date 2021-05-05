<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 
	main-list.jsp
	index의 전체 게시판 끌어오기
 -->
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
					<tr>
						<td>1</td>
						<td>프랑스</td>
						<td>정보</td>
						<td class="left"><a href="#">프랑스환전소</a></td>
						<td>java</td>
						<td>2021.05.04</td>
						<td>0</td>
					</tr>
					<tr>
						<td>2</td>
						<td>이탈리아</td>
						<td>동행</td>
						<td class="left"><a href="#">이탈리아에서 한식을 외치다</a></td>
						<td>spring</td>
						<td>2021.05.05</td>
						<td>0</td>
					</tr>
					<tr>
						<td>3</td>
						<td>영국</td>
						<td>후기</td>
						<td class="left"><a href="#">영국 맛집 플랫아이언 추천!</a></td>
						<td>mvc</td>
						<td>2021.05.06</td>
						<td>0</td>
					</tr>
					<tr>
						<td>4</td>
						<td>프랑스</td>
						<td>후기</td>
						<td class="left"><a href="#">독일 히틀러호텔 비추 ㅠ</a></td>
						<td>singleton</td>
						<td>2021.05.07</td>
						<td>0</td>
					</tr>
					<tr>
						<td>5</td>
						<td>프랑스</td>
						<td>정보</td>
						<td class="left"><a href="#">프랑스환전소</a></td>
						<td>java</td>
						<td>2021.05.04</td>
						<td>0</td>
					</tr>
					<tr>
						<td>6</td>
						<td>이탈리아</td>
						<td>동행</td>
						<td class="left"><a href="#">이탈리아에서 한식을 외치다</a></td>
						<td>spring</td>
						<td>2021.05.05</td>
						<td>0</td>
					</tr>
					<tr>
						<td>7</td>
						<td>영국</td>
						<td>후기</td>
						<td class="left"><a href="#">영국 맛집 플랫아이언 추천!</a></td>
						<td>mvc</td>
						<td>2021.05.06</td>
						<td>0</td>
					</tr>
					<tr>
						<td>8</td>
						<td>프랑스</td>
						<td>후기</td>
						<td class="left"><a href="#">독일 히틀러호텔 비추 ㅠ</a></td>
						<td>singleton</td>
						<td>2021.05.07</td>
						<td>0</td>
					</tr>
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