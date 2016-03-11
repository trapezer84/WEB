<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/view/common/header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/view/common/logout.jsp"></jsp:include>
<head>
<script type="text/javascript">
	
	$(document).ready( function(){
		$("#increaseRecommends").click( function(){
			
			var form = $("#increaseRecommendsForm");
			
			form.attr("method", "POST");
			form.attr("action", "/increaseRecommends");
			form.submit(); 
		})
	})

</script>
</head>
<body>

<div id="detailWrapper">
<table>
	<tr>
		<th>글 번호</th>
		<th>글 제목</th>
		<th>글쓴이</th>
		<th>글쓴이의 ID</th>
		<th>조회수</th>
	</tr>
	
	<tr>
		<td>${article.articleId}</td>
		<td>${article.title}</td>
		<td>${article.nickName}</td>
		<td>${article.memberId}</td>
		<td>${article.hits}</td>							
	</tr>

	<tr>
		<th colspan="5">글 내용</th>
	</tr>

	<tr>
		<td id="descript" colspan="5">${article.descript}</td>
	</tr>
	
	<tr>
		<th colspan="5">파일</th>
	</tr>

	<tr>
		<td colspan="5">
			<a href="/delete?articleId=${article.articleId}">${file.fileName}</a>
		</td>
		
	</tr>
	
</table>
	<form id="increaseRecommendsForm">
	<br/>
	추천수 <input id="recommends" name="recommends" value="${article.recommends}">
	<input type="hidden" name="articleId" value="${article.articleId}">
	<input type="button" id="increaseRecommends" value="추천하기">
	</form>
	
	
	${sessionScope._MEMBER_.memberId}
	${sessionScope._MEMBER_.nickName}
	${sessionScope._MEMBER_.password}
	${sessionScope._MEMBER_.email}
	
	<c:set var="loginId" value="${sessionScope._MEMBER_.memberId}" />
	
	<c:if test="${loginId eq article.memberId}">
	<a href="/delete?articleId=${article.articleId}"> 삭제 </a> 
	<a href="/modify?articleId=${article.articleId}"> 수정 </a>
	</c:if>
</div>
	

<jsp:include page="/WEB-INF/view/common/footer.jsp"></jsp:include>