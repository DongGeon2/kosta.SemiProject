<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<script type="text/javascript">
	function checkRegForm() {
		let cf = document.registerForm;
		if (cf.password.value != cf.confirmPassword.value) {
			alert("비밀번호와 확인란이 일치하지 않습니다");
			return false;
		}
		if (cf.id.value != cf.flag.value) {
			alert("인증받은 아이디가 아닙니다\n아이디 중복확인을 하세요");
			i
			return false;
		}
	}
	function checkId() {
		// id 입력여부 체크 
		let id = document.registerForm.id.value;
		if (id == "") {
			alert("아이디를 입력하세요!");
		} else {
			//1. 팝업url?id=data , 2. 별칭 , 3. 팝업옵션 
			window.open("../IdCheckController.do?id=" + id, "mypopup",
					"width=450,height=250,top=150,left=400");
		}
	}
	function welcome() {
		// 회원가입 확인
		alert("환영합니다~ 회원가입이 완료되었습니다!")
	}
	
</script>



<%-- --%>
<title>한국인 모여라, 회원가입</title>
<!-- Custom fonts for this template-->
<link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
	type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link href="${pageContext.request.contextPath}/css/sb-admin-2.min.css"
	rel="stylesheet">

</head>
<%-- 회원가입 : 이름, id, password, password확인, 이메일, 생년월일, 성별, 여행스타일, 현재국가--%>
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
								<h1 class="h4 text-gray-900 mb-4">회원가입</h1>
							</div>
							<form class="user" name="registerForm" method="post"
								action="${pageContext.request.contextPath}/RegisterController.do"
								onsubmit="return checkRegForm()">
								<div class="form-group">
									<input type="text" class="form-control form-control-user"
										name="name" placeholder="이름 (한글만 입력 가능합니다)"
										onkeypress="if(!(event.keyCode < 47 && event.keyCode > 58)) event.returnValue=false;">
								</div>
								<div class="form-group row">
									<div class="col-sm-6 mb-3 mb-sm-0">
										<input type="text" class="form-control form-control-user"
											name="id" placeholder="ID">
									</div>
									<div class="col-sm-6">
										<input type="button" class="form-control form-control-user"
											value="중복확인" onclick="checkId()">
									</div>
								</div>
								<div class="form-group row">
									<div class="col-sm-6 mb-3 mb-sm-0">
										<input type="password" class="form-control form-control-user"
											name="password" placeholder="Password">
									</div>
									<div class="col-sm-6">
										<input type="password" class="form-control form-control-user"
											name="confirmPassword" placeholder="Password 확인">
									</div>
								</div>
								<div class="form-group">
									<input type="email" class="form-control form-control-user"
										name="email" placeholder="이메일 주소">
								</div>
								<div class="container">
									<p>생일을 선택해주세요 (ex.1993-08-22)</p>
								</div>

								<div class="form-group">
									<input type="date" class="form-control form-control-user"
										name="birth" placeholder="생년월일" value="date">
								</div>
								<div class="container">
									<p>성별</p>
									<div class="form-check-inline">
										<label class="form-check-label" for="radio1"> <input
											type="radio" class="form-check-input" id="Male" name="gender"
											value="M" checked>남성
										</label>
									</div>
									<div class="form-check-inline">
										<label class="form-check-label" for="radio2"> <input
											type="radio" class="form-check-input" id="Female"
											name="gender" value="F">여성
										</label>
									</div>
								</div>
								&nbsp;

								<div class="container">
									<p>여행스타일</p>
									<div class="form-check-inline">
										<label class="form-check-label" for="radio1"> <input
											type="radio" class="form-check-input" id="radio1"
											name="travelstyle" value="사진" checked>사진
										</label>
									</div>
									<div class="form-check-inline">
										<label class="form-check-label" for="radio2"> <input
											type="radio" class="form-check-input" id="radio2"
											name="travelstyle" value="맛집">맛집
										</label>
									</div>
									<div class="form-check-inline">
										<label class="form-check-label" for="radio3"> <input
											type="radio" class="form-check-input" id="radio3"
											name="travelstyle" value="뚜벅이">뚜벅이
										</label>
									</div>
									<div class="form-check-inline">
										<label class="form-check-label" for="radio4"> <input
											type="radio" class="form-check-input" id="radio4"
											name="travelstyle" value="리무진">리무진
										</label>
									</div>
								</div>
								&nbsp;

								<div class="container">
									<p>현재국가</p>
									<div class="form-check-inline">
										<label class="form-check-label" for="radio1"> <input
											type="radio" class="form-check-input" id="radio1"
											name="countryId" value="82" checked>한국
										</label>
									</div>
									<div class="form-check-inline">
										<label class="form-check-label" for="radio2"> <input
											type="radio" class="form-check-input" id="radio2"
											name="countryId" value="44">영국
										</label>
									</div>
									<div class="form-check-inline">
										<label class="form-check-label" for="radio3"> <input
											type="radio" class="form-check-input" id="radio3"
											name="countryId" value="49">독일
										</label>
									</div>
									<div class="form-check-inline">
										<label class="form-check-label" for="radio4"> <input
											type="radio" class="form-check-input" id="radio4"
											name="countryId" value="33">프랑스
										</label>
									</div>
									<div class="form-check-inline">
										<label class="form-check-label" for="radio5"> <input
											type="radio" class="form-check-input" id="radio5"
											name="countryId" value="39">이탈리아
										</label>
									</div>
								</div>
								<div class="text-center">
									<input type="submit" class="btn btn-primary" value="함께 여행하기" onclick="welcome()">
								</div>
							</form>

							<hr>

							<div class="text-center">
								<a class="small"
									href="${pageContext.request.contextPath}/member/findMyId.jsp">아이디
									찾기</a>
							</div>
							<div class="text-center">
								<a class="small"
									href="${pageContext.request.contextPath}/member/findMyPassword.jsp">비밀번호
									찾기</a>
								<div class="text-center">
									<a class="small"
										href="${pageContext.request.contextPath}/index.jsp">Home</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Bootstrap core JavaScript-->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="vendor/jquery-easing/jquery.easing.min.js"></script>


	<!-- Custom scripts for all pages-->
	<script src="js/sb-admin-2.min.js"></script>
</body>
</html>