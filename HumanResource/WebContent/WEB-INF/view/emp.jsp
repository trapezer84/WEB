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
			<th>EMPLOYEE_ID</th>
			<th>FIRST_NAME</th>
			<th>LAST_NAME</th>
			<th>EMAIL</th>
			<th>PHONE_NUMBER</th>
			<th>HIRE_DATE</th>
			<th>JOB_ID</th>
			<th>SALARY</th>
			<th>COMMISSION_PCT</th>
			<th>MANAGER_ID</th>
			<th>DEPARTMENT_ID</th>
		</tr>
		<c:forEach items="${ allEmployees }" var="emp">

			<tr>
				<td>${emp.employeeId }</td>
				<td>${emp.firstName }</td>
				<td>${emp.lastName }</td>
				<td>${emp.email}</td>
				<td>${emp.phoneNumber }</td>
				<td>${emp.hireDate}</td>
				<td>${emp.jobId}</td>
				<td>${emp.salary}</td>
				<td>${emp.commissionPct}</td>
				<td>${emp.managerId}</td>
				<td>${emp.departmentId}</td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>