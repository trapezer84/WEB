<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/view/common/header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/view/common/logout.jsp"></jsp:include>
</div>
<jsp:include page="/WEB-INF/view/member/leftMenu.jsp"></jsp:include>
<head>
<script type="text/javascript">
	
	$(document).ready( function(){
		$("#addNewMember").click( function(){
			
			var form = $("#addNewMemberForm");
			
			form.attr("method", "POST");
			form.attr("action", "<c:url value="/addNewMember"/>");

			form.submit(); 
		})
	})

</script>
</head>

<div id="joinwrapper">
	<br/>
	
	<form id="addNewMemberForm">
	<table id="joinTable" border="1px" width="500px">
		<tr>
			<th colspan="2" style="text-align: center;">회원가입</th>
		</tr>

		<tr>
			<th>아이디</th>
			<td><input type="text" id="userId" name="userId" style="width:100%;"/></td>
		</tr>
		
		<tr>
			<th>비밀번호</th>
			<td><input type="password" id="userPassword" name="userPassword" style="width:100%;"/></td>
		</tr>		
		<tr>
			<th>별명(애칭)</th>
			<td><input type="text" id="nickName" name="nickName" style="width:100%;"/></td>
		</tr>
		
		<tr>
			<th>이메일</th>
			<td><input type="email" id="userEmail" name="userEmail" style="width:100%;"/></td>
		</tr>	
		
		<tr>
			<td colspan="2">
				<input type="button" id="addNewMember" value="회원 가입" style="width:100%;" />
			</td>
		</tr>					
	</table>
	</form>
	
	

</div>

<jsp:include page="/WEB-INF/view/common/footer.jsp"></jsp:include>