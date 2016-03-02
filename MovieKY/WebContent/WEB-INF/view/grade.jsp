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
		<th>GRADE_ID</th>
		<th>GRADE_TITLE</th>
	</tr>
	<c:forEach items="${allGrades}" var="grade">
		<tr>
			<td>${grade.gradeId}</td>
			<td>${grade.gradeTitle}</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>