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
<script src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script> 
<title>회원관리</title>
<script type="text/javascript">
//	삭제링크 클릭시, 삭제여부를 묻는 창을 출력하고, 삭제하겠다고 선택하는 경우에만 삭제하도록 구현
// 	alert('') //출력만
// 	confirm('') //예, 아니오 true, false
// 	prompt('') //문자열 입력 받기
	$(function() {  //문서 로드가 끝난 다음에 실행하겠다는 것.
		$('#delLink').on('click', function(e){ //id=delLink인 엘리먼트 클릭시 실행
			var confirm = window.confirm("삭제하시겠습니까?");
			if(confirm === false) {
				e.preventDefault(); // 이벤트에 대한 브라우저 기본동작 취소
	//			return false;  //이벤트리스너함수에서 false를 반환하면 이벤트에 대한 브라우저 기본동작 취소 
			}
		});
	});
	
	//문서 로드가 끝난 다음에 실행하겠다는 것.$(function() {}와 동일
// 	window.addEventListener('DOMContentLoaded', function() {
// // 		document.querySelector('#delLink').addEventListener('click', function(e) {
// // 		});
// 		document.querySelector('#delLink').onclick = function(e) {
// 			if(!confirm('진짜 삭제?')) {
// 				e.preventDefault(); // 또는 return false; 
// 			}
// 		};
// 	})

</script>

</head>
<body>
<div class="container">
<div class="row">
    <div class="col">
	<h1>회원정보변경</h1>
	<form action="${pageContext.request.contextPath}/member/edit.do" method="post" > 
	  <div class="mb-3">
	    <label for="memId" class="form-label">아이디</label>
	    <input type="text" name="memId" value="${memVo.memId}" readonly="readonly" class="form-control" id="memId" >
	  </div>
	  <div class="mb-3">
	    <label for="memName" class="form-label">이름</label>
	    <input type="text" name="memName" value="${memVo.memName}" class="form-control" id="memName">
	  </div>
	  <div class="mb-3">
	    <label for="memPoint" class="form-label">포인트</label>
	    <input type="text" name="memPoint" value="${memVo.memPoint}" class="form-control" id="memPoint">
	  </div>
	  <button type="submit" class="btn btn-primary"><i class="bi bi-save"></i> 저장</button>
	 	<a href="${pageContext.request.contextPath}/member/list.do">
			<button type="button" class="btn btn-outline-warning"><i class="bi bi-filter-square"></i> 회원목록</button>
		</a>
		<a id="delLink" href="${pageContext.request.contextPath}/member/del.do?memId=${memVo.memId}">
			<button type="button" class="btn btn-outline-warning"><i class="bi bi-filter-square"></i> 삭제</button>
		</a> 
	</form>
	
	
	</div>
</div>
</div>
<!-- Option 1: Bootstrap Bundle with Popper -->
<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js" ></script>
</body>
</html>