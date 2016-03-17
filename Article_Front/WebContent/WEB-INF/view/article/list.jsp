<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/view/common/header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/view/common/logout.jsp"></jsp:include>


<body>
<div id="listWrapper">
	<!-- 총 게시글 갯수 보여주기 -->
	총 <b style="color:red">${articles.paging.totalArticleCount}</b> 건의 게시물이 있습니다. 
	
	<!-- 게시글 보여주는 list table -->
	<table id="tableList">
		<tr>
			<th>번호</th>
			<th style="width:300px;">제목</th>
			<th>글쓴이</th>
			<th>조회수</th>
			<th>추천수</th>
		</tr>
		
		<form id="massiveDeleteForm">	
		<c:forEach items = "${articles.articleList}" var = "article">
		<tr>
			<td>${article.articleId}</td>
			<td class="articleTitle">
				<a href = "<c:url value="/increaseHits?articleId=${article.articleId}" />">${article.title}</a>
				<c:if test="${article.fileCount gt 0}">
					<img src= "<c:url value="/resource/img/attach.png" />" height=12px" />
					<!--[${article.fileCount}개의 첨부파일 있음]-->
				</c:if>
				
			</td>
			<td>${article.nickName}</td>
			<td>${article.hits}</td>
			<td>${article.recommends}</td>
		</tr>
		</c:forEach>
		</form>
		<tr>
			<td colspan="6">
				<!-- 검색 form  -->
				<form id="searchForm">
					<div style="text-align: center;"> <!-- 페이징 -->
						${ articles.paging.getPagingList("pageNO", "[@]", "[이전]", "[다음]", "searchForm") }
					</div>
					<br/>
					<div style="text-align: center;"> <!-- 검색어 -->
						<c:set var="selectedList" value="${sessionScope._SEARCH_.searchList }" />
						<select name="searchList" id="searchList">
							<option value="article" ${selectedList eq "article" ? "selected" : "" }>제목 + 내용</option>
						  	<option value="memberId" ${selectedList eq "memberId" ? "selected" : "" }>작성자 아이디</option>
						  	<option value="memberNickName" ${selectedList eq "memberNickName" ? "selected" : "" }>작성자명</option>
						</select>
						
						<input type="text" id="searchKeyword" name="searchKeyword" value="${searchVO.searchKeyword}"/>
						<input type="button" id="searchBtn" name="searchBtn" value="검색"/>
						<input type="button" id="initSearchBtn" value="검색초기화" />
					</div>
				</form>			
			</td>
		</tr>
	</table>
	<br/>
	
</div>
<jsp:include page="/WEB-INF/view/common/footer.jsp"></jsp:include>