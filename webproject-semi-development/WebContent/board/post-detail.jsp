<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 
	post-update.jsp
	나라별 게시판 글 수정 jsp
	필요한것 : 
		카테고리(분류), 글제목, 글내용
 -->
<div class="card shadow mb-4">
	<div class="card-header py-3">
		<h6 class="m-0 font-weight-bold text-primary">프랑스 게시판</h6>
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
					<td>1</td>
					<th class="table-active">조회수</th>
					<td>111</td>
					<th class="table-active">작성자</th>
					<td>최인재</td>
				</tr>
				<tr>
					<th class="table-active">분류 </th>
					<td colspan="3">후기</td>
					<th class="table-active">작성일</th>
					<td>2021-05-05</td>
				</tr>
				<tr>
					<th class="table-active">제목</th>
					<td colspan="5">히틀러호텔 비추비추</td>
				</tr>
				<tr>
					<td colspan="6" class="cotentWrap">
					<pre>히틀러 호텔 비추비추 이렇게 친절하지 않을수가! 세상에서 제일 별로!<br>최악 최악 ! 핵최악!</pre></td>
				</tr>
			</table>
			<div class="btnWrap">
				<!-- 모든 사용자는 목록 btn을 볼 수 있다. -->
				<button type="button" class="btn btn-outline-primary">
				<i class="fas fa-fw fa-pencil-alt"></i> 목록</button>
				<button type="button" class="btn btn-outline-primary">
				<!-- 
					자기가 작성한 글 일때만 수정, 삭제 보이도록 구현
				 -->
				<i class="fas fa-fw fa-pencil-alt"></i> 수정</button>
				<button type="button" class="btn btn-outline-primary">
				<i class="fas fa-fw fa-times"></i> 삭제</button>
			</div>
		</div>
	</div>
</div>