<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>  
<ul
			class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion"
			id="accordionSidebar">
			<!-- Sidebar - Brand -->
			<a class="sidebar-brand d-flex align-items-center justify-content-center"
				href="${pageContext.request.contextPath}/index.jsphttps://github.com/StartBootstrap/startbootstrap-sb-admin-2/blob/master/register.html">
				<div class="sidebar-brand-icon rotate-n-15">
					<i class="fas fa-map-marked"></i>
				</div>
				<div class="sidebar-brand-text mx-3">Korean Assemble!</div>
			</a>

			<!-- Divider -->
			<hr class="sidebar-divider my-0">

			<!-- Nav Item - Dashboard -->
			<li class="nav-item active"><a class="nav-link"
				href="${pageContext.request.contextPath}/index.jsp"> <i class="fas fa-fw fa-home"></i> <span>Home</span></a>
			</li>

			<!-- Divider -->
			<hr class="sidebar-divider">

			<!-- Heading -->
			<div class="sidebar-heading">Community</div>

			<!-- Nav Item - Pages Collapse Menu -->
			<li class="nav-item"><a class="nav-link collapsed" href="#"
				data-toggle="collapse" data-target="#collapseTwo"
				aria-expanded="true" aria-controls="collapseTwo"> <i
					class="fas fa-fw fa-folder-plus"></i> <span>나라별 게시판</span>
			</a>
				<div id="collapseTwo" class="collapse" aria-labelledby="headingTwo"
					data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
						<h6 class="collapse-header">Country</h6>
						<a class="collapse-item" href="${pageContext.request.contextPath}/IndividualListBycountryController.do?countryId=33&name=프랑스">프랑스</a> 
						<a class="collapse-item"href="${pageContext.request.contextPath}/IndividualListBycountryController.do?countryId=39&name=이탈리아">이탈리아</a> 
						<a class="collapse-item" href="${pageContext.request.contextPath}/IndividualListBycountryController.do?countryId=44&name=영국">영국</a> 
						<a class="collapse-item" href="${pageContext.request.contextPath}/IndividualListBycountryController.do?countryId=49&name=독일">독일</a>
					</div>
				</div></li>

			<!-- Divider -->
			<hr class="sidebar-divider">

			<!-- Heading -->
			<div class="sidebar-heading">Member</div>
			<!-- Nav Item  -->
			<!-- 
				로그인 X -> 회원가입, 로그인
				로그인 O -> 로그아웃, 내정보 관리
			 -->
			<c:choose>
			<c:when test="${sessionScope.mvo==null}">
			<li class="nav-item"><a class="nav-link" href="#"> <i
					class="fas fa-fw fa-user-plus"></i> <span>회원가입</span></a></li>
			<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/login/login.jsp"> <i
					class="fas fa-fw fa-sign-in-alt"></i> <span>로그인</span></a></li>
			</c:when>
			<c:otherwise>
			
			
			<!-- Nav Item - Pages Collapse Menu -->
			
			<li class="nav-item"><a class="nav-link collapsed" href="#"
				data-toggle="collapse" data-target="#collapsePages"
				aria-expanded="true" aria-controls="collapsePages"> <i
					class="fas fa-fw fa-user"></i> <span>내정보 관리</span>
			</a>
				<div id="collapsePages" class="collapse"
					aria-labelledby="headingPages" data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
						<h6 class="collapse-header">My Info</h6>
<<<<<<< HEAD
						<a class="collapse-item" href="UpdateMemberFormController.do">내정보 수정</a> <a
							class="collapse-item" href="MyPostListController.do">내가 쓴 글 보기</a>
=======
						<a class="collapse-item" href="#">내정보 수정</a> <a
							class="collapse-item" href="MyPostListController.do?id=${sessionScope.mvo.id}">내가 쓴 글 보기</a>
>>>>>>> branch 'main' of https://github.com/DongGeon2/kosta.SemiProject.git
						<!-- <a class="collapse-item" href="#">내가 쓴 댓글 보기</a>  -->
					</div>
				</div></li>
			<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/LogoutController.do"> <i
					class="fas fa-fw fa-sign-out-alt"></i> <span>로그아웃</span></a></li>
			</c:otherwise>
			</c:choose>
			<!-- Divider -->
			<hr class="sidebar-divider d-none d-md-block">

			<!-- Sidebar Toggler (Sidebar) -->
			<div class="text-center d-none d-md-inline">
				<button class="rounded-circle border-0" id="sidebarToggle"></button>
			</div>

		</ul>