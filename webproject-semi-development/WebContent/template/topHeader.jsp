<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<nav
	class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

	<!-- Sidebar Toggle (Topbar) -->
	<button id="sidebarToggleTop"
		class="btn btn-link d-md-none rounded-circle mr-3">
		<i class="fa fa-bars"></i>
	</button>

	<!-- Topbar Search -->
	<form action="SearchController.do" name="searchForm"
		class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search searchForm">
		<div class="input-group">
			<select class="form-control" name="country_id" required="required">
				<option value="all" selected="selected">-모든국가-</option>
				<option value="33" >프랑스</option>
				<option value="39">이탈리아</option>
				<option value="44">영국</option>
				<option value="49">독일</option>
			</select>	
			
			<select  class="form-control" name="column" required="required" >
				<option value="(p.content||p.post_title)" selected="selected">제목+내용</option>
				<option value="p.content">내용</option>
				<option value="p.post_title">제목</option>
				<option value="p.member_id">작성자id</option>
			</select>
			 
			<input type="text" name="keyWord" class="form-control bg-light border-0 small"
				placeholder="Search for..." aria-label="Search"
				aria-describedby="basic-addon2">
			<div class="input-group-append">
				<button class="btn btn-primary" type="submit" >
					<i class="fas fa-search fa-sm"></i>
				</button>
			</div>
		</div>
	</form>

	<!-- Topbar Navbar -->
	<ul class="navbar-nav ml-auto">

		<!-- Nav Item - Search Dropdown (Visible Only XS) -->
		<li class="nav-item dropdown no-arrow d-sm-none"><a
			class="nav-link dropdown-toggle" href="#" id="searchDropdown"
			role="button" data-toggle="dropdown" aria-haspopup="true"
			aria-expanded="false"> <i class="fas fa-search fa-fw"></i>
		</a> <!-- Dropdown - Messages -->
			<div
				class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in"
				aria-labelledby="searchDropdown">
				<form class="form-inline mr-auto w-100 navbar-search">
					<div class="input-group">
						<input type="text" class="form-control bg-light border-0 small"
							placeholder="Search for..." aria-label="Search"
							aria-describedby="basic-addon2">
						<div class="input-group-append">
							<button class="btn btn-primary" type="button">
								<i class="fas fa-search fa-sm"></i>
							</button>
						</div>
					</div>
				</form>
			</div></li>

		<div class="topbar-divider d-none d-sm-block"></div>

		<!-- Nav Item - User Information -->
		<c:choose>
		<c:when test="${sessionScope.mvo!=null}">		
		<li class="nav-item dropdown no-arrow">
		<%-- check 사항 --%>
	  	<a  href="#" class="nav-link dropdown-toggle" id="userDropdown"
			role="button" data-toggle="dropdown" aria-haspopup="true"
			aria-expanded="false"> 
			
			<span class="mr-2 d-none d-lg-inline text-gray-600 small">
				${sessionScope.mvo.name}님 </span> <img
				class="img-profile rounded-circle" src="images/circle_flag/${sessionScope.mvo.countryVO.countryId}.svg"
				alt="사용자 프로필 이미지">
		</a>  
		 	<div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
				aria-labelledby="userDropdown">
				<a class="dropdown-item" href="${pageContext.request.contextPath}/ChangeCountryController.do?cid=82">대한민국
				</a>
				<div class="dropdown-divider"></div>
				<a class="dropdown-item" href="${pageContext.request.contextPath}/ChangeCountryController.do?cid=33">프랑스
				</a>
				<div class="dropdown-divider"></div>
				<a class="dropdown-item" href="${pageContext.request.contextPath}/ChangeCountryController.do?cid=39">이탈리아
				</a>
				<div class="dropdown-divider"></div>
				<a class="dropdown-item" href="${pageContext.request.contextPath}/ChangeCountryController.do?cid=44">영국
				</a>
				<div class="dropdown-divider"></div>
				<a class="dropdown-item" href="${pageContext.request.contextPath}/ChangeCountryController.do?cid=49">독일
				</a>
				</div> 
			</li>
		</c:when>
		<c:when test="${sessionScope.mgvo!=null}">
			<li class="nav-item dropdown no-arrow"><a
			class="nav-link dropdown-toggle" href="#" id="managerDropdown"
			role="button" data-toggle="dropdown" aria-haspopup="true"
			aria-expanded="false"> <span
				class="mr-2 d-none d-lg-inline text-gray-600 small">
				${sessionScope.mgvo.name}님</span> <img
				class="img-profile rounded-circle" src="images/circle_flag/manager.svg"
				alt="사용자 프로필 이미지">
		</a> <!-- Dropdown - User Information -->
			</li>
		</c:when>
		<c:otherwise>
		<span class="mr-2 d-none d-lg-inline text-gray-600 small">로그인이 필요합니다</span>
		
		</c:otherwise>
		</c:choose>	

	</ul>

</nav>