<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">

	$(document).ready(function () {
		
		$("#btnLogin").click(function() {
			// validation check 생략
			var form = $("#loginForm");
			form.attr("method", "post");
			form.attr("action", "/doLogin");
			form.submit();
		});	
		
		
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
				<input type="password" id="userPw" name="userPw" placeholder="Password" />
				<input type="text" id="userId" name="userId" placeholder="ID" />
			</form>
		</div>
	
		<div class="clear"></div>