<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/view/common/header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/view/common/logout.jsp"></jsp:include>
</div>
<head>
<script type="text/javascript">
$(document).ready(function() {
		
	
		$(".hide").hide();

		$("#favorite").click(function() {
			$.post( //파라미터 3개 가지고 있음 
				"/favorite" //어디로 요청
				, { "articleId" : "${article.articleId}"} //뭘 준다.  
				, function(data) { // 콜백, 응답해주는것 (우리가 직접 처리해줘야 한다)
					// 방법 3. 
					var jsonData3 = {}
					try {
						jsonData3 = JSON.parse(data);
					} catch (e) {
						jsonData3.result = false;
					}
					
					
					alert(jsonData3);
					console.log(jsonData3);
					
					if ( jsonData3.result ) {
						var text = $("#favorite").text();
						if (jsonData3.isFavorite) {
							$("#favorite").text("♥");
						} 
						else {
							$("#favorite").text("♡");
						}
					} else {
						alert("세션이 완료되었습니다.");
						location.href="/";
					}
				}  
				
			);
			
		});
		
		$("#increaseRecommends").click(
				function() {

					var form = $("#increaseRecommendsForm");

					form.attr("method", "POST");
					form.attr("action",
							"<c:url value="/increaseRecommends" />");
					form.submit();
				});

		$("#writeReplyBtn").click(function() {
			var form = $("#wrtieReplyForm");

			form.attr("method", "POST");
			form.attr("action", "<c:url value="/doWriteReply" />");
			form.submit();
		});

		//새로 추가하는 jQuery
		$("body").on("click", "#writeReplyBtn", function() {
			var form = $("#wrtieReplyForm");

			form.attr("method", "POST");
			form.attr("action", "<c:url value="/doWriteReply"/>");
			form.submit();
		});

		$(".writeReply").click(
				function() {
					var table = $(this).parent().parent().parent();
					console.log(table.text());

					var groupId = table.children(":eq(1)").children(
							":eq(0)").html();
					var parentReplyId = table.children(":eq(1)")
							.children(":eq(1)").html();
					var depth = table.children(":eq(2)").children(
							":eq(0)").html();
					var orderNo = table.children(":eq(2)").children(
							":eq(1)").html();
					var replyId = table.children(":eq(3)").children(
							":eq(0)").html();

					$("#groupId").val(groupId);
					$("#parentReplyId").val(replyId);
					$("#depth").val(parseInt(depth) + 1);
					$("#orderNo").val(orderNo);
					$("#replyId").val(replyId);

					var form = $("#formWrapper").html();
					// detach : 날려버리는 것
					$("#formWrapper").detach();

					if (form == undefined) {

						$(".formAppender").each(function(index, data) {
							if (data.innerHTML != "") {
								form = data.innerHTML;
							}
						});
						$(".formAppender").html("");
					}

					var formAppender = table.parent().parent()
							.children(":eq(1)");
					formAppender.html(form);
					formAppender.show();

				});

	});
</script>
</head>
<div id="detailWrapper">
	<table id="detailTable">
		<tr>
			<th>글 번호</th>
			<th width="50%">글 제목
				<c:if test="${isExistFavoriteData}"> 
					<span id="favorite" style="color:red">♥</span>
				</c:if>
				<c:if test="${!isExistFavoriteData}"> 
					<span id="favorite" style="color:red">♡</span>
				</c:if>			
			</th>
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
				<td colspan="6"><a
					href="<c:url value= "/download?fileId=${file.fileId}&articleId=${article.articleId}" />">${file.fileName}</a>
				</td>
			</c:forEach>
		</tr>
	</table>

	<!-- 목록보기 -->
	<div id="buttonCollection">
		<div id="listCollection">
			<a href="<c:url value="/list" />"><img id="showListBtn"
				src="<c:url value="/resource/img/showListBtn.png" />"
				style="cursor: pointer; width: 100px;"></a>

			<!-- 글 수정/삭제 -->
			<c:set var="loginId" value="${sessionScope._MEMBER_.memberId}" />
			<c:if test="${loginId eq article.memberId || isAdmin}">
				<a href="<c:url value="/delete?articleId=${article.articleId}" />"><img
					class="deleteBtn"
					src="<c:url value="/resource/img/deleteDetailBtn.png" />"
					width=" 100px"></a>
				<a href="<c:url value="/modify?articleId=${article.articleId}" />"><img
					class="modifyBtn"
					src="<c:url value="/resource/img/modifyBtn.png" />" width=" 100px"></a>
			</c:if>
		</div>
		<div id="listCollectionTwo">
			<form id="increaseRecommendsForm">
				<img id="increaseRecommends"
					src="<c:url value="/resource/img/likeBtn.png" />"> <img
					id="reportArticle"
					src="<c:url value="/resource/img/reportBtn.png" />"> <input
					type="hidden" name="articleId" value="${article.articleId}">
			</form>
		</div>
	</div>

	<br />
	<!-- 댓글 등록 -->
	<div id="formWrapper">
		<form id="wrtieReplyForm">
			<input type="hidden" name="articleId" id="articleId"
				value="${article.articleId}" /> <input type="hidden" name="depth"
				id="depth" value="0" /> <input type="hidden" name="parentReplyId"
				id="parentReplyId" value="0" /> <input type="hidden" name="replyId"
				id="replyId" value="0" /> <input type="hidden" name="groupId"
				id="groupId" value="0" /> <input type="hidden" name="orderNo"
				id="orderNo" value="0" />
			<textarea id="descript" name="descript" style="width: 100%"></textarea>
			<input type="button" id="writeReplyBtn" value="댓글 등록" />
		</form>
	</div>
	<br />

	<!-- 댓글 보여주기 -->
	<div id="reply">
		<c:forEach items="${article.replyList}" var="reply">
			<div style="padding-left: ${reply.depth * 20}px">
				<table width="100%">
					<tr>
						<td width="5%" class="hide">${reply.nickName}</td>
						<td>${reply.descript}<br /> <span class="writeReply"
							style="cursor: pointer"><b>댓글달기</b></span>
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
</div>
<br />
<br />
<br />


<jsp:include page="/WEB-INF/view/common/footer.jsp"></jsp:include>