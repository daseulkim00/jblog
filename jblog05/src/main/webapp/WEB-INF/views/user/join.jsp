<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!-- valid추가 -->
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script>
	$(function() {
		$("#btn-check-id")
				.click(
						function() {
							// 클릭되었을때 되는거 확인
							var id = $("#blog-id").val(); // var 는 {}안에서 사용 가능 

							if (id == '') {
								return;
							}

							console.log(id);
							$.ajax({
										url : "${pageContext.request.contextPath }/user/api/checkid?id="
								      + id,
										type : "get",
										dataType : "json",
										error : function(xhr, status, e) { //통신에러체크 
											console.log(status, e);
										},
										success : function(response) {
											console.log(response);
											if (response.result != "success") {
												console.error(response.message);
												return;
											}

											if (response.data) {
												alert("존재하는 아이디입니다. 다른 아이디를 사용하세요.")
												$("#blog-id").val("").focus();
												return;
											}

											$("#btn-check-id").hide();
											$("#img-check-id").show();

										}
									});
						})
	});
</script>
</head>
<body>
	<div class="center-content">

		<c:import url="/WEB-INF/views/includes/header.jsp" />

		<form class="join-form" id="join-form" method="post"
			action="${pageContext.request.contextPath}/user/join">
			<label class="block-label" for="name">이름</label> <input id="name"
				name="name" type="text" value="">
			<p style="text-align: left; padding-left: 0; color: #f00;">

				<!-- valid 에러 추가 -->
				<spring:hasBindErrors name="userVO">
					<c:if test="${errors.hasFieldErrors('name') }">
						<!-- errors 라는 객체가 넘어와서 사용된다 -->
						<spring:message code="${errors.getFieldError('name').codes[0] }" />
					</c:if>
				</spring:hasBindErrors>
			</p>

			<label class="block-label" for="blog-id">아이디</label> 
			<input id="blog-id" name="id" type="text"> 
			<input id="btn-check-id" type="button" value="id 중복체크"> 
			<img id="img-check-id" style="display: none;" src="${pageContext.request.contextPath}/assets/images/check.png">
			
			
			<p style="text-align: left; padding-left: 0; color: #f00;">
				<!-- valid 에러 추가 -->
				<spring:hasBindErrors name="userVO">
					<c:if test="${errors.hasFieldErrors('id') }">
						<!-- errors 라는 객체가 넘어와서 사용된다 -->
						<spring:message code="${errors.getFieldError('id').codes[0] }" />
					</c:if>
				</spring:hasBindErrors>
			</p>

			<label class="block-label" for="password">패스워드</label> <input
				id="password" name="password" type="password" />
			<p style="text-align: left; padding-left: 0; color: #f00;">
				<!-- valid 에러 추가 -->
				<spring:hasBindErrors name="userVO">
					<c:if test="${errors.hasFieldErrors('password') }">
						<!-- errors 라는 객체가 넘어와서 사용된다 -->
						<spring:message
							code="${errors.getFieldError('password').codes[0] }" />
					</c:if>
				</spring:hasBindErrors>
			</p>

			<fieldset>
				<legend>약관동의</legend>
				<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
				<label class="l-float">서비스 약관에 동의합니다.</label>
			</fieldset>

			<input type="submit" value="가입하기">

		</form>
	</div>
</body>
</html>
