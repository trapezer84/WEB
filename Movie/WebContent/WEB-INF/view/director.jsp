<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Director Talbe</title>
</head>
<body>
<table>
	<tr>
		<th>DIRECTOR_ID</th>
		<th>DIRECTOR_NAME</th>
		
	</tr>
	<c:forEach items="${ allDirectors }" var="dir">
	<tr>
		<td>${ dir.directorId }</td>
		<td>${ dir.directorName }</td>
	</tr>
	</c:forEach>
</table>
</body>
</html>