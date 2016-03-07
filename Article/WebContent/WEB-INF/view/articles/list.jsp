<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<jsp:include page="/WEB-INF/view/common/header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/view/common/footer.jsp"></jsp:include>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="./resource/css/list.css" />
<title>게시판 리스트</title>
</head>
<body>

<h1>Article list</h1>
<div>
<table>
	<tr>
		<td>번호</td>
		<td>제목</td>
		<td>글쓴이</td>
		<td>조회수</td>
		<td>추천수</td>
	</tr>
	
	<c:forEach items="${ articleList }" var="article">
	<tr>
		<td>${article.articleId}</td>
		<td id="title"><a href="/detail?title=${article.title}">${article.title}</a></td>
		<td>${article.nickName}</td>
		<td>${article.hits}</td>
		<td>${article.recommends}</td>
	</tr>
	</c:forEach>
	
</table>
</div>
	