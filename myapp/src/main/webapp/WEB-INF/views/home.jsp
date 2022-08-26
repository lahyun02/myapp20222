<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Home</title>
	<script src="${pageContext.request.contextPath}/js/jquery-3.6.0.js"></script>
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
<form id="testForm" action="${pageContext.request.contextPath}/test.do" method="post">
	x : <input type="text" name="x" /> <br />
	y : <input type="text" name="y" /> <br /> 
	<p>음식주문</p>
	<input type="checkbox" name="food" value="pizza" /> 피자
	<input type="checkbox" name="food" value="sand" /> 샌드위치
	<input type="checkbox" name="food" value="spa" /> 스파게티 
	<br />
	<p>자격증</p>
	<table id="licTable" border="1">
		<thead>
			<tr><th>자격이름</th><th>발급기관</th><th>발급일</th><th></th></tr>
		</thead>
		<tbody>
			<tr>
				<td><input type="text" name="licenseName" /></td>
				<td><input type="text" name="licenseOrg" /></td>
				<td><input type="date" name="licenseDate" /></td>
				<td><button class="delBtn" type="button">삭제</button></td>
				<%-- 파라미터 이름과 TestVO 변수 이름이 동일해야 값이 바인딩될 수 있다. --%>
				<%-- 여러개에 동일한 값을 주려면 id보다 class를 사용하는 게 낫다. --%>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="4"><button id="addBtn" type="button">추가</button></td>
			</tr>
		</tfoot>
	</table>
	<input type="submit" value="전송" />
</form>
<%-- template 엘리먼트 --%>
<%-- 화면에 출력되지 않지만, 이후 동적으로 화면에 추가할 엘리먼트를 정의하기 위하여 사용 --%>
<%-- 구버전의 브라우저에서는 지원하지 않으며, 자바스크립트에서 템플릿 내용은 template 엘리먼트의 content 속성을 통해서 사용 --%>
<template id="rowTemp"> 
	<tr>
		<td><input type="text" name="licenseName" /></td>
		<td><input type="text" name="licenseOrg" /></td>
		<td><input type="date" name="licenseDate" /></td>
		<td><button class="delBtn" type="button">삭제</button></td>
	</tr>
</template>
<%-- template - 기본적인 행없이 추가버튼만 있게 할 수 있음. 
단, html5이후에서만 가능(구버전 지원x) 구버전일경우- div로 감싼 후 css로 안보이게 해서(display:none등) 사용 가능 --%>

<script type="text/javascript">



//추가버튼을 클릭하면, 자격증 1개 정보를 입력할 수 있는 <tr>을 <tbody>에 추가
//	var $row = $('#licTable > tbody > tr').clone();  //삭제버튼을 눌렀을 떄 나중에 없어질 수 있기 때문에 clone()한 것.
	//보통 이 변수 안에 제이쿼리 객체가 있다는 걸 표현하기 위해 변수 이름에 $를 붙임.
	
	var $row = $(document.querySelector('#rowTemp').content);
	$('#addBtn').on('click', function() {
// 		var row = '<tr>'
// 			+'<td><input type="text" name="licenseName" /></td>'
// 			+'<td><input type="text" name="licenseOrg" /></td>'
// 			+'<td><input type="date" name="licenseDate" /></td>'
// 			+'<td><button class="delBtn" type="button">삭제</button></td>'
// 			+'</tr>';
		var row = $row.clone();
		$('#licTable > tbody').append( row );	//$('#licTable > tbody').append( $row.clone() );
	});
	
	//삭제버튼을 클릭하면, 클릭한 삭제버튼이 속한 <tr>을 삭제 
	//버튼에 클릭이벤트가 들어오면 그 조상들에게도 이벤트가 발생한다.  //이벤트 캡쳐링 - 이벤트가 들어오면 잡는 것 //이벤트 버블링 - 이벤트가 나가는 것.
	//테이블에 클릭이 발생했는데 클릭이 발생한 애가 .delBtn면 함수를 실행해라.
	$('#licTable').on('click', '.delBtn', function(){
		$(this).closest('tr').remove();
	});
	//새로 브라우저화면을 처음 로딩했을 때 - 즉, 삭제버튼이 하나만 있었을 때 이 js 명령이 설정됨.
	// 이후 추가버튼으로 클래스 삭제버튼이 생긴 것들은 명령어가 적용안됨.
	
	//전송버튼을 클릭하면, 입력한 자격증 정보들이 올바른 파라미터명으로 전송
	$('#testForm').on('submit',function(ev){ //testForm에 submit이벤트가 발생하면 실행
// 		ev.preventDefault();  //submit 이벤트에 대한 브라우저 기본동작(폼제출) 취소 (혹은 return false; 하기)
		$('#licTable > tbody > tr').each(function(idx,elm) { //tbody의 tr마다 한번씩 함수 실행 
			console.log(idx,elm);
			$(elm).find('input').each(function(i,e){  //tr 내부의 각 input마다 한번씩 함수 실행 
				var n = $(e).prop('name');	//input의 원래 name 속성값 
				$(e).prop('name', 'license['+idx+'].' + n );  //input의 name 속성값 변경 
			});
			
		});
	});
	//.each -> tr 개수만큼 반복실행됨. idx -> 0,1,2... / elm -> tr
	
	
//	$(document).ready(function(){
// 		$("#addBtn").click(function(){
//		});
//	})
	
//	var i = 1;
// 	$("#addBtn").click(function(){
// 		$("tbody").append("<tr><td><input type='text' name='license["+i+"].licenseName' /></td><td><input type='text' name='license["+i+"].licenseOrg' /></td><td><input type='date' name='license["+i+"].licenseDate' /></td><td><button class='delBtn' type='button'>삭제</button></td></tr>");
// 		i++; 
// 	});
	

</script>	

</body>
</html>
