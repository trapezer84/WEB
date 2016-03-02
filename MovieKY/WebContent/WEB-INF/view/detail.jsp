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
		<th>MOVIE_ID</th>
		<th>TITLE</th>
		<th>RATE</th>
		<th>RUNNING_TIME</th>
		<th>OPEN_DATE</th>
		<th>GRADE_ID</th>
		<th>GRADE_TITLE</th>
		
	</tr>

	<tr>
		<td>${movie.movieId}</td>
		<td>${movie.title}</td>
		<td>${movie.rate}</td>
		<td>${movie.runningTime}</td>
		<td>${movie.openDate}</td>
		<td>${movie.gradeId}</td>
		<td>${movie.gradeTitle}</td>
	</tr>
</table>

<br/>
<table border=1px>

<c:forEach items="${directors}" var="director">
<tr>
	<td>감독 : ${director.directorName}</td>
</tr>
</c:forEach>

<c:forEach items="${actors}" var="actors">
<tr>
	<td>출연 배우 : ${actors.actorName}</td>
</tr>
</c:forEach>




</table>

</body>
</html>