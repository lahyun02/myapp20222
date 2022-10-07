<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-expand-lg bg-light">
  <div class="container-fluid">
    <a class="navbar-brand" href="http://localhost:8000/myapp/member/list.do">웹앱수업</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse justify-content-between" id="navbarNavAltMarkup">
      <ul class="navbar-nav">
		<%-- 로그인했을 때 보여줄 메뉴 --%>
      	<c:if test="${loginUser != null}">
	        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/member/list.do">회원관리</a></li>
	        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/bbs/list.do">게시판</a></li>
	        <li class="nav-item"><a class="nav-link disabled">${loginUser.memName}님</a></li>
	        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/member/logout.do">로그아웃</a> </li>
			<%-- 세션을 꺼낼때는 sessionScope. ${sessionScope.loginUser.memName} 여기에서 sessionScope를 생략하면 모든 page, request, session 등 scope를 다 뒤져서 찾는다. loginUser이 이름으로 다른 곳에 저장된 것이 없다면 sessionScope 생략 가능. --%>
      	</c:if>
      </ul>
	  <ul class="navbar-nav">
		<%-- 로그인 안했을 때 보여줄 메뉴 --%>
		<c:if test="${loginUser == null}">
    	    <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/member/login.do">로그인</a> </li>
        	<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/member/add.do">회원가입</a> </li>
		</c:if>
      </ul>
    </div>
    
  </div>
</nav>