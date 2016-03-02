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
		<th>DEPARTMENT_ID</th>
		<th>DEPARTMENT_NAME</th>
		<th>MANAGER_ID</th>
		<th>LOCATION_ID</th>
	</tr>
	
	<c:forEach items="${allDepartments}" var="dpt">
	<tr>
		<td>${dpt.departmentId}</td>
		<td>${dpt.departmentName}</td>
		<td>${dpt.managerId}</td>
		<td>${dpt.locationId}</td>
	</tr>
	</c:forEach>
</table>


</body>
</html>