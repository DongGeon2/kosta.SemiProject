<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 셀렉트박스로 선호취향 받기
사람 많은곳/적은곳/무관
저예산/가성비/럭셔리
맛집/쇼핑/숙소
 -->
<body class="bg-gradient-primary">

	<div class="container">
		<div class="card o-hidden border-0 shadow-lg my-5">
			<div class="card-body p-0">
				<!-- Nested Row within Card Body -->
				<div class="row">
					<div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
					<div class="col-lg-7">
						<div class="p-5">
							<div class="text-center">
								<h1 class="h4 text-gray-900 mb-4">나의 여행 취향</h1>
							</div>
							<form class="user" name="travelStyleForm" method="post"
								action="${pageContext.request.contextPath}/RegisterController.do"
								onsubmit="">
								
								<div class="container">
									<p>인파</p>
									<c:set var="testVar">사람 많은 곳,사람 적은 곳,무관</c:set>
									<c:forTokens items="${testVar }" delims="," var="value">
										<div class="form-check-inline">
											<label class="form-check-label" for="radio1"> <input
												type="radio" class="form-check-input" name="crowd"
												value="M" checked>${value}
											</label>
										</div>
									</c:forTokens>
								</div>
								&nbsp;
	
								<div class="container">
									<p>소비</p>
									<c:set var="testVar">저예산,럭셔리,가성비</c:set>
									<c:forTokens items="${testVar }" delims="," var="value">
										<div class="form-check-inline">
											<label class="form-check-label" for="radio1"> <input
												type="radio" class="form-check-input" name="budget"
												value="M" checked>${value}
											</label>
										</div>
									</c:forTokens>
								</div>
								&nbsp;

								<div class="container">
									<p>스타일1</p>
									<c:set var="testVar">큰계획,세부계획,즉흥,</c:set>
									<c:forTokens items="${testVar }" delims="," var="value">
										<div class="form-check-inline">
											<label class="form-check-label" for="radio1"> <input
												type="radio" class="form-check-input" name="style1"
												value="M" checked>${value}
											</label>
										</div>
									</c:forTokens>
								</div>
								&nbsp;
								
								<div class="container">
									<p>스타일2</p>
									<c:set var="testVar">음식,쇼핑,숙소</c:set>
									<c:forTokens items="${testVar }" delims="," var="value">
										<div class="form-check-inline">
											<label class="form-check-label" for="radio1"> <input
												type="radio" class="form-check-input" name="style2"
												value="M" checked>${value}
											</label>
										</div>
									</c:forTokens>
								</div>
								&nbsp;
								<div class="text-center">
									<input type="submit" class="btn btn-primary" value="나와 같은 여행 스타일 가진 분 계실까요?">
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>









</body>
</html>