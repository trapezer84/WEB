<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<jsp:include page="/WEB-INF/view/common/header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/view/common/footer.jsp"></jsp:include>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="./resource/css/details.css" />
<title>게시판 글 (detailed page)</title>
<script type="text/javascript" src="/Movie/resource/js/jquery-1.12.1.js"></script>
<script type="text/javascript">
	$(document).ready( function() {
		$("#recommendArticle").click( function(){
			console.log("추천을 등록했습니다");
		});
		
	});
</script>
</head>
<body>

<div>
<table>
	<tr id="title">
		<td>글 번호</td>
		<td>글 제목</td>
		<td>글쓴이</td>
		<td>글쓴이의 ID</td>
	</tr>
	<tr>
		<c:forEach items="${ articles }" var="articles">
		<td>${articles.articleId}</td> </c:forEach>
		<c:forEach items="${ articles }" var="articles">
		<td>${articles.title}</td> </c:forEach>
		<c:forEach items="${ articles }" var="articles">
		<td>${articles.nickName }</td> </c:forEach>
		<c:forEach items="${ articles }" var="articles">
		<td>${articles.memberId}</td> </c:forEach>
	</tr>
	
	
	<tr id="title">
		
		<td colspan="4">글 내용 </td>
	</tr>
	<tr>
		<c:forEach items="${ articles }" var="articles">
		<td colspan="4">${articles.descript}</td> </c:forEach>
	</tr>
	
	<tr id="title">
		<td colspan="2">조회수</td>
		<td colspan="2">추천수</td>
	</tr>
	<tr>
		<c:forEach items="${ articles }" var="articles">
		<td colspan="2">${articles.hits}</td> </c:forEach>
		<c:forEach items="${ articles }" var="articles">
		<td colspan="2">${articles.recommends}</td> </c:forEach>
	</tr>
</table>

<input type="button" id="recommendArticle" value="추천하기" />

</div>

