<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap CSS -->
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" >
<link href="${pageContext.request.contextPath}/css/bootstrap-icons/bootstrap-icons.css" rel="stylesheet" >
<script src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script> 
<!-- 타일즈의 title 속성 값을 문자열로서 이 위치에 주입  -->
<title><tiles:getAsString name="title"/></title>
</head>
<body>
<div class="container">
	<!-- 타일즈의 header 속성 값을 이 위치에 주입  -->
	<tiles:insertAttribute name="header" />
	<!-- 타일즈의 body 속성 값을 이 위치에 주입  -->
	<tiles:insertAttribute name="body" />
	<!-- 타일즈의 footer 속성 값을 이 위치에 주입  -->
	<tiles:insertAttribute name="footer" />
</div>
<!-- Option 1: Bootstrap Bundle with Popper -->
<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js" ></script>
</body>
</html>