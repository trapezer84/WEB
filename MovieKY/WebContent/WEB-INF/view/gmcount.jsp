<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border=1px>
	<tr>
		<th>장르</th>
		<th>영화 수</th>
	</tr>
	
	<c:forEach items="${allGenreMovieCount}" var="gm">
	<tr>
		<td>${gm.genreTitle}</td>
		<td>${gm.movieCount}</td>
	</tr>
	</c:forEach>
</table>
</body>
</html>