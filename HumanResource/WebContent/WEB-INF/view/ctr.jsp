<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<table>
	<tr>
		<th>COUNTRY_ID</th>
		<th>COUNTRY_NAME</th>
		<th>REGION_ID</th>
	</tr>
	
	<c:forEach items="${allCountries}" var="ctr">
		<tr>
			<td>${ctr.countryId }</td>
			<td>${ctr.countryName }</td>
			<td>${ctr.regionId }</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>