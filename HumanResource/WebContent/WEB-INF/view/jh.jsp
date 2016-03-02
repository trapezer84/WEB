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
<table>
	<tr>
		<th>EMPLOYEE_ID</th>
		<th>START_DATE</th>
		<th>END_DATE</th>
		<th>JOB_ID</th>
		<th>DEPARTMENT_ID</th>
	</tr>
	
	<c:forEach items="${getAllJobHistory}" var="jh">
		<tr>
			<td>${jh.employeeId}</td>
			<td>${jh.startDate}</td>
			<td>${jh.endDate}</td>
			<td>${jh.jobId}</td>
			<td>${jh.departmentId}</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>