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
		document.MoveForm.submit();
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
	function commentPost() {
		if (confirm("댓글을 등록하시겠습니까?")) {
			document.commentForm.submit();
		}

	}
	function DeleteComment() {
		var flag = false;
		if (confirm("댓글을 삭제하시겠습니까?")) {
			flag = true;
		}
		return flag;
	}
</script>
<script>
	let likeFlag;
	function like(flag){
		likeFlag=flag;
		let postNo=document.getElementById("postNo").value;
		xhr = new XMLHttpRequest(); //Ajax를 위한 자바스크립트 객체
		xhr.onreadystatechange=callback; //서버가 응답할때 callback 함수를 실행하기 위한 코드
		xhr.open("POST","LikeController.do",true);
		xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		xhr.send("postNo="+postNo);
		/* xhr.open("GET","LikeController.do?postNo="+postNo);
		xhr.send(null); */
	}
	function callback(){
		
		//readyState가 4: 서버의 응답정보를 받은 상태
		//status가 200: 정상 수행
		if(xhr.readyState==4 && xhr.status==200){
			//alert(likeFlag+" "+xhr.responseText);
			//xhr.responseText : 서버의 응답데이터를 저장하는 변수
			if(likeFlag==false){
				document.getElementById("likeView").innerHTML="<input type='image' id='yes' src='images/yes-heart.png' onclick='like(true)'>";
			}else{
				document.getElementById("likeView").innerHTML="<input type='image' id='no' src='images/no-heart.png' onclick='like(false)'>";
			}
			document.getElementById("like_result").innerHTML=xhr.responseText;
			
		}//if
	}//callback
