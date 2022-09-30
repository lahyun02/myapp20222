<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-lg bg-light">
  <div class="container-fluid">
    <a class="navbar-brand" href="http://localhost:8000/myapp/member/list.do">웹앱수업</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
      <div class="navbar-nav">
        <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/member/list.do">회원목록</a>
        <a class="nav-link" href="${pageContext.request.contextPath}/member/add.do">회원추가</a> 
        <a class="nav-link" href="${pageContext.request.contextPath}/bbs/list.do">게시글목록</a>
        <a class="nav-link" href="${pageContext.request.contextPath}/bbs/add.do">게시글추가</a>
<!--         <a class="nav-link disabled">Disabled</a> -->
      </div>
    </div>
  </div>
</nav>