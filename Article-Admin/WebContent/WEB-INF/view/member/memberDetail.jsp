<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/view/common/header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/view/common/logout.jsp"></jsp:include>
</div>
<jsp:include page="/WEB-INF/view/member/leftMenu.jsp"></jsp:include>

<div id="memberDetailWrapper">
	<table id="memberDetail">
		<tr>
			<th>아이디</th>
			<th>닉네임</th>
			<th>비밀번호</th>
			<th>이메일</th>
			<th>관리자 여부</th>
		</tr>
		
		<tr>
			<td>${member.memberId}</td>
			<td>${member.nickName}</td>
			<td>******</td>
			<td>${member.email}</td>
			<td>${member.isAdmin}</td>
		</tr>
	</table>
	
	<div id="memberButtonCollection">
		<a href= "<c:url value="/modifyMemberInfo?memberId=${member.memberId}" />" >
		<img src= "<c:url value="/resource/img/modifyMemberBtn.png" />"  width="100px" />
		</a>
	</div> <!-- memberButtonCollection -->
	
</div> <!-- memberDetailWrapper -->