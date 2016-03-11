<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
			form.attr("action", "/doLogin");
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

		<div id="join">
			<a href="/registerMember" id="joinForm">
				<img src="/resource/img/join.png" id="btnJoin" />		
			</a>
		</div>
		
		<div id="login">
			<form id="loginForm">
				<img src="/resource/img/login.png" id="btnLogin" />		
				<input type="password" tabindex="2" id="userPw" name="userPw" placeholder="Password" />
				<input type="text" tabindex="1" id="userId" name="userId" placeholder="ID" />
			</form>
		</div>
	
		<div class="clear"></div>