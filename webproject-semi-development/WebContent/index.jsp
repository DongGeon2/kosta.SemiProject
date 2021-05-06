<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">

<head>
	<!-- metatag, title, css import -->
	<c:import url="/template/headerLink.jsp"></c:import>
</head>

<body id="page-top">
	<!-- Page Wrapper -->
	<div id="wrapper">
		<!-- sideHeader -->
		<c:import url="/template/sideHeader.jsp"></c:import>
		<!-- End of sideHeader -->
		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">
			<!-- Main Content -->
			<div id="content">
				<!-- topHeader -->
				<c:import url="/template/topHeader.jsp"></c:import>
				<!-- End of topHeader -->
				<!-- Begin Page Content -->

				<div class="container-fluid">
					<!-- Page Heading -->
					<!-- content1 나라별회원수현황. url에 앞 경로명 추가해야. -->
					<%-- <c:import url="MemberCountController.do"></c:import> --%>
					
					<!-- TABLE BOARD -->
					<%-- 			
						main-list.jsp url 에 setAttribute 해서 넘겨주기		
						<c:import url="${url}"></c:import>
					--%>
					<c:import url="/board/main-list.jsp"></c:import>
					<!-- /.TABLE BOARD -->
				</div>
				<!-- /.container-fluid -->
			</div>
			<!-- End of Main Content -->

			<!-- Footer -->
			<c:import url="/template/footer.jsp"></c:import>
			<!-- End of Footer -->

		</div>
		<!-- End of Content Wrapper -->

	</div>
	<!-- End of Page Wrapper -->
	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>
	<!-- scriptLink import -->
	<!-- Bootstrap core JavaScript-->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="vendor/jquery-easing/jquery.easing.min.js"></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="js/sb-admin-2.min.js"></script>
</body>

</html>