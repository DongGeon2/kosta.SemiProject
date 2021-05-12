<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<div class="d-sm-flex align-items-center justify-content-between mb-4">
		<!-- ${totalCount} 넣어주세요 -->
		<a class="collapse-item" href="${pageContext.request.contextPath}/MemberListController.do"><i class="fas fa-chevron-left"></i></a>
		
	</div>
	<h4 class="h4 mb-0 text-gray-800" style="text-align: center; margin-bottom:50px !important;">
		<b style="background-color:#4e73df; color:white; padding:5px 10px">${requestScope.detailMVO.name}</b>님의 회원 정보</h4>
	<!-- Content Row -->
	<div class="row">
	<!-- Earnings (Monthly) Card Example -->
	<div class="col-xl-3 col-md-6 mb-4">
		<div class="card border-left-primary shadow h-100 py-2">
			<div class="card-body">
				<div class="row no-gutters align-items-center">
					<div class="col mr-2">
						<div
							class="text-lg font-weight-bold text-primary text-uppercase mb-1">
							현재 여행 중인 국가</div>
							<div class="h5 mb-0 font-weight-bold text-gray-800">${requestScope.detailMVO.countryVO.countryName}</div>
							
					</div>
					<div class="col-auto">
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Earnings (Monthly) Card Example -->
	<div class="col-xl-3 col-md-6 mb-4">
		<div class="card border-left-success shadow h-100 py-2">
			<div class="card-body">
				<div class="row no-gutters align-items-center">
					<div class="col mr-2">
						<div
							class="text-lg font-weight-bold text-success text-uppercase mb-1">
							여행 취향</div>
						<div class="h5 mb-0 font-weight-bold text-gray-800">${requestScope.detailMVO.travelStyle}</div>
					</div>
					<div class="col-auto">
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Earnings (Monthly) Card Example -->
	<div class="col-xl-3 col-md-6 mb-4">
		<div class="card border-left-info shadow h-100 py-2">
			<div class="card-body">
				<div class="row no-gutters align-items-center">
					<div class="col mr-2">
						<div
							class="text-lg font-weight-bold text-info text-uppercase mb-1">생일</div>
						<div class="row no-gutters align-items-center">
							<div class="col-auto">
								<div class="h5 mb-0 mr-3 font-weight-bold text-gray-800">${requestScope.detailMVO.birth}</div>
							</div>
						</div>
					</div>
					<div class="col-auto">
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Pending Requests Card Example -->
	<div class="col-xl-3 col-md-6 mb-4">
		<div class="card border-left-warning shadow h-100 py-2">
			<div class="card-body">
				<div class="row no-gutters align-items-center">
					<div class="col mr-2">
						<div
							class="text-lg font-weight-bold text-warning text-uppercase mb-1">
							포인트</div>
						<div class="h5 mb-0 font-weight-bold text-gray-800">${requestScope.detailMVO.point}</div>
					</div>
					<div class="col-auto">
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
