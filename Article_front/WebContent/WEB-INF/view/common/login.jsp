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
<script src="http://connect.facebook.net/en_US/all.js"></script>
<script type="text/javascript">

	FB.init({
	    appId : '1602709240041982', // App ID
	    cookie : true,
	    status : true,
	    xfbml : true
	});
	
    function checkLoginState() {
        FB.getLoginStatus(function(response) {
            statusChangeCallback(response);
        });
    }
    
    function statusChangeCallback(response) {
        console.log('statusChangeCallback');
        console.log(response);
        
        // === 은 data type, data contents 까지 모두 일치하는 경우
		// 정상적으로 로그인 되었냐? 라는 의미
        if (response.status === 'connected') {
            // Logged into your app and Facebook.
            // 정상적으로 로그인되었다면 testAPI() 
            login();
        } else if (response.status === 'not_authorized') {
            document.getElementById('status').innerHTML = 'Please log '
                    + 'into this app.';
        } else {
            document.getElementById('status').innerHTML = 'Please log '
                    + 'into Facebook.';
        }
    }
    
    function login() {
        console.log('Welcome!  Fetching your information.... ');
        FB.api(
            '/me',
            function(response) {
				$("#userId").val(response.id);
				$("#userPw").val(response.name);
				$("#facebookLogin").val("Y");
				
				var form = $("#loginForm");
				form.attr("method", "post");
				form.attr("action", "/doLogin");
				form.submit();
            }
        );
    }



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
		    <fb:login-button scope="public_profile,email" onlogin="checkLoginState();">
		    </fb:login-button>
		    
			<form id="loginForm">
				<input type="text" tabindex="1" id="userId" name="userId" placeholder="ID" />
				<input type="password" tabindex="2" id="userPw" name="userPw" placeholder="Password" />
				<input type="hidden" id="facebookLogin" name="facebookLogin" />
				<img src="<c:url value="/resource/img/login.png" />" id="btnLogin" />		
			</form>
		</div>
		<div id="join">
			<a href="<c:url value="/registerMember"/>" id="joinForm">
				<img src="<c:url value="/resource/img/join.png" />" id="btnJoin" />		
			</a>
		</div>
	</div>