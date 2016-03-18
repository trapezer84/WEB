<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>   	
<jsp:include page="/WEB-INF/view/common/header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/view/common/logout.jsp"></jsp:include>
</div>
<head>
<script type="txext/javascript" src="<c:url value="/resource/js/json2.js"/>"></script>
<script type="text/javascript">
	
	$(document).ready( function(){
		$("#addNewMember").click( function(){
			
			var form = $("#addNewMemberForm");
			
			form.attr("method", "POST");
			form.attr("action", "<c:url value="/addNewMember"/>");

			form.submit(); 
		});
		
		$("#userIdCheck").click( function() {
			$.post( //파라미터 3개 가지고 있음 
					"/memberIdCheck" //어디로 요청
					, { "userId" : $("#userId").val() } //뭘 준다.  
					, function(data) { // 콜백, 응답해주는것 (우리가 직접 처리해줘야 한다)
						// 방법 3. 
						var jsonData = {}
						try {
							jsonData = JSON.parse(data);
						} catch (e) {
							jsonData.result = false;
						}
						
						if ( jsonData.result ) {
							
							if (jsonData.isExistMember) {
								alert("아이디가 이미 존재합니다.");
								$("#userId").val("");
							} 
							else {
								alert("아이디 사용 가능합니다.");
							}
						} else {
							alert("세션이 완료되었습니다.");
							location.href="/";
						}
					});  			
		});
		
	});

</script>
</head>

<div id="joinwrapper">
	<br />

	<form id="addNewMemberForm">
		<table id="joinTable">
			<tr>
				<th colspan="3" style="text-align: center;">회원가입</th>
			</tr>

			<tr>
				<th>아이디</th>
				<td><input type="text" id="userId" name="userId"
					style="width: 100%;" /></td>
				<td><input type="button" id="userIdCheck" name="userIdCheck"
					value="중복 확인" style="width: 100%; height: 100%;" /></td>
			</tr>

			<tr>
				<th>비밀번호</th>
				<td colspan="2"><input type="password" id="userPassword"
					name="userPassword" style="width: 100%;" /></td>
			</tr>

			<tr>
				<th>비밀번호 확인</th>
				<td colspan="2"><input type="password" id="userPasswordCheck"
					name="userPasswordCheck" style="width: 100%;" /></td>
			</tr>

			<tr>
				<th>별명(애칭)</th>
				<td colspan="2"><input type="text" id="nickName"
					name="nickName" style="width: 100%;" /></td>
			</tr>

			<tr>
				<th>이메일</th>
				<td colspan="2"><input type="email" id="userEmail"
					name="userEmail" style="width: 100%;" /></td>
			</tr>

			<tr>
				<td colspan="3"><input type="button" id="addNewMember"
					value="회원 가입" style="width: 100%; height: 100%;" /></td>
			</tr>
		</table>
	</form>



</div>

<jsp:include page="/WEB-INF/view/common/footer.jsp"></jsp:include>