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
	<h1>회원등록</h1>
	<form action="${pageContext.request.contextPath}/member/add.do" method="post" > 
	  <div class="mb-3">
	    <label for="memId" class="form-label">아이디</label>
	    <input type="text" name="memId" class="form-control" id="memId">
	  </div>
	  <div class="mb-3">
	    <label for="memPass" class="form-label">비밀번호</label>
	    <input type="password" name="memPass" class="form-control" id="memPass">
	  </div>
	  <div class="mb-3">
	    <label for="memName" class="form-label">이름</label>
	    <input type="text" name="memName" class="form-control" id="memName">
	  </div>
	  <div class="mb-3">
	    <label for="memPoint" class="form-label">포인트</label>
	    <input type="text" name="memPoint" class="form-control" id="memPoint">
	  </div>
	  <button type="submit" class="btn btn-primary"><i class="bi bi-save"></i> 저장</button>
	 	<a href="${pageContext.request.contextPath}/member/list.do">
			<button type="button" class="btn btn-outline-warning"><i class="bi bi-filter-square"></i> 회원목록</button>
		</a>
	</form>
	
	
	</div>
</div>
</div>
<!-- Option 1: Bootstrap Bundle with Popper -->
<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js" ></script>
</body>
</html>