<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


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


<div class="row">
    <div class="col">
	<h1>게시글수정</h1>
	<form:form modelAttribute="bbsVo" action="${pageContext.request.contextPath}/bbs/edit.do" method="post" > 
	  <div class="mb-3">
	    <form:label path="bbsNo" class="form-label">글번호</form:label>
	    <form:input path="bbsNo" readonly="true" class="form-control" />
		<!--  name, value, id => path 하나로 해결! -->
	  </div>
	  <div class="mb-3">
	    <form:label path="bbsTitle" class="form-label">제목</form:label>
	    <form:input path="bbsTitle" class="form-control" cssErrorClass="form-control is-invalid"/>
	    <form:errors path="bbsTitle"  cssClass="invalid-feedback" />
	  </div>
	  <div class="mb-3">
	    <form:label path="bbsContent" class="form-label">내용</form:label>
	    <form:textarea path="bbsContent" rows="5" class="form-control" cssErrorClass="form-control is-invalid"/>
	    <form:errors path="bbsContent"  cssClass="invalid-feedback" />
	  </div>
	  <button type="submit" class="btn btn-primary"><i class="bi bi-save"></i> 저장</button>
	 	<a href="${pageContext.request.contextPath}/bbs/list.do">
			<button type="button" class="btn btn-outline-warning"><i class="bi bi-filter-square"></i> 목록</button>
		</a>
		<a id="delLink" href="${pageContext.request.contextPath}/bbs/del.do?bbsNo=${bbsVo.bbsNo}">
			<button type="button" class="btn btn-outline-warning"><i class="bi bi-filter-square"></i> 삭제</button>
		</a> 
	</form:form>
	
	
	</div>
</div>
