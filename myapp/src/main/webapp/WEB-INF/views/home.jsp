<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>

<%-- x,y값을 입력하고 전송버튼을 클릭하면, test.do로 요청이 전송되어 화면에 x+y=두수의 합 출력되도록 --%>

<%-- 전송버튼을 클릭하면 이클립스 콘솔에 선택한 음식들이 출력. (pizza, sand, spa)
웹 브라우저 화면에 선택한 음식들이 리스트(<li>) 엘리먼트들로 출력 --%> 

<%-- <form action="<c:url value="/test.do" />"> 이경우 c:url이 자동으로 contextPath를 경로 앞에 붙여줌 --%>
<form action="${pageContext.request.contextPath}/test.do" method="post">
	x : <input type="text" name="x" /> <br />
	y : <input type="text" name="y" /> <br /> 
	<p>음식주문</p>
	<input type="checkbox" name="food" value="pizza" /> 피자
	<input type="checkbox" name="food" value="sand" /> 샌드위치
	<input type="checkbox" name="food" value="spa" /> 스파게티 
	<br />
	<input type="submit" value="전송" />
</form>
	
</body>
</html>
