<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">

	$(document).ready(function() {
		$("#btnlogin").click(function() {
			var form = $("#loginForm");
			form.attr("method", "post");
			form.attr("action", "/doLogin");
			form.submit();
		});
	});

</script>
    <div id="login">
    	<form id="loginForm">
    		<img src ="/resource/img/login.png" id="btnlogin" />
    		<input type="text" id="userId" name="userId" placeholder="ID"/>
    		<input type="password" id="userPw" name="userPw" placeholder="PASSWORD"/>	
    	</form>
    </div>
    <div class="clear"></div>