<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:include page="/WEB-INF/view/common/header.jsp"></jsp:include>
<script type="text/javascript">
	$(document).ready(function() {
		$("#doWrite").click(function() {

			/*
			// 유효성 검사 or Validation Check
			if ($("#actorName").val() == "") {
				alert("배우의 이름을 입력하세요.");
				return;
			}*/

			var form = $("#writeForm")
			// attr = attribute
			// jQueryObject.attr("속성명", "속성 값");
			form.attr("method", "POST");
			form.attr("action", "/writeAction");
			form.submit();

		});
	});
</script>
<form id="writeForm">

	<table>
		<tr>
			<td>Title</td>
			<td>
				<input type="text" id="title" name="title" style="width:250px" />
			</td>
		</tr>

		<tr>
			<td>Description</td>
			<td>
				<textarea id="description" name="description" style="width: 150px"> </textarea>
			</td>
		</tr>	
	</table>
	
	<input type="button" id="doWrite" value="등록" />
	<input type="reset" value="다시쓰기" />	
	<input type="button" id="cancel" value="취소" />	

</form>


<jsp:include page="/WEB-INF/view/common/footer.jsp"></jsp:include>