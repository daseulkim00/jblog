<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<ul>
		<c:choose>
			<c:when test="${empty authUser }">
      <h1 class="logo" style="background:url(${pageContext.request.contextPath}/assets/images/logo.jpg) no-repeat 0 0">JBlog</h1>
				<ul class="menu">
					<li><a href="${pageContext.request.contextPath}/user/login">로그인</a></li>
					<li><a href="${pageContext.request.contextPath}/user/join">회원가입</a></li>
					<li><a href="${pageContext.request.contextPath}">로그아웃</a></li>
					<li><a href="${pageContext.request.contextPath}/blog/blog-main">내블로그</a></li>
				</ul>
			</c:when>
			<c:otherwise>
			<h1 class="logo" style="background:url(${pageContext.request.contextPath}/assets/images/logo.jpg) no-repeat 0 0">JBlog</h1>
				<ul class="menu">
					<li><a href=#>${authUser.name }님</a></li>
					<li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li>  <!-- spring-servlet.xml -->
					<li><a
						href="${pageContext.request.contextPath}/blog/blog-main">내블로그</a></li>
				</ul>
			</c:otherwise>
		</c:choose>
	</ul>
