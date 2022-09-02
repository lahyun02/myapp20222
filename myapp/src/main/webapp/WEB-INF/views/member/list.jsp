<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap CSS -->
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" >
<link href="${pageContext.request.contextPath}/css/bootstrap-icons/bootstrap-icons.css" rel="stylesheet" >


<title>회원관리</title>
</head>
<body>
<div class="container">
<div class="row">
    <div class="col">
	<h1>회원목록</h1>
	<a href="${pageContext.request.contextPath}/member/add.do">
		<button type="button" class="btn btn-outline-warning"><i class="bi bi-person-plus"></i> 회원추가</button>
	</a>
	<table class="table table-hover">
		<thead>
			<tr class="table-light"><th>아이디</th><th>이름</th><th>포인트</th></tr>
		</thead>
		<tbody>
			<c:forEach var="vo" items="${memList}" >
				<tr>
					<td><c:out value="${vo.memId}" /></td>
					<td><c:out value="${vo.memName}" /></td>
					<td>${vo.memPoint}</td> <%--int이기 때문에 보안상의 이유인 c:out을 굳이 쓰지 않았음--%>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
</div>
</div>
<!-- Option 1: Bootstrap Bundle with Popper -->
<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js" ></script>
</body>
</html>