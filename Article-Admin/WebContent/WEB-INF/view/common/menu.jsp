<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>   
<script type="text/javascript">

	$(document).ready(function () {
		
		$("#memberBoard").click(function() {
			// validation check 
			// 회원이 아닐 때, alert 
			alert("회원이 아닙니다.");
		});	
		
	});
</script>	 
	
	<div id="menuWrapper">
	
	<ul>
		<li>홈으로</li>
		<li>익명 게시판</li>
		<li>회원 게시판</li>
		
	</ul>


		
	</div> <!-- menuWrapper -->
