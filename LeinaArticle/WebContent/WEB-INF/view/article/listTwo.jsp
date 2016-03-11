<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/view/common/header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/view/common/logout.jsp"></jsp:include>
<jsp:include page="/WEB-INF/view/common/menu.jsp"></jsp:include>


<body>
총 <b style="color:red">${articles.paging.totalArticleCount}</b> 건의 게시물이 있습니다. 
<div class="clear"></div>
<div id="listWrapper">
	<table id="tableList">
		<tr>
			<td>번호</td>
			<td id="titleTD">제목</td>
			<td>글쓴이</td>
			<td>조회수</td>
			<td>추천수</td>
		</tr>
		
	<c:forEach items = "${articles.articleList}" var = "article">
		<tr>
			<td>${article.articleId}</td>
			<td class="articleTitle">
				<a href = "/increaseHits?articleId=${article.articleId}">${article.title}</a>
				<c:if test="${article.fileCount gt 0}">
					<img src="/resource/img/attach.png" height=12px" />
					<!--[${article.fileCount}개의 첨부파일 있음]-->
				</c:if>
			</td>
			<td>${article.nickName}</td>
			<td>${article.hits}</td>
			<td>${article.recommends}</td>

		</tr>
	</c:forEach>
		<tr>
			<td colspan="5">
			<form id="searchForm">
				${ articles.paging.getPagingList("pageNO", "[@]", "[이전]", "[다음]", "searchForm") }
			</form>
			</td>
		</tr>
	
	</table>
	
	
	<br/>
	<a href="/write" >글쓰기</a>
</div>
<jsp:include page="/WEB-INF/view/common/footer.jsp"></jsp:include>