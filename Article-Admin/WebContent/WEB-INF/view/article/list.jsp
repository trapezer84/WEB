<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/view/common/header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/view/common/logout.jsp"></jsp:include>
<jsp:include page="/WEB-INF/view/article/leftMenu.jsp"></jsp:include>
<script type="text/javascript">
	$(document).ready( function() {
		
		// 검색 종류 list 받아오기 
		
		
		// 검색 초기화 클릭
		$("#initSearchBtn").click(function() {
			location.href = "<c:url value="/list/init" />";
		});
		
		// 검색 버튼 클릭 
		$("#searchBtn").click(function() {
			
			// 검색어를 입력하지 않았을 경우
			if ( $("#searchKeyword").val() =="") { 
				alert("검색어를 입력하세요");
				return;
			}
			
			// 검색을 마치고 movePage 0으로 간다 
			movePage('0');
			
		});
		
		$("#massiveSelectCheckBox").click( function() {
			var isChecked = $(this).prop("checked");
			//일괄체크 되도록 하는 것 
			$(".deleteArticleId").prop("checked", isChecked);
		});
		
		$("#massiveDeleteBtn").click( function() {
			var isChecked = false;
			
			$(".deleteArticleId").each( function (index, data) {
				if (data.checked) {
					isChecked = data.checked;
				}
				
			});
			
			// 삭제할 대상을 정하지 않았으면 alert뜨기 
			if( !isChecked ) {
				alert("삭제할 대상을 선택하세요");
			}
			
			// 사용자의 confirm 받기 
			if( confirm("정말 삭제하시겠습니까?") ) {
				// 지우는 로직 넣기 
				var form = $("#massiveDeleteForm");
				form.attr("method", "post");
				form.attr("action", "<c:url value="/massiveDelete" />");
				form.submit();
			}
			
		});
	});
</script>

<div id="listWrapper">
	<!-- 총 게시글 갯수 보여주기 -->
	총 <b style="color:red">${articles.paging.totalArticleCount}</b> 건의 게시물이 있습니다. 
	
	<!-- 글쓰기 / 삭제 버튼 -->
	<div id="buttonCollection">
		<a href="<c:url value="/write" />"><img id="writeBtn" src="<c:url value="/resource/img/writeBtn.png" />" width="100px"></a>
		<span id="massiveDeleteBtn" style="cursor: pointer"><img id="writeBtn" src="<c:url value="/resource/img/deleteBtn.png" />" width="100px"></span>
	</div>
		
	<!-- 게시글 보여주는 list table -->
	<table id="tableList">
		<tr>
			<th>
				<input type="checkbox" id="massiveSelectCheckBox" />
			</th>
			<th>번호</th>
			<th style="width:300px;">제목</th>
			<th>글쓴이</th>
			<th>조회수</th>
			<th>추천수</th>
		</tr>
		
		<form id="massiveDeleteForm">	
		<c:forEach items = "${articles.articleList}" var = "article">
		<tr>
			<td>
				<input type="checkbox" class="deleteArticleId" name="deleteArticleId" value="${article.articleId}" />
			</td>
			<td>${article.articleId}</td>
			<td class="articleTitle">
				<a href = "<c:url value="/increaseHits?articleId=${article.articleId}" />">${article.title}</a>
				<c:if test="${article.fileCount gt 0}">
					<img src= "<c:url value="/resource/img/attach.png" />" height=12px" />
					<!--[${article.fileCount}개의 첨부파일 있음]-->
				</c:if>
				
				<c:if test="${article.replyCount gt 0}">
					(${article.replyCount})
					<!--[${article.fileCount}개의 댓글 있음]-->
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