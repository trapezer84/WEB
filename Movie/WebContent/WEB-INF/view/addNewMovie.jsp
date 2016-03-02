<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add New Movie</title>

<script type="text/javascript"
	src="<c:url value="/resource/js/jquery-1.12.1.js"/>"></script>
<script type="text/javascript">
	
	$(document).ready( function() {
		
		/*
		$("#addNewMovieForm").hide();
		// 1초 = 1000
		
		$("#addNewMovieForm").slideDown(5000);
		$("#addNewMovieForm").slideUp(5000);
		
		$("#addNewMovieForm").fadeIn(1000);
		$("#addNewMovieForm").fadeOut(1000);
		*/
		
		$("#directors").click(function(){
			var selectedDirectors = "";
			$("#directors option:selected").each( function(index, data) {
				selectedDirectors += data.text;
				selectedDirectors += ", ";
			});		
			$("#directorsName").text(selectedDirectors);
		});
		
		
		$("#addNewMovie").click(function() {
			
			//Validation Check : movieTitle 
			var movieTitle = $("#movieTitle").val();
			movieTitle = $.trim(movieTitle); //공백제거
			if(movieTitle == "") {
				alert("영화명을 입력하세요!");
				$("#movieTitle").focus(); //입력하지 않았을 때, 여기서 커서가 깜빡거린다. 
				return;
			}
			
			//Validation Check : rate
			var rate = $("#rate").val();
			rate = $.trim(rate);
			if(rate == "") {
				alert("평점을 입력하세요!");
				$("#rate").focus();
				return;
			}
			
			//rate = parseFloat(rate);
			if ( isNaN(rate) ) {
				alert("평점을 올바르게 입력하세요! \n소수점이 아닙니다.");
			}
			
			//var isNumber = isNaN(rate);
			//alert(isNumber);
			//return;
			
			//Validation Check : runningTime
			var runningTime = $("#runningTime").val();
			runningTime = $.trim(runningTime);
			if(runningTime == "") {
				alert("상영시간을 입력하세요!");
				return;
			}
			if (runningTime.length > 5) {
				alert("상영 시간을 올바르게 입력하세요!");
				return;
			}
			
			var regExp = new RegExp("^[0-2]{0,1}[0-9]{1}:[0-5][0-9]$");
			var isValidRunningTiem = regExp.test(runningTime);
			if (isValidRunningTiem == false) {
				alert("상영시간을 올바르게 입력하세요!");
				return;
			}
			
			//Validation Check : openDate
			var openDate = $("#openDate").val();
			openDate = $.trim(openDate);
			if(openDate == "") {
				alert("개봉일을 입력하세요!");
				return;
			}
			
			//Validation Check : grade
			var grade = $(".grade:checked").val();
			//grade = $.trim( grade );
			//alert(grade);
			if ( grade == undefined ) {
				alert("등급 재 입력");
				$(".grade").fadeOut().fadeIn().fadeOut().fadeIn();
				return;
			}
			
			//Validation Check : directors
			var directors = $("#directors option:selected").val();
			if(directors == undefined) {
				alert("감독을 선택하세요!");
				return;
			}
			
			//Validation Check : actors
			var actors = $("#actors option:selected").val();
			if(actors == undefined) {
				alert("배우를 선택하세요!");
				return;
			}
			
			//Validation Check : genre
			var genres = $(".genres:checked").val();
			if(genres == "") {
				alert("장르를 선택하세요!");
				return;
			}

			var form = $("#addNewMovieForm");
			form.attr("method", "post");
			form.attr("action", "<c:url value="/addNewMovieAction"/>");
			form.submit();
		});
		
	});
	
	
</script>

</head>
<body>

	<h1>영화 등록</h1>
	<hr />

	<span class="errorCode"> ${errorCode}</span>
	
	<form id="addNewMovieForm">
		영 화 명 : <input type="text" id="movieTitle" name="movieTitle" /><br/><br/>
		평점 : <input type="text" id="rate" name="rate" /><br/><br/>
		상영시간 : <input type="text" id="runningTime" name="runningTime" /><br/><br/>
		개봉일 : <input type="date" id="openDate" name="openDate" /><br/><br/>

		<c:forEach items="${ gradeList }" var="grade">
			상영등급 : <input type="radio" class="grade" name="grade" value="${ grade.gradeId }" />${ grade.gradeTitle }
		</c:forEach><br/><br/>
		
		감독 : 
		<select id="directors" name="directors" multiple="multiple">
			<c:forEach items="${ directorList }" var="director">
				<option value="${ director.directorId }">
					${ director.directorName }
				</option>
			</c:forEach>
		</select>
		<span id="directorsName"></span>
		<br/><br/>
		
		출연진
		<select id="actors" name="actors" multiple="multiple">
			<c:forEach items="${ actorList }" var="actor">
				<option value="${ actor.actorId }">
					${ actor.actorName }
				</option>
			</c:forEach>
		</select><br/><br/>
		
		장르 :
		<c:forEach items="${ genreList }" var="genre">
			<input type="checkbox"
					class="genres"
					name="genres"
					value="${ genre.genreId }" />
					${ genre.genreTitle }
		</c:forEach><br/><br/>
		
		<input type="button" id="addNewMovie" value="영화 등록!" />
	</form>

</body>
</html>
