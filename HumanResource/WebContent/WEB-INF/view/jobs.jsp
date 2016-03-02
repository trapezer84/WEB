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
		<th>JOB_ID</th>
		<th>JOB_TITLE</th>
		<th>MIN_SALARY</th>
		<th>MAX_SALARY</th>
	</tr>
	
	<c:forEach items="${allJobs}" var="jobs">
	<tr>
		<td>${jobs.jobId}</td>
		<td>${jobs.jobTitle}</td>
		<td>${jobs.minSalary}</td>
		<td>${jobs.maxSalary}</td>
	</tr>
	</c:forEach>

</table>
</body>
</html>