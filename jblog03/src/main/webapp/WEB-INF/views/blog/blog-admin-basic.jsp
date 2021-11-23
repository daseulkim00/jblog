<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	
	<div id="container">
	
	<c:import url="/WEB-INF/views/includes/blogheader.jsp" />
	
		<div id="wrapper">
			<div id="content" class="full-screen">
				<ul class="admin-menu">
					<li class="selected"><a href="${pageContext.request.contextPath }/blog/${blog.id}/blog-admin-basic">기본설정</a></li>
					<li><a href="${pageContext.request.contextPath }/blog/${blog.id}/blog-admin-category">카테고리</a></li>
					<li><a href="${pageContext.request.contextPath }/blog/${blog.id}/blog-admin-write">글작성</a></li>
				</ul>
				
				
				<form action="${pageContext.request.contextPath }/blog/${blog.id}/blog-admin-basic" method="post" enctype="multipart/form-data">
					<table class="admin-config">
				
						<tr>
							<td class="t">블로그 제목</td>
							<td><input type="text" size="40" name="title" value="${blog.title }"></td>
							<!-- 아이디 가져옴 -->
							<td><input type="hidden" name="id" value="${blog.id }"></td>
						</tr>
						<tr>
							<td class="t">로고이미지</td>
							<td><img src="${pageContext.request.contextPath}${blog.logo}">
							<!-- 파일이름 default값 -->
							<input type="hidden" name="logo" value="${blog.logo }">
							</td>
						</tr>
						<tr>
							<td class="t">&nbsp;</td>
							<td><input type="file" name="file"></td>
						</tr>
						<tr>
							<td class="t">&nbsp;</td>
							<td class="s"><input type="submit" value="기본설정 변경"></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div id="footer">
			<p>
				<strong>Spring 이야기</strong> is powered by JBlog (c)2016
			</p>
		</div>
	</div>
</body>
</html>