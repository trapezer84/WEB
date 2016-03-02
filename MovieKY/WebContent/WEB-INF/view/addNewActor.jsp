<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add New Actor</title>

<script type="text/javascript"
	src="/Movie/resource/js/jquery-1.12.1.js"></script>
<script>
	$(document).ready(function() {
		$("#addNewActor").click(function() {

			/*
			// 유효성 검사 or Validation Check
			if ($("#actorName").val() == "") {
				alert("배우의 이름을 입력하세요.");
				return;
			}*/

			var form = $("#addNewActorForm")
			// attr = attribute
			// jQueryObject.attr("속성명", "속성 값");
			form.attr("method", "POST");
			form.attr("action", "/Movie/addNewActorAction");
			form.submit();

		});
	});
</script>

</head>
<body>

	<%-- jQuery로 컨트롤하기 위해 적지 않음 --%>
	<%-- method : get/post 선택 --%>
	<%-- action : 어디로 보낼지 선택 --%>
	<form id="addNewActorForm">
		
		<input type="text" id="actorName" name="actorName" />
		<input type="button" id="addNewActor" value="등록하기" />
		${ ErrorMessage }

	</form>

</body>
</html>