</script>
<div class="card shadow mb-4">
	<div class="card-header py-3">
		<h6 class="m-0 font-weight-bold text-primary">${requestScope.pvo.countryVO.countryName}
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
					<td>${pvo.catergory}</td>
					<th class="table-active">한국작성일</th>
					<td>${time[0]}</td>
					<th class="table-active">현지작성일</th>
					<!-- 이 th 이름 바꿔주시면 될 것 같아요  -->
					<td>${time[2]}</td>
				</tr>
				<tr>
				<c:set var="fvo" value="${requestScope.fvo }"></c:set>
					<th class="table-active">제목</th>
					<td colspan="3">${pvo.postTitle}</td>
					<th class="table-active">첨부파일</th>
					<c:choose>
						<c:when test="${fvo!=null }">
							<td><a href="DownloadController.do?postNo=${fvo.postVO.postNo }&fileName=${fvo.fileName}"><i class="fas fa-link"></i>${fvo.fileName }</a></td>
						</c:when>
						<c:otherwise>
							<td>없음</td>
						</c:otherwise>
					</c:choose>
				</tr>
				<tr>
					<td colspan="6" class="cotentWrap"><pre>${pvo.postContent}</pre></td>
				</tr>
			</table>
			
			<c:choose>
				<c:when test="${sessionScope.mvo!=null}">
					<input type="hidden" id="postNo" value="${requestScope.pvo.postNo }">
					<span id="likeView">
					<c:choose>
						<c:when test="${requestScope.lvo==null}">
							<input type="image" id="no" src="images/no-heart.png" onclick="like(false)">
							<!-- <input type="button" value="좋아요!" onclick="return like()"> -->
						</c:when>
						<c:otherwise>
							<input type="image" id="yes" src="images/yes-heart.png" onclick="like(true)">
						</c:otherwise>
					</c:choose>
					</span>
					<span id="like_result">${requestScope.totalLike }</span>
				</c:when>
				<c:otherwise>
					<img alt="좋아요" src="images/no-heart.png">
					<span>${requestScope.totalLike }</span>
				</c:otherwise> 			
			</c:choose>

			<div class="btnWrap">
				<!-- submit 을 위한 form -->
				<form name="MoveForm" action="${pageContext.request.contextPath}/IndividualListBycountryController.do" method="post">
					<input type="hidden" name="countryId"
						value="${requestScope.pvo.countryVO.countryId}">
				</form>
				<form name="deleteForm"
					action="${pageContext.request.contextPath}/DeletePostController.do"
					method="post">
					<input type="hidden" name="postNo"
						value="${requestScope.pvo.postNo}">
				</form>
				<form name="updateForm"
					action="${pageContext.request.contextPath}/UpdatePostFormController.do"
					method="post">
					<input type="hidden" name="postNo"
						value="${requestScope.pvo.postNo}">
				</form>
				<!-- 모든 사용자는 목록 btn을 볼 수 있다. -->
				<button type="button" class="btn btn-outline-primary"
					onclick="MoveList()">
					<i class="fas fa-fw fa-pencil-alt"></i> 목록
				</button>
				<!-- 내가 쓴 글만 수정 삭제 가능 -->
				
				<c:if test="${requestScope.pvo.memberVO.id==sessionScope.mvo.id}">
					<button type="button" class="btn btn-outline-primary"
						onclick="updatePost()">
						<i class="fas fa-fw fa-pencil-alt"></i> 수정
					</button>
					<button type="button" class="btn btn-outline-primary"
						onclick="deletePost()">
						<i class="fas fa-fw fa-times"></i> 삭제
					</button>
				</c:if>
				<c:if test="${sessionScope.mgvo != null}">
					<button type="button" class="btn btn-outline-primary"
						onclick="deletePost()">
						<i class="fas fa-fw fa-times"></i> 삭제
					</button>
				</c:if>
			</div>
			<hr>
			<!-- 댓글 리스트를 불러오기  -->
			<c:if test="${requestScope.commentList !=null}">
				<c:forEach var="comment" items="${requestScope.commentList}">
					<div>
						<!-- ID, 작성날짜 -->
						${comment.memberVO.id}<br> <font size="2" color="lightgray">${comment.commentedTime}</font>
						<!-- 본문내용 -->
						<div class="text_wrapper">${comment.commentContent}</div>
						<!-- 댓글 작성자만 수정 가능 -->
						<c:if test="${comment.memberVO.id==sessionScope.mvo.id}">
							<div class="btnWrap">
								<form name="updateComment"
									action="${pageContext.request.contextPath}/UpdateCommentFormController.do"
									method="post" style="display: inline">
									<input type="hidden" name="commentNo"
										value="${comment.commentNo}">
									<button type="submit" class="btn btn-sm">
										수정</button>
								</form>
								<form name="deleteComment"
									action="${pageContext.request.contextPath}/DeleteCommentController.do"
									method="post" style="display: inline" onsubmit="return DeleteComment();">
									<input type="hidden" name="commentNo"
										value="${comment.commentNo}">
										<input type="hidden" name="postNo"
										value="${pvo.postNo}">
									<button type="submit" class="btn btn-sm">
										삭제</button>
								</form>
							</div>
						</c:if>
						<hr>
					</div>
				</c:forEach>
			</c:if>

			<!-- 로그인 시 댓글 작성 가능 -->
			<c:if test="${sessionScope.mvo!=null}">
				<div class="commentWrap">
					<form name="commentForm"
						action="${pageContext.request.contextPath}/WriteCommentController.do"
						method="post">
						<input type="hidden" name="postNo"
							value="${requestScope.pvo.postNo}"> <input type="hidden"
							name="memberId" value="${sessionScope.mvo.id}">

						<div>${sessionScope.mvo.id}</div>
						<div class="form-group">
							<label for="comment"></label>
							<textarea class="form-control" name="commentContent" rows="2"
								placeholder="댓글을 작성해 주세요" required="required"></textarea>
						</div>
					</form>

					<div class="btnWrap">
						<!-- 댓글등록버튼 -->
						<button type="button" class="btn btn-outline-success btn-sm"
							onclick="commentPost()">
							<i class="fas fa-fw fa-pencil-alt"></i>등록
						</button>
					</div>
				</div>
			</c:if>
		</div>
	</div>
</div>