<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<html>
<head>
	<title>test</title>
</head>
<body>
	<h1>테스트화면</h1>
	<h2>x + y = ${result}</h2>
	<ul>
		<c:forEach var="f" items="${testVo.food}">
			<li>${f}</li>
		</c:forEach>
	</ul>
	
</body>
</html>
