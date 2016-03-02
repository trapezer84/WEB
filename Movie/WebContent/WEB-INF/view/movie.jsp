<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

<style type="text/css">
table {
	border:solid, 1;
	
}
td {
	text-align: center;
}
</style>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Movie Table</title>

<script type="text/javascript" src="/Movie/resource/js/jquery-1.12.1.js"></script>
<script type="text/javascript">

	// jquery event listner 등록 - 초기화
	// Selector
	// $("대상").이벤트(기능);
	// document : movie.jsp 라는 현재 문서가
	// 실행될 준비가 되었으면
	$(document).ready( function() {

		/*
		alert("jquery를 실행할 준비가 되었습니다.");
		var windowHeight = $(window).height();
		var windowWidth = $(window).width();
		console.log("브라우저의 높이는 " + windowHeight + "입니다.");
		console.log("브라우저의 너비는 " + windowWidth + "입니다.");
		
		var documentHeight = $(document).height();
		var documentWidth = $(document).width();
		console.log("문서의의 높이는 " + windowHeight + "입니다.");
		console.log("문서의의 너비는 " + windowWidth + "입니다.");
		*/
		
		// ID가 addNewActor인 DOM을 클릭했을 때 
		$("#addNewActor").click( function() {
			console.log("배우 등록을 클릭했습니다.")
			// 페이지 이동
			location.href = "/Movie/addNewActor";
		});
		
		$("#addNewMovie").click( function() {
			console.log("영화 등록을 클릭했습니다.")
			// 페이지 이동
			location.href = "/Movie/addNewMovie";
		});
		
		$("td").click( function() {
			console.log( $(this).text() );
		});
		
	});

</script>

</head>
<body>
	<table>
		<tr>
			<th>MOVIE_ID</th>
			<th>TITLE (ACTOR_COUNT)</th>
			<th>RATE</th>
			<th>RUNNING_TIME</th>
			<th>OPEN_DATE</th>
			<th>GRADE_ID</th>
			<th>GRADE_TITLE</th>

		</tr>
		<c:forEach items="${ allMovies }" var="movie">
			<tr>
				<td>${ movie.movieId }</td>
				<td><a href="/Movie/detail?movieId=${ movie.movieId }">${ movie.title }
						(${ movie.actorCount })</a></td>
				<td>${ movie.rate }</td>
				<td>${ movie.runningTime }</td>
				<td>${ movie.openDate }</td>
				<td>${ movie.gradeId }</td>
				<td>${ movie.gradeTitle }</td>
			</tr>
		</c:forEach>
	</table>
	
	<%-- 새로운 버튼 추가 --%>
	<input type="button" id="addNewActor" value="배우 등록" />
	<input type="button" id="addNewMovie" value="영화 등록" />

</body>
</html>






