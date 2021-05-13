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
		if (cf.id.value != cf.flagId.value) {
			alert("인증받은 아이디가 아닙니다\n아이디 중복확인을 하세요");
			return false;
		}
		if (cf.email.value != cf.flagEmail.value) {
			alert("인증받은 이메일이 아닙니다\n이메일 중복확인을 하세요");
			return false;
		}
		if (cf.name.value == "") {
			alert("이름을 입력하세요");
			return false;
		}
		if (cf.birth.value == "") {
			alert("생일을 입력하세요");
			return false;
		}
	}

	function checkId() {
		// id 입력여부 체크 
		let id = document.registerForm.id.value;
		if (id == "") {
			alert("아이디를 입력하세요!");
			return false;
		} else {
			//1. 팝업url?id=data , 2. 별칭 , 3. 팝업옵션 
			window.open("../IdCheckController.do?id=" + id, "mypopup",
					"width=450,height=250,top=150,left=400");
		}
	}

	function checkEmail() {
		// id 입력여부 체크 
		let email = document.registerForm.email.value;
		if (email == "") {
			alert("이메일을 입력하세요!");
			return false;
		} else {
			//1. 팝업url?id=data , 2. 별칭 , 3. 팝업옵션 
			window.open("../EmailCheckController.do?email=" + email,
					"emailpopup", "width=450,height=250,top=150,left=400");
		}
	}

	function welcome() {
		// 회원가입 확인
		alert("환영합니다~ 회원가입이 완료되었습니다!")
	}

	function len_chk() {
		var frm = document.registerForm.id;

		if (frm.value.length > 20) {
			alert("아이디는 최대 20자로 제한됩니다!");
			frm.value = frm.value.substring(0, 20);
		}
	}
</script>
<c:import url="/template/headerLink.jsp"></c:import>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
	Date nowTime = new Date();
	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
%>

<%-- --%>
<title>한국인 모여라, 회원가입</title>

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
							<hr>
							<form class="user" name="registerForm" method="post"
								action="${pageContext.request.contextPath}/RegisterController.do"
								onsubmit="return checkRegForm()">
								<div class="form-group row">
									<div class="col-sm-8 mb-3 mb-sm-0">
										<input type="text" class="form-control form-control-user"
											name="name" required="required" placeholder="이름"
											onkeypress="if(!(event.keyCode < 47 && event.keyCode > 58)) event.returnValue=false;">
									</div>
									<div class="col-sm-4">
										<p class="small" style="margin-top: 13px">한글만 입력 가능합니다</p>
									</div>
								</div>
								<div class="form-group row">
									<div class="col-sm-8 mb-3 mb-sm-0">
										<input type="text" class="form-control form-control-user"
											name="id" required="required" placeholder="ID"
											onKeyup="len_chk()">
									</div>
									<div class="col-sm-4">
										<button type="submit" class="btn btn-warning"
											onclick="checkId()"
											style="border-radius: 25px; padding: 12px 10px; width: 100%">ID확인</button>
										<input type="hidden" name="flagId" value="">
									</div>
								</div>
								<div class="form-group row">
									<div class="col-sm-8 mb-3 mb-sm-0">
										<input type="email" class="form-control form-control-user"
											name="email" required="required" placeholder="이메일 주소">
									</div>
									<div class="col-sm-4">
										<button type="submit" class="btn btn-warning"
											onclick="checkEmail()"
											style="border-radius: 25px; padding: 12px 10px; width: 100%">EMAIL확인</button>
										<input type="hidden" name="flagEmail" value="">
									</div>
								</div>
								<div class="form-group row">
									<div class="col-sm-12 mb-3 mb-sm-0">
										<input type="password" class="form-control form-control-user"
											name="password" required="required" placeholder="Password">
									</div>
									
								</div>
								<div class="form-group row">
									<div class="col-sm-12">
										<input type="password" class="form-control form-control-user"
											name="confirmPassword" required="required"
											placeholder="Password 확인">
									</div>
								</div>
								<hr>
								
								<p>
									<b>생일</b> (ex.1993-08-22)
								</p>
								<div class="form-group">
									<input type="date" class="form-control form-control-user"
										name="birth" required="required" placeholder="생년월일"
										value="date"  min="1900-01-01" max="<%= sf.format(nowTime) %>">						
								</div><br>
								<div class="form-group">
									<p>
										<b>성별</b>
									</p>
									<div class="form-check-inline">
										<label class="form-check-label" for="Male"> <input
											type="radio" class="form-check-input" id="Male" name="gender"
											value="M" checked>남성
										</label>
									</div>
									<div class="form-check-inline">
										<label class="form-check-label" for="Female"> <input
											type="radio" class="form-check-input" id="Female"
											name="gender" value="F">여성
										</label>
									</div>
								</div>
								<br>

								<div class="form-group">
									<p>
										<b>여행스타일</b>
									</p>
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
								</div><br>

								<div class="form-group">
									<p>
										<b>현재국가</b>
									</p>
									<div class="form-check-inline">
										<label class="form-check-label" for="radio5"> <input
											type="radio" class="form-check-input" id="radio5"
											name="countryId" value="82" checked>한국
										</label>
									</div>
									<div class="form-check-inline">
										<label class="form-check-label" for="radio6"> <input
											type="radio" class="form-check-input" id="radio6"
											name="countryId" value="44">영국
										</label>
									</div>
									<div class="form-check-inline">
										<label class="form-check-label" for="radio7"> <input
											type="radio" class="form-check-input" id="radio7"
											name="countryId" value="49">독일
										</label>
									</div>
									<div class="form-check-inline">
										<label class="form-check-label" for="radio8"> <input
											type="radio" class="form-check-input" id="radio8"
											name="countryId" value="33">프랑스
										</label>
									</div>
									<div class="form-check-inline">
										<label class="form-check-label" for="radio9"> <input
											type="radio" class="form-check-input" id="radio9"
											name="countryId" value="39">이탈리아
										</label>
									</div>
								</div>
								<hr><br>
								<div class="text-center">
									<input type="submit" class="btn btn-primary" value="함께 여행하기"
										onsubmit="return checkRegForm()">
								</div>
							</form><br>
							<div class="text-center">
								<a class="small"
									href="${pageContext.request.contextPath}/member/findMyId.jsp">아이디
									찾기</a> &nbsp;&nbsp; <a class="small"
									href="${pageContext.request.contextPath}/member/findMyPassword.jsp">비밀번호
									찾기</a>&nbsp;&nbsp; <a class="small"
									href="${pageContext.request.contextPath}/index.jsp">Home</a>
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