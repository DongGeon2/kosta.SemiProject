<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
   function deleteCheck() {
      // 회원탈퇴 확인
      var flag = false;
      if (confirm("정말 탈퇴하시겠습니까?")) {
         alert("탈퇴가 완료되었습니다");
         flag = true;
      } else {
         alert("당신의 여행이 더 빛날 수 있도록! 한국인 모여라♥");
      }
      return flag;
   }
</script>
<c:import url="/template/headerLink.jsp"></c:import>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%
Date nowTime = new Date();
SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
%>

<!--  * 회원정보 수정 폼
    * 수정 가능 정보 : 비밀번호, 이름, 생년월일, 여행스타일, 나라 -->
<body class="bg-gradient-primary">

   <div class="container">

      <div class="card o-hidden border-0 shadow-lg my-5">
         <div class="card-body p-0">
            <!-- Nested Row within Card Body -->
            <div class="row">
               <div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
               <div class="col-lg-7">
                  <div class="p-5">
                     <div class="text-right">
                        <form class="delete" method="post"
                           onsubmit="return deleteCheck();"
                           action="${pageContext.request.contextPath}/DeleteMyIdController.do">
                           <input type="submit" class="btn btn-primary" value="회원탈퇴">
                        </form>
                     </div>

                     <div class="text-center">
                        <h1 class="h4 text-gray-900 mb-4">회원정보 수정</h1>

                     </div>
                     <form
                        action="${pageContext.request.contextPath}/UpdateMemberController.do"
                        method="post" class="user">
                        <div class="form-group row">
                           <div class="col-sm-6 mb-3 mb-sm-0">
                              <h3>ID : ${sessionScope.mvo.id}</h3>
                              <!-- <input type="text" class="form-control form-control-user"
                                 id="id" placeholder="ID"> -->
                           </div>
                           <div class="col-sm-6">
                              <input type="text" class="form-control form-control-user"
                                 name="name" value="${sessionScope.mvo.name}">
                           </div>
                        </div>
                        <div class="form-group row">
                           <div class="col-sm-6 mb-3 mb-sm-0">
                              <input type="password" class="form-control form-control-user"
                                 name="password" placeholder="Password" required="required">
                           </div>
                           <div class="col-sm-6">
                              <input type="password" class="form-control form-control-user"
                                 name="confirmPassword" placeholder="Password 확인"
                                 required="required">
                           </div>
                        </div>
                        <div class="form-group">
                           <h3>Email : ${sessionScope.mvo.email}</h3>
                           <!-- <input type="email" class="form-control form-control-user"
                              id="email" placeholder="이메일 주소"> -->
                        </div>
                        <hr>
                        <div class="form-group">
                           <p>
                              <b>생일</b>
                           </p>
                        </div>
                        <div class="form-group">
                           <input type="date" class="form-control form-control-user"
                              name="birth" required="required" placeholder="생년월일"
                              value="date"  min="1900-01-01" max="<%= sf.format(nowTime) %>">                  
                        </div><br>
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
                              <input type="submit" class="btn btn-primary" value="수정">
                           </div>
                        </form>

                        <%-- <div class="form-group row">
                                    <div class="col-sm-6 mb-3 mb-sm-0">
                                        <input type="text" class="form-control form-control-user" name="name"
                                            value="${sessionScope.mvo.name}">
                                    </div>
                                    <div class="col-sm-6">
                                        <input type="password" class="form-control form-control-user"
                                            name="password" placeholder="Password">
                                    </div>
                                </div>
                               <div class="form-group">
                                    <input type="date" class="form-control form-control-user" name="birth"
                                        value="${sessionScope.mvo.birth}">
                                </div>
                                <div class="form-group row">
                                    <div class="col-sm-6 mb-3 mb-sm-0">
                                        <input type="text" class="form-control form-control-user" name="travelStyle"
                                            value="${sessionScope.mvo.travelStyle}">
                                    </div>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control form-control-user" name="countryID"
                                            value="${sessionScope.mvo.countryVO.countryName}">
                                    </div>
                                </div> -->
                                <div class="text-center">
                                   <input type="submit" class="btn btn-primary" value="수정">
                                </div> --%>
                     
                  </div>
               </div>
            </div>
         </div>
      </div>
   </div>
</body>