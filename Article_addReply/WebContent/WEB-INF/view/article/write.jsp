<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>    
<jsp:include page="/WEB-INF/view/common/header.jsp"></jsp:include>
<script type="text/javascript">
	$(document).ready(function() {

		<c:if test="${empty article }" >
		$("#doWrite").click(function() {
			var form = $("#writeForm")
			// attr = attribute
			// jQueryObject.attr("속성명", "속성 값");
			form.attr("method", "POST");
			form.attr("action", "/writeAction");
			form.submit();
		});
		</c:if>
		
		<c:if test="${not empty article}" >
		$("#doModify").click(function() {
			var form = $("#writeForm")
			// attr = attribute
			// jQueryObject.attr("속성명", "속성 값");
			form.attr("method", "POST");
			form.attr("action", "/doModify");
			form.submit();
		});
		
		$("#reset").click(function() {
			/*$("#title").val("${ article.title }");
	         $("#description").val("${ article.descript }"); */
			location.reload();
		});	

		</c:if>
		
		$("#cancel").click(function() {
			location.href="/list";
		});
		
	});
</script>
<div id="writeWrapper">
<form id="writeForm" enctype="multipart/Form-data">
	<table>
		<tr>
			<td>Title</td>
			<td>
				<input type="text" id="title" name="title" style="width:350px" 
				value="${article.title}" />
			</td>
		</tr>

		<tr>
			<td>Description</td>
			<td>
				<textarea id="description" name="descript" style="width: 350px; height: 400px;">${article.descript}</textarea>
			</td>
		</tr>	
		
		<tr>
			<td>업로드</td>
			<td>
				<input type="file" id="file" name="file" />
			</td>
		</tr>
		
	</table>
	
	<c:if test="${ empty article }" >
		<input type="button" id="doWrite" value="등록" />
		<input type="reset" value="다시쓰기" />	
	</c:if>
	
	<c:if test="${ not empty article}" >
		<input type="hidden" name="articleId" value="${article.articleId}"/>
		<input type="button" id="doModify" value="수정" />
		<input type="button" id="reset" value="다시쓰기" />	
	</c:if>	
	
	<input type="button" id="cancel" value="취소" />
	
</form>
</div>

<jsp:include page="/WEB-INF/view/common/footer.jsp"></jsp:include>