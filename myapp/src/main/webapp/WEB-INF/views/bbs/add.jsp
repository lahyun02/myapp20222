<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 


<script type="text/javascript">
// (1)회원등록 폼을 제출(submit)할 때, 
// 	비밀번호와 비밀번호확인 값이 다르면 안내메시지를 출력하고 제출하지 않도록 구현
// (2)중복확인을 성공한 적이 없으면 안내메시지를 출력하고 제출하지 않도록 구현
// (3)중복확인 버튼을 클릭하면 입력한 아이디가 데이터베이스에 없는 경우, 저장 버튼을 활성화
// 	입력한 아이디가 데이터베이스에 없는 경우, 저장 버튼을 비활성화(disabled)
// (4)회원아이디 값을 변경한 경우에는 다시 중복확인을 하도록 구현(중복확인 버튼을 활성화, 저장 버튼을 비활성화)

$(function(){
	var idChecked = false; //아이디중복확인여부
	
	$('#memForm').on('submit', function(e){  // id=memForm인 폼 엘리먼트 제출(submit)시 실행
		if(!idChecked) {
			alert('아이디 중복확인이 필요합니다.'); 
			return false;
		}
		if( $('#memPass').val() != $('#memPassCheck').val() ) { 
			alert("비밀번호가 서로 다릅니다. 다시 확인해주세요.");
			return false;  //e.preventDefault();
		}
	});
	
	$('#dupBtn').on('click', function(){
		$.ajax({  //요청보내기
			  url: "${pageContext.request.contextPath}/bbs/duplicate.do",  //요청주소
			  method: "post",	//요청방식
			  data: { memId : $('#memId').val() }, //파라미터 붙여서 보내기
			  dataType: "json"   //결과는 json으로 받기
			}).done(function( data ) {  //요청에 대한 응답을 성공적으로 받았을때 실행할 함수 //응답받기
				  console.log( data );  //컨트롤러에서 return한 map이 data에 전달됨
				  idChecked = data.result;
				  if(data.result) { //사용가능한 아이디
					  alert('사용가능한 아이디입니다.'); 
//   					  $('#saveBtn').prop('disabled', false);
//   					  $('#dupBtn').prop('disabled', true);
				  } else {  //이미 존재하는 아이디 
					  alert('이미 존재하는 아이디입니다.'); 
//  					  $('#saveBtn').prop('disabled', true);  
//  					  $('#dupBtn').prop('disabled', false);  
				  }
				  // $('#saveBtn').prop('disabled', !data.result);
				  //$('#dupBtn').prop('disabled', data.result);
			}).fail(function( jqXHR, textStatus ) {  //요청에 대한 응답을 실패했을 때 실행할 함수
				//jqXHR, textStatus는 사용안할 거면 안적어도 됨 
				alert( "아이디 중복확인 요청 실패: " + textStatus );
			});
	});
	
	$('#memId').on('change', function(){  
		idChecked = false;
// 		$('#saveBtn').prop('disabled', true);  	  
// 		$('#dupBtn').prop('disabled', false);  
	});
	
});
</script>

<div class="row">
    <div class="col"> 
	<h1>게시글등록</h1>
	<form:form modelAttribute="bbsVo" id="memForm" action="${pageContext.request.contextPath}/bbs/add.do" method="post" > 
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
	  <button type="submit" id="saveBtn" class="btn btn-primary"><i class="bi bi-save"></i> 저장</button>
	 	<a href="${pageContext.request.contextPath}/bbs/list.do">
			<button type="button" class="btn btn-outline-warning"><i class="bi bi-filter-square"></i> 목록</button>
		</a>
	</form:form>
	
	
	</div>
</div>

