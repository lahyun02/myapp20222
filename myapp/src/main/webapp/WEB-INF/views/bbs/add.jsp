<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 


<script type="text/javascript">

</script>

<div class="row">
    <div class="col"> 
	<h1>게시글등록</h1>
	<%-- enctype : 첨부파일시 인코딩타입 지정 (form태그 내) --%>
	<form:form enctype="multipart/form-data" modelAttribute="bbsVo" id="memForm" action="${pageContext.request.contextPath}/bbs/add.do" method="post" > 
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
	  <div class="mb-3">
	    <label class="form-label">첨부파일1</label>
	    <input type="file" name="upfileList" class="form-control" />
	  </div>
	  <div class="mb-3">
	    <label class="form-label">첨부파일2</label>
	    <input type="file" name="upfileList" class="form-control" />
	  </div>
	  <button type="submit" id="saveBtn" class="btn btn-primary"><i class="bi bi-save"></i> 저장</button>
	 	<a href="${pageContext.request.contextPath}/bbs/list.do">
			<button type="button" class="btn btn-outline-warning"><i class="bi bi-filter-square"></i> 목록</button>
		</a>
	</form:form>
	
	
	</div>
</div>

