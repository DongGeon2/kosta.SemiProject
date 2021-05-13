<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 여행 취향</title>
<script type="text/javascript">
	alert("프리미엄 게시판에 입장하셨습니다.\n현재 포인트: ${point}");
</script>
</head>
<body>
	<!-- 셀렉트박스로 선호취향 받기. <주제, 보기항목> map을 getAttribute해서 forEach이용해 동적으로 생성
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
								action="TravelStyleMatchController.do" onsubmit="">
								<c:forEach items="${requestScope.map }" var="map"
									varStatus="status">
									<div class="container">
										<p>${map.key }</p>
										<c:forEach items="${map.value }" var="radio" varStatus = "type">
											<div class="form-check-inline">
												<label class="form-check-label" for="radio1"> <input
													type="radio" class="form-check-input"
													name="style${status.count}" value="${radio}" checked>${radio}
													<input type="hidden" name="index${status.count}" value="${type.count}">
												</label>
											</div>
										</c:forEach>
									</div>
								&nbsp;
								</c:forEach>

								<div class="text-center">
								새로 로그인 할때마다 제출할 수 있습니다<br>
<!-- 								제출은 한번만 가능하니 신중하게 골라주세요!<br>
 -->									<input type="submit" class="btn btn-primary"
										value="나와 같은 여행 스타일 가진 분 계실까요?">
										
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