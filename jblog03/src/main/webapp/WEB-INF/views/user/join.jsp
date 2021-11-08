<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script >
$(function () {    
	 $("#btn-check-id").click(function () {
		// 클릭되었을때 되는거 확인
		var id = $("#blog-id").val(); // var 는 {}안에서 사용 가능 
		
		if(id == ''){
			return;
		}	
		
		console.log(id);
		$.ajax({
			url: "${pageContext.request.contextPath }/user/api/checkid?id=" + id,
			type: "get",
			dataType:"json",
			error: function(xhr, status, e){ //통신에러체크 
				console.log(status, e);
			},      
			success: function(response) {
				console.log(response);
				if(response.result != "success") {
					console.error(response.message);
					return;
				}
				
				
				if(response.data){
					alert("존재하는 아이디입니다. 다른 아이디를 사용하세요.")
					$("#id").val("").focus();
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
		
		<form class="join-form" id="join-form" method="post" action="${pageContext.request.contextPath}/user/join">
			<label class="block-label" for="name">이름</label>
			<input id="name" name="name" type="text" value="" required>
			
			<label class="block-label" for="blog-id">아이디</label>
			<input id="blog-id" name="id" type="text" required> 
			<input id="btn-check-id" type="button" value="id 중복체크">
			<img id="img-check-id" style="display: none;" src="${pageContext.request.contextPath}/assets/images/check.png">

			<label class="block-label" for="password">패스워드</label>
			<input id="password" name="password" type="password" required/>

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
