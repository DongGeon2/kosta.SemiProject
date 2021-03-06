<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>  
<ul
			class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion"
			id="accordionSidebar">
			<!-- Sidebar - Brand -->
			<a class="sidebar-brand d-flex align-items-center justify-content-center"
				href="${pageContext.request.contextPath}/index.jsp">
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
						<a class="collapse-item" href="${pageContext.request.contextPath}/IndividualListBycountryController.do?countryId=33">프랑스</a> 
						<a class="collapse-item" href="${pageContext.request.contextPath}/IndividualListBycountryController.do?countryId=39">이탈리아</a> 
						<a class="collapse-item" href="${pageContext.request.contextPath}/IndividualListBycountryController.do?countryId=44">영국</a> 
						<a class="collapse-item" href="${pageContext.request.contextPath}/IndividualListBycountryController.do?countryId=49">독일</a>
						<%-- <c:if test="${sessionScope.mvo.point>=10}"> --%>
						<c:if test="${sessionScope.mvo!=null}"> 
						<a class="collapse-item" href="PointCheckController.do" style="background-color:gold; font-weight:bold; color:dimgray"><i class="fas fa-crown" style="color:white;"></i>&nbsp;&nbsp;&nbsp;&nbsp;PREMIUM</a>
						</c:if>
					</div>
				</div></li>

			<!-- Divider -->
			<hr class="sidebar-divider">

			<c:choose>
			<c:when test="${sessionScope.mvo!=null}">
			<!-- 회원 로그인 -->
			<!-- Nav Item - Pages Collapse Menu -->
			<!-- Heading -->
			<div class="sidebar-heading">Member</div>
			<!-- Nav Item  -->
			<li class="nav-item"><a class="nav-link collapsed" href="#"
				data-toggle="collapse" data-target="#collapsePages"
				aria-expanded="true" aria-controls="collapsePages"> <i
					class="fas fa-fw fa-user"></i> <span>내정보 관리</span>
			</a>
				<div id="collapsePages" class="collapse"
					aria-labelledby="headingPages" data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
						<h6 class="collapse-header">My Info</h6>
						<a class="collapse-item" href="MyDetailInfoController.do">내 정보 조회</a>
						<a class="collapse-item" href="UpdateMemberPasswordController.do">내 정보 수정</a> <a
							class="collapse-item" href="${pageContext.request.contextPath}/MyPostListController.do?id=${sessionScope.mvo.id}">내가 쓴 글 보기</a>
					

						<!-- <a class="collapse-item" href="#">내가 쓴 댓글 보기</a>  -->
					</div>
				</div></li>
			<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/LogoutController.do"> <i
					class="fas fa-fw fa-sign-out-alt"></i> <span>로그아웃</span></a></li>
			</c:when>
			<c:when test="${sessionScope.mgvo!=null}">
			<!-- manager 로그인 -->
			<!-- Heading -->
			<div class="sidebar-heading">Manager</div>
			<li class="nav-item"><a class="nav-link collapsed" href="#"
				data-toggle="collapse" data-target="#collapsePages"
				aria-expanded="true" aria-controls="collapsePages"> <i
					class="fas fa-fw fa-user"></i> <span>사이트 관리</span>
			</a>
				<div id="collapsePages" class="collapse"
					aria-labelledby="headingPages" data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
						<h6 class="collapse-header">회원 관리</h6>
						<a class="collapse-item" href="${pageContext.request.contextPath}/MemberListController.do">회원 조회</a>
					

						<!-- <a class="collapse-item" href="#">내가 쓴 댓글 보기</a>  -->
					</div>
				</div></li>
			<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/LogoutController.do"> <i
					class="fas fa-fw fa-sign-out-alt"></i> <span>로그아웃</span></a></li>
			</c:when>
			<c:otherwise>
			<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/member/register-ok.jsp"> <i
					class="fas fa-fw fa-user-plus"></i> <span>회원가입</span></a></li>
			<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/member/login.jsp"> <i
					class="fas fa-fw fa-sign-in-alt"></i> <span>로그인</span></a></li>
			
			</c:otherwise>
			</c:choose>
			<!-- Divider -->
			<hr class="sidebar-divider d-none d-md-block">

			<!-- Sidebar Toggler (Sidebar) -->
			<div class="text-center d-none d-md-inline">
				<button class="rounded-circle border-0" id="sidebarToggle"></button>
			</div>

		</ul>