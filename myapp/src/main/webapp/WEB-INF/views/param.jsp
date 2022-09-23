<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 스프링 폼 태그 사용을 위한 태그 라이브러리 --%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스프링 폼 태그 연습</title>
</head>
<body>
<h2>스프링 폼 태그 사용</h2>
<%--<c:if test="${item.id == testVo.lunch}">checked</c:if> --%>
<%-- action에 아무것도 안쓰면 submit을 눌렀을 때 자기 자신의 주소로 다시 요청을 보냄. --%>
	<%-- modelAttribute : 어떤 객체와 연결하고있는지. TestControlloer에서 파라미터 @ModelAttribute("testVo") TestVo vo 처럼 사용 --%>
	<%-- 구 버전에서는 modelAttribute대신 commandName을 썼음. --%>
	<form:form modelAttribute="testVo" action="${pageContext.request.contextPath}/param.do"> 
		<%-- path : 입력 엘리먼트에 출력할 값. path속성에 모델객체에 어떤 변수값을 꺼내서 출력하고, 모델객체의 어떤 변수값에 담을 것인지.  --%>
		<%-- name과 라벨태그에 for속성, value값도 넣어줌.  --%>
		<%-- <input type="text" name="lunch" value="${testVo.lunch}" /> --%>
		점심식사 :
		<form:radiobuttons path="lunch" items="${codeList}" itemValue="id" itemLabel="title" />
		<%-- codeList에 있는 걸 하나씩 꺼내서 라디오버튼을 생성한다. 
			items="${codeList}" itemValue="id" itemLabel="title" 는 라디오 버튼이어서 썼던 것. --%>
		<br/>
		저녁식사 :
		<form:select path="dinner" items="${codeList}" itemValue="id" itemLabel="title" />
		<br/>
		좋아하는 음식들 :
		<form:checkboxes path="food" items="${codeList}" itemValue="id" itemLabel="title" />
		<br/>
		<input type="submit" />  
	</form:form>
	
	<h2>스프링 폼 태그 미사용</h2>
	<form action="" method="post">   
		<div>
			점심 식사 :
		  <c:forEach var="item" items="${codeList}" varStatus="status">	
		      <input type="radio" name="lunch" value="${item.id}" ${item.id==testVo.lunch?'checked':''} id="lunch${status.index}"  />
		      <label for="${item.id}"> ${item.title} </label>
		  </c:forEach>
	    </div>
		<!-- 라디오버튼으로 codeList에 있는 음식들을 선택할 수 있도록 구현  -->
		<!-- 현재 testVo의 lunch의 음식이 선택되어 있도록 구현  -->
		<br/>
		저녁 식사 : 
		<select name="dinner">
			<c:forEach var="item" items="${codeList}">	
			    <option value="${item.id}" ${item.id==testVo.dinner?'selected':''} >${item.title}</option>
			</c:forEach>
		</select>
		<!-- 셀렉트로 codeList에 있는 음식들을 선택할 수 있도록 구현  -->
		<!-- 현재 testVo의 dinner의 음식이 선택되어 있도록 구현  -->
		<br/>
		좋아하는 음식들 : 
		<c:forEach var="item" items="${codeList}" varStatus="status">	
			<label for="food${status.index}"></label>
			<input type="checkbox" name="food" value="${item.id}" ${testVo.food.contains(item.id)?'checked':''} id="food${status.index}" /> ${item.title}
		</c:forEach>
		<!-- 체크박스로 codeList에 있는 음식들을 선택할 수 있도록 구현  -->
		<!-- 현재 testVo의 food의 음식들이 선택되어 있도록 구현  -->
		<br/>
		<input type="submit" /> 
	</form>
</body>
</html>