<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>    
<%
	Cookie[] cookies = request.getCookies();

	String userId = "";
	String userPassword = "";
	String autoLogin = "";

	for ( Cookie cookie : cookies ) {
		if (cookie.getName().equals("userId") ) {
			userId = cookie.getValue();
		} 
		else if ( cookie.getName().equals("userPassword")) {
			userPassword = cookie.getValue();
		}
		else if ( cookie.getName().equals("autoLogin")) {
			autoLogin = cookie.getValue();
		}
	}
%>
<script type="text/javascript">

	$(document).ready(function () {
		
		$("#btnLogin").click(function() {
			// validation check 생략
			var form = $("#loginForm");
			form.attr("method", "post");
			form.attr("action", "<c:url value="/doLogin" />");
			form.submit();
		});	
		
		var autoLogin = "<%= autoLogin %>";
		if ( autoLogin == "true" ) {
			$("#userPw").val("<%= userPassword %>");
			$("#userId").val("<%= userId %>");
			$("#btnLogin").click();
		}
		
	});

</script>		
	<div id="loginWrapper">
		<div id="login">
			<form id="loginForm">
				<input type="text" tabindex="1" id="userId" name="userId" placeholder="ID" />
				<input type="password" tabindex="2" id="userPw" name="userPw" placeholder="Password" />
				<img src="<c:url value="/resource/img/login.png" />" id="btnLogin" />		
			</form>
		</div>
		<div id="join">
			<a href="<c:url value="/registerMember"/>" id="joinForm">
				<img src="<c:url value="/resource/img/join.png" />" id="btnJoin" />		
			</a>
		</div>
	</div>