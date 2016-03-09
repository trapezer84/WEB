<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/view/common/header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/view/common/logout.jsp"></jsp:include>


<body>

<div class="clear"></div>
<div id="listWrapper">
	<table id="tableList">
		<tr>
			<td>번호</td>
			<td>제목</td>
			<td>글쓴이</td>
			<td>조회수</td>
			<td>추천수</td>
		</tr>
		
	<c:forEach items = "${articles}" var = "article">
		<tr>
			<td>${article.articleId}</td>
			<td><a href = "/increaseHits?articleId=${article.articleId}">${article.title}</a></td>
			<td>${article.nickName}</td>
			<td>${article.hits}</td>
			<td>${article.recommends}</td>

		</tr>
	</c:forEach>
	</table>
	<br/>
	<a href="/write" >글쓰기</a>
</div>
<jsp:include page="/WEB-INF/view/common/footer.jsp"></jsp:include>