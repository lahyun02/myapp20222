<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="row">
    <div class="col">
		<h1>게시글목록</h1>
		<a href="${pageContext.request.contextPath}/bbs/add.do">
			<button type="button" class="btn btn-outline-warning"><i class="bi bi-person-plus"></i> 글쓰기</button>
		</a>
		<table class="table table-hover">
			<thead>
				<tr class="table-light"><th>제목</th><th>작성자</th><th>등록일</th></tr>
			</thead>
			<tbody>
				<c:forEach var="vo" items="${bbsList}" >
					<tr>
						<td><a href="${pageContext.request.contextPath}/bbs/edit.do?bbsNo=${vo.bbsNo}"><c:out value="${vo.bbsTitle}" /></a></td>
						<td><c:out value="${vo.bbsWriter}" /></td>
						<td>${vo.bbsRegDate}</td> 
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
