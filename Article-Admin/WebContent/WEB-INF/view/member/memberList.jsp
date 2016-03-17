<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/view/common/header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/view/common/logout.jsp"></jsp:include>
</div>
<jsp:include page="/WEB-INF/view/member/leftMenu.jsp"></jsp:include>

<script type="text/javascript">
	$(document).ready( function() {
		$("#massiveSelectCheckBox").click( function() {
			var isChecked = $(this).prop("checked");
			//일괄체크 되도록 하는 것 
			$(".deleteMemberId").prop("checked", isChecked);
		});
		
		$("#massiveDeleteBtn").click( function() {
			var isChecked = false;
			
			$(".deleteMemberId").each( function (index, data) {
				if (data.checked) {
					isChecked = data.checked;
				}
				
			});
			
			// 삭제할 대상을 정하지 않았으면 alert뜨기 
			if( !isChecked ) {
				alert("삭제할 대상을 선택하세요");
			}
			
			// 사용자의 confirm 받기 
			if( confirm("정말 삭제하시겠습니까?") ) {
				// 지우는 로직 넣기 
				var form = $("#massiveDeleteForm");
				form.attr("method", "post");
				form.attr("action", "<c:url value="/memberMassiveDelete" />");
				form.submit();
			}
			
		});
	});
</script>

<div id="memberListWrapper">

<!-- 총 회원 수 보여주기  -->
	총 <b style="color:red">${members.paging.totalArticleCount}</b> 명의 회원이 있습니다. 
	
<!-- 회원 버튼 -->	
	<div id="buttonCollection">
		<span id="massiveDeleteBtn" style="cursor: pointer"><img id="deleteBtn" src="<c:url value="/resource/img/deleteBtn.png" />" width="100px"></span>
		<span id="massiveBlockBtn" style="cursor: pointer"><img id="blockBtn" src="<c:url value="/resource/img/block.png" />" width="100px"></span>
		<a href="<c:url value="/registerMember"/>" ><img id="addBtn" src="<c:url value="/resource/img/addMember.png" />" width="100px"></a>
	</div>
	
<!-- 게시글 보여주는 list table -->
	<table id="tableList">
		<tr>
			<th>
				<input type="checkbox" id="massiveSelectCheckBox" />
			</th>
			<th>아이디</th>
			<th>닉네임</th>
			<th>이메일</th>
			<th>관리자</th>
		</tr>
		
		<form id="massiveDeleteForm">	
		<c:forEach items = "${members.memberList}" var = "member">
		<tr>
			<td>
				<input type="checkbox" class="deleteMemberId" name="deleteMemberId" value="${member.memberId}" />
			</td>
			<td><a href = "<c:url value="/memberDetail?memberId=${member.memberId}" />">${member.memberId}</a></td>
			<td>${member.nickName}</td>
			<td>${member.email}</td>
			<td>${member.isAdmin}</td>
		</tr>
		</c:forEach>
		</form>
		<tr>
			<td colspan="5">
			<form id="searchForm">
				${ members.paging.getPagingList("pageNO", "[@]", "[이전]", "[다음]", "searchForm") }
			</form>
			</td>
		</tr>
	</table>	
	
</div>

<jsp:include page="/WEB-INF/view/common/footer.jsp"></jsp:include>