<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/view/common/header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/view/common/logout.jsp"></jsp:include>
<jsp:include page="/WEB-INF/view/common/menu.jsp"></jsp:include>
<head>
<script type="text/javascript">
	
	$(document).ready( function(){
		$(".hide").hide();
		
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
		
		//새로 추가하는 jQuery
		$("body").on( "click", "#writeReplyBtn", function () {
			var form = $("#wrtieReplyForm");
			
			form.attr("method", "POST");
			form.attr("action", "/doWriteReply");
			form.submit(); 
		});
		
		
		$(".writeReply").click ( function () {
			var table = $(this).parent().parent().parent();
			console.log(table.text());
			
			var groupId = table.children(":eq(1)").children(":eq(0)").html();
			var parentReplyId = table.children(":eq(1)").children(":eq(1)").html();
			var depth = table.children(":eq(2)").children(":eq(0)").html();
			var orderNo = table.children(":eq(2)").children(":eq(1)").html();
			var replyId = table.children(":eq(3)").children(":eq(0)").html();
			
			$("#groupId").val(groupId);
			$("#parentReplyId").val(replyId);
			$("#depth").val(parseInt(depth)+1);
			$("#orderNo").val(orderNo);
			$("#replyId").val(replyId);
			
			var form = $("#formWrapper").html();
			// detach : 날려버리는 것
			$("#formWrapper").detach();
			
			if ( form == undefined ) {
				
				$(".formAppender").each( function ( index, data ) {
					if ( data.innerHTML != "" ) {
						form = data.innerHTML;					
					}
				});
				$(".formAppender").html("");
			}
			
			var formAppender =table.parent().parent().children(":eq(1)");
			formAppender.html(form);
			formAppender.show();
			
			
		});
		
	});

</script>
</head>
<body>

<div id="detailWrapper">
<table id="detailTable">
	<tr>
		<th>글 번호</th>
		<th width="50%">글 제목</th>
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
<div id="formWrapper">
<form id="wrtieReplyForm"> 
	<input type="hidden" name="articleId" id="articleId" value="${article.articleId}" />
	<input type="hidden" name="depth" id="depth" value="0" />
	<input type="hidden" name="parentReplyId" id="parentReplyId" value="0" />
	<input type="hidden" name="replyId" id="replyId" value="0" />
	<input type="hidden" name="groupId" id="groupId" value="0" />
	<input type="hidden" name="orderNo" id="orderNo" value="0" />
	<textarea id="descript" name="descript"></textarea>
	<input type="button" id="writeReplyBtn" value="댓글 등록" />
</form>
</div>


<!-- 댓글 보여주기 -->
<div id="reply">
	<c:forEach items="${article.replyList}" var="reply">
		<div style="padding-left: ${reply.depth * 20}px">
		<table width="100%">
			<tr>
				<td width="5%" class="hide">${reply.nickName}</td>
				<td> ${reply.descript} <br/>
					<span class="writeReply" style="cursor: pointer"><b>댓글달기</b></span>
				</td>
				
			</tr>
			<tr class="hide">
				<td class="groupId">${reply.groupId}</td>
				<td class="parentReplyId">${reply.parentReplyId}</td>
			</tr>
			<tr class="hide">
				<td class="depth">${reply.depth}</td>
				<td class="orderNo">${reply.orderNo}</td>
			</tr>
			<tr class="hide">
				<td colspan="2" class="replyId">${reply.replyId}</td>
			</tr>
			
		</table>
		 <div class="hide formAppender"></div>	
		</div>
	</c:forEach>
</div>

<br/>
<br/>
<br/>
	

<jsp:include page="/WEB-INF/view/common/footer.jsp"></jsp:include>