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
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script>
	var render = function(vo, index, size) {
		var html = "<tr data-no='" + vo.no +"'>" 
				+ "<td>" + vo.name + "</td>" 
				+ "<td>" + vo.postcount + "</td>" + "<td>" + vo.desc
				+ "</td>";

		if (size == 1) {
			html += "<td>"
					+ "<img src='${pageContext.request.contextPath}/assets/images/delete.jpg'></td>";
		} else {
			html += "<td>"
					+ "<a href='${pageContext.request.contextPath }/blog/${blog.id}/delete/"+vo.no+ "'>"
					+ "<img class='delete' data-no='" + vo.no + "' src='${pageContext.request.contextPath}/assets/images/delete.jpg'></td>"
		}

		html += "</tr>"
		return html;
	}

	var fetchCategory = function() {
		$
				.ajax({
					url : '${pageContext.request.contextPath}/blog/${blog.id}/catelist',
					type : 'get',
					dataType : 'json',
					data : '',
					success : function(response) {

						if (response.result != "success") {
							console.error(response.message);
							return;
						}

						var categorylist = "";
						for (var i = 0; i < response.data.length; i++) {
							var html = render(response.data[i], i,
									response.data.length)
							categorylist += html
							console.log(html)
						}

						$(".admin-cat").append(categorylist)

					}
				})
	}
	
	

	$(function() {
		$('#add-form')
				.submit(
						function(event) {
							event.preventDefault();

							var vo = {};
							vo.name = $("#input-name").val();
							vo.desc = $("#desc").val();

							$.ajax({
										url : '${pageContext.request.contextPath}/blog/${blog.id}/addCategory',
										type : 'post',
										dataType : 'json',
										contentType : 'application/json',
										data : JSON.stringify(vo),
										success : function(response) {
											if (response.result != "success") {
												console.error(response.message)
												return;
											}
											var html = render(response.data)
											$(".admin-cat").append(html)
										}

									})

						})
						
						
						$(".admin-cat").on("click",".delete",function(event) {
							event.preventDefault()
							
							var deleteno = $(event.target).data("no");
							/* console.log($(event.target).data("no")); */
							t = event.target;
								
								var list = document.getElementsByTagName("tbody")[0]
								
								if(list.childElementCount == 2){
									return;
								}
							
							$.ajax({
								url:'${pageContext.request.contextPath}/blog/${blog.id}/deletecate/'+ deleteno,
								type:'delete',
								dataType: 'json',
								data: 'deleteno=' + deleteno  ,
								success: function(response){
									if(response.result != "success"){
										console.error(response.message)
										return;
									}
									
								}
									
							})
									$('tr[data-no=' +$(t).data("no") + ']').remove()  
							
							

						})
						fetchCategory();
					})
</script>
</head>
<body>
	<div id="container">

		<c:import url="/WEB-INF/views/includes/blogheader.jsp" />

		<div id="wrapper">
			<div id="content" class="full-screen">
				<ul class="admin-menu">
					<li class="selected"><a
						href="${pageContext.request.contextPath }/blog/${blog.id}/blog-admin-basic">기본설정</a></li>
					<li><a
						href="${pageContext.request.contextPath }/blog/${blog.id}/blog-admin-category">카테고리</a></li>
					<li><a
						href="${pageContext.request.contextPath }/blog/${blog.id}/blog-admin-write">글작성</a></li>
				</ul>
				<table class="admin-cat">
					<tr>
						<th>카테고리명</th>
						<th>포스트 수</th>
						<th>설명</th>
						<th>삭제</th>
					</tr>

				</table>

				<h4 class="n-c">새로운 카테고리 추가</h4>
				<form id="add-form"
					action="${pageContext.request.contextPath }/blog/${blog.id}/blog-admin-category/add"
					method="post">
					<table id="admin-cat-add">
						<tr>
							<td class="t">카테고리명</td>
							<td><input id="input-name" type="text" name="name"></td>
						</tr>
						<tr>
							<td class="t">설명</td>
							<td><input id="desc" type="text" name="desc"></td>
						</tr>
						<tr>
							<td class="s">&nbsp;</td>
							<td><input type="submit" value="카테고리 추가"></td>
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