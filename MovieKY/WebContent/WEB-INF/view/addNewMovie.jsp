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
	});
</script>

</head>
<body>

	<h1>영화 등록</h1>
	<hr />

	<form id="addNewMovieForm">
		영 화 명 : <input type="text" id="movieTitle" name="movieTitle" /><br/><br/>
		평점 : <input type="text" id="rate" name="rate" /><br/><br/>
		상영시간 : <input type="text" id="runningTime" name="runningTime" /><br/><br/>
		개봉일 : <input type="text" id="openDate" name="openDate" /><br/><br/>

		<c:forEach items="${ gradeList }" var="grade">
			상영등급 : <input type="radio" class="grade" name="grade"
				value="${ grade.gradeId }" />
							${ grade.gradeTitle }
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








