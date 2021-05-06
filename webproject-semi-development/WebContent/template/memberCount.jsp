<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Page Heading --><!-- Page Heading -->
<div class="d-sm-flex align-items-center justify-content-between mb-4">
	<h4 class="h4 mb-0 text-gray-800" style="text-align: center">
		지금 당신과 즐거운 시간을 보낼수도 있는 한국인 <b>${requestScope.countryMap.get("totalCount")}</b> 명이 여행을 하고있어요 !!
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
							프랑스(France)</div>
						<div class="h5 mb-0 font-weight-bold text-gray-800">
						${requestScope.countryMap.get("프랑스")}
							명</div>
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
							이탈리아(Italy)</div>
						<div class="h5 mb-0 font-weight-bold text-gray-800">
						${requestScope.countryMap.get("이탈리아")}
							명</div>
					</div>
					<div class="col-auto">
						<i class="fas fa-dollar-sign fa-2x text-gray-300"></i>
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
							class="text-lg font-weight-bold text-info text-uppercase mb-1">영국(United
							Kingdom)</div>
						<div class="row no-gutters align-items-center">
							<div class="col-auto">
								<div class="h5 mb-0 mr-3 font-weight-bold text-gray-800">
								${requestScope.countryMap.get("영국")}
									명</div>
							</div>
						</div>
					</div>
					<div class="col-auto">
						<i class="fas fa-clipboard-list fa-2x text-gray-300"></i>
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
							독일(Germany)</div>
						<div class="h5 mb-0 font-weight-bold text-gray-800">
						${requestScope.countryMap.get("독일")}
							명</div>
					</div>
					<div class="col-auto">
						<i class="fas fa-comments fa-2x text-gray-300"></i>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>