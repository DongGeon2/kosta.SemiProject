<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<div class="d-sm-flex align-items-center justify-content-between mb-4">
		<!-- ${totalCount} 넣어주세요 -->
		<h4 class="h4 mb-0 text-gray-800" style="text-align: center">
			지금 당신과 즐거운 시간을 보낼수도 있는 한국인 <b>${requestScope.count }</b> 명이 ${requestScope.countryName}에서 여행을 하고있어요 !!
			
		</h4>
	</div>
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
							시차</div>
					
							
						<div class="h5 mb-0 font-weight-bold text-gray-800">${country.countryTime}</div>
					</div>
					<div class="col-auto">
						<i class="fas fa-calendar fa-2x text-gray-300"></i>
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
							언어</div>
						<div class="h5 mb-0 font-weight-bold text-gray-800">${country.language}</div>
					</div>
					<div class="col-auto">
						<i class="fas fa-clipboard-list fa-2x text-gray-300"></i>
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
							class="text-lg font-weight-bold text-info text-uppercase mb-1">화폐</div>
						<div class="row no-gutters align-items-center">
							<div class="col-auto">
								<div class="h5 mb-0 mr-3 font-weight-bold text-gray-800">${country.currency}</div>
							</div>
						</div>
					</div>
					<div class="col-auto">
						<i class="fas fa-dollar-sign fa-2x text-gray-300"></i>
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
						<div class="h5 mb-0 font-weight-bold text-gray-800"style="text-align:center;"><img
				 src="images/flag/${country.countryId}.svg"
				alt="국기 이미지" style="width:120px;">
				 </div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>