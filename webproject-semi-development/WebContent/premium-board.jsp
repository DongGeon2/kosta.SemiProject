<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
alert("프리미엄 게시판에 입장하셨습니다.\n현재 포인트: ${point}");
</script>

	<div class="d-sm-flex align-items-center justify-content-between mb-4">
		<!-- ${totalCount} 넣어주세요 -->
		<h4 class="h4 mb-0 text-gray-800" style="text-align: center">
			지금 당신과 즐거운 시간을 보낼수도 있는 한국인 <b>${requestScope.count}</b> 명이 ${country.countryName}에서 여행을 하고있어요 !!
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
							동행</div>
						<div class="h5 mb-0 font-weight-bold text-gray-800">또래를 원해요<br>로맨틱한 만남을 기대해요</div>
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
							실시간대기현황</div>
						<div class="h5 mb-0 font-weight-bold text-gray-800"> 지금 가면 살수 있나요?<br>지금 가면 바로 입장되나요?<br>데이시트, 맛집 대기줄..</div>
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
							class="text-lg font-weight-bold text-info text-uppercase mb-1">추천 코스</div>
						<div class="row no-gutters align-items-center">
							<div class="col-auto">
								<div class="h5 mb-0 mr-3 font-weight-bold text-gray-800">나에게 맞는 보장된 코스를 원해요</div>
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
						<div
							class="text-lg font-weight-bold text-warning text-uppercase mb-1">
							파티</div>
						<div class="h5 mb-0 font-weight-bold text-gray-800">Korean Assemble팀은 성숙한 여행문화를 응원합니다</div>
					</div>
					<div class="col-auto">
						<i class="fas fa-comments fa-2x text-gray-300"></i>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>