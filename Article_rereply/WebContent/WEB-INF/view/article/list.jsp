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
<a href="/write" ><img id="writeBtn" src="/resource/img/writeBtn.png" width="100px"></a>
	<table id="tableList"  width="100%">
		<tr>
			<th>번호</th>
			<th width="70%">제목</th>
			<th>글쓴이</th>
			<th>조회수</th>
			<th>추천수</th>
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
	
</div>
<jsp:include page="/WEB-INF/view/common/footer.jsp"></jsp:include>