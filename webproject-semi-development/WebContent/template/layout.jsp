<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
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
					<!-- Content Row -->
						<c:import url="${urlCountry}"></c:import>
					<!-- MAIN 전체 count 받아오기 -->
						<%-- <c:import url="/template/memberCount.jsp"></c:import> --%>
					<!-- Country 별 Info 받아오기 -->
						<%-- <c:import url="/template/countryInfo.jsp"></c:import> --%>
					<!-- TABLE BOARD -->
					<%-- 			
						url 에 setAttribute 해서 넘겨주기
						ex ) 
						main-list.jsp (전체 나라 게시판 리스트)
						board-list.jsp (나라별 게시판 리스트)
						post-detail.jsp (게시물 상세보기)
						post-update.jsp (게시물 수정하기)
						post-write.jsp (게시물 등록하기)
						<c:import url="${url}"></c:import>
					--%>
					<c:import url="${url}"></c:import>
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
	<c:import url="/template/scriptLink.jsp"></c:import>
</body>
</html>