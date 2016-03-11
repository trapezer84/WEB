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
		});
		
		$("#writeReplyBtn").click( function (){
			var form = $("#wrtieReplyForm");
			
			form.attr("method", "POST");
			form.attr("action", "/doWriteReply");
			form.submit(); 
		});
	});

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
		<th>추천수</th>
	</tr>
	
	<tr>
		<td>${article.articleId}</td>
		<td>${article.title}</td>
		<td>${article.nickName}</td>
		<td>${article.memberId}</td>
		<td>${article.hits}</td>
		<td>${article.recommends}</td>							
	</tr>

	<tr>
		<th colspan="6">글 내용</th>
	</tr>

	<tr>
		<td id="articleDescript" colspan="6">${article.descript}</td>
	</tr>
	
	<tr>
		<th colspan="6">파일</th>
	</tr>

	<tr>
		<c:forEach items="${article.fileList}" var="file">
			<td colspan="6">
				<a href="/download?fileId=${file.fileId}&articleId=${article.articleId}">${file.fileName}</a>
			</td>
		</c:forEach>
		
	</tr>
	
</table>

<form id="increaseRecommendsForm">
<br/>
<input type="hidden" name="articleId" value="${article.articleId}">
<input type="button" id="increaseRecommends" value="추천하기">
</form>

<!--  
${sessionScope._MEMBER_.memberId}
${sessionScope._MEMBER_.nickName}
${sessionScope._MEMBER_.password}
${sessionScope._MEMBER_.email} -->

<!-- 댓글 등록 -->
<form id="wrtieReplyForm"> 
	<input type="hidden" name="articleId" id="articleId" value="${article.articleId}" />
	<input type="hidden" name="depth" id="depth" value="0" />
	<input type="hidden" name="parentReplyId" id="parentReplyId" value="0" />
	<input type="hidden" name="groupId" id="groupId" value="0" />
	<input type="hidden" name="orderNo" id="orderNo" value="0" />
	<textarea id="descript" name="descript"></textarea>
	<input type="button" id="writeReplyBtn" value="댓글 등록" />
</form>

<!-- 댓글 보여주기 -->
<div>
	<c:forEach items="${article.replyList}" var="reply">
		<table>
			<tr>
				<td> ${reply.nickName} </td>
				<td> ${reply.descript} <br/>
				<a href="#" >댓글달기</a>
				</td>
				
			</tr>
			<tr class="hide">
				<td class="groupId"> ${reply.groupId} </td>
				<td class="parentReplyId"> ${reply.parentReplyId} </td>
			</tr>
			<tr class="hide">
				<td class="depth"> ${reply.depth}</td>
				<td class="orderNo"> ${reply.orderNo} </td>
			</tr>
			
		</table>
	</c:forEach>
</div>

<!-- 글 수정/삭제 -->
<c:set var="loginId" value="${sessionScope._MEMBER_.memberId}" />
	<c:if test="${loginId eq article.memberId}">
	<a href="/delete?articleId=${article.articleId}"> 삭제 </a> 
	<a href="/modify?articleId=${article.articleId}"> 수정 </a>
	</c:if>
</div>
	

<jsp:include page="/WEB-INF/view/common/footer.jsp"></jsp:include>