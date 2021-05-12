<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<c:if test="${sessionScope.mvo!=null}">
				<div class="commentWrap">
					<form name="commentFormv" 
						action="${pageContext.request.contextPath}/UpdateCommentController.do"
						method="post">
						<input type="hidden" name="commentNo"
							value="${requestScope.cvo.commentNo}"> 
						<input type="hidden" name="postNo"
							value="${requestScope.cvo.postVO.postNo}">	

						<div>${sessionScope.mvo.id}</div>
						<div class="form-group">
							<label for="comment"></label>
							<textarea class="form-control" name="commentContent" rows="2"
								required="required" values="${requestScope.cvo.commentContent}">${requestScope.cvo.commentContent}</textarea>
						</div>
			<div class="btnWrap"> 
				<button type="submit" class="btn btn-outline-primary">
				<i class="fas fa-fw fa-pencil-alt"></i> 수정</button>
				<button onclick="location='/board/post-detail.jsp'" class="btn btn-outline-primary">
				<i class="fas fa-fw fa-times"></i> 취소</button>
			</div>
					</form>

					
				</div>
			</c:if>