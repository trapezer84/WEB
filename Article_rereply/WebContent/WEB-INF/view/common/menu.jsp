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
    </div>	

	
	<div id="menuWrapper">
		<div id="welcome" style="font-size: 22px; color: white">
			<br/>
			<c:if test="${ not empty sessionScope._MEMBER_.memberId }" >
			${sessionScope._MEMBER_.memberId} 님 안녕하세요!
			</c:if>
		</div>
	<br />
	<br />
		<a href="/list">
			<img src="/resource/img/home.png" /> 
		</a>
		
		<br />
		<br />	
	
		<a href="">
			<img src="/resource/img/anonBoard.png" /> 
		</a>
		
		<br />
		<br />
		
		<a href="/list">
			<img src="/resource/img/memberBoard.png" id="memberBoard"/>
		</a>
		
	</div>
