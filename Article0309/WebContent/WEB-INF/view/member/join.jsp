<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/view/common/header.jsp"></jsp:include>
<head>
<script type="text/javascript">
	
	$(document).ready( function(){
		$("#addNewMember").click( function(){
			
			var form = $("#addNewMemberForm");
			
			form.attr("method", "POST");
			form.attr("action", "/addNewMember");

			form.submit(); 
		})
	})

</script>
</head>
<body>


<div id="joinwrapper">
<h1> 회원 가입 </h1>
<br/>

	<form id="addNewMemberForm">
		아이디 : <input type="text" id="userId" name="userId" /><br/><br/>
		비밀번호 : <input type="password" id="userPassword" name="userPassword" /><br/><br/>
		별명(애칭) : <input type="text" id="nickName" name="nickName" /><br/><br/>
		이메일 : <input type="email" id="userEmail" name="userEmail" /><br/><br/>
		
		<input type="button" id="addNewMember" value="회원 가입" />
	</form>		
	
	<br/>
</div>

<jsp:include page="/WEB-INF/view/common/footer.jsp"></jsp:include>