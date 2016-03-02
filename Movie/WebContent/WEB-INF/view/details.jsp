<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<table>
	<tr>
		<th>MOVIE_ID</th>
		<th>TITLE</th>
		<th>RATE</th>
		<th>RUNNING_TIME</th>
		<th>OPEN_DATE</th>
		<th>GRADE_ID</th>
		<th>GRADE_TITLE</th>
	</tr>
	<tr>
		<td>${ movieVO.movieId }</td>
		<td>${ movieVO.title }</td>
		<td>${ movieVO.rate }</td>
		<td>${ movieVO.runningTime }</td>
		<td>${ movieVO.openDate }</td>
		<td>${ movieVO.gradeId }</td>
		<td>${ movieVO.gradeTitle }</td>
	</tr>
</table>

<table>
	<c:forEach items="${ directors }" var="dir">
	<tr><td>감독 : ${ dir.directorName }</td></tr>
	</c:forEach>
	
	<c:forEach items="${ actors }" var="act">
	<tr><td>출연 배우 : ${ act.actorName }</td></tr>
	</c:forEach>
	
	<c:forEach items="${ genres }" var="gen">
	<tr><td>장르 : ${ gen.genreTitle }</td></tr>
	</c:forEach>
	
</table>
</body>
</html>



