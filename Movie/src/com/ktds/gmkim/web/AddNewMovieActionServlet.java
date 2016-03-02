package com.ktds.gmkim.web;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.gmkim.dao.ActorDAO;
import com.ktds.gmkim.dao.DirectorDAO;
import com.ktds.gmkim.dao.GenreDAO;
import com.ktds.gmkim.dao.GradeDAO;
import com.ktds.gmkim.dao.MovieDAO;
import com.ktds.gmkim.dao.MovieValidateConst;
import com.ktds.gmkim.vo.ActorVO;
import com.ktds.gmkim.vo.DirectorVO;
import com.ktds.gmkim.vo.GenreVO;
import com.ktds.gmkim.vo.GradeVO;
import com.ktds.gmkim.vo.MovieVO;

/**
 * Servlet implementation class AddNewMovieAction
 */
public class AddNewMovieActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private MovieDAO movieDAO;
	private GenreDAO genreDAO;
	private ActorDAO actorDAO;
	private DirectorDAO directorDAO;
	private GradeDAO gradeDAO;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddNewMovieActionServlet() {
        super();
        movieDAO = new MovieDAO();
        genreDAO = new GenreDAO();
        actorDAO = new ActorDAO();
        directorDAO = new DirectorDAO();
        gradeDAO = new GradeDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//form get으로 들어오는 것들은 죄다 막아주는 것임..! post일 때만 처리해준다는 의미
		response.sendError(HttpServletResponse.SC_FORBIDDEN, "잘 못 된 요청입니다.");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String movieTitle = request.getParameter("movieTitle");
		String rate = request.getParameter("rate");
		String runningTime = request.getParameter("runningTime");
		String openDate = request.getParameter("openDate");
		String grade = request.getParameter("grade");
		
		List<String> directors = Arrays.asList(request.getParameterValues("directors"));
		List<String> actors = Arrays.asList(request.getParameterValues("actors"));
		List<String> genres = Arrays.asList(request.getParameterValues("genres"));
		
		if( movieTitle == null || movieTitle.length() == 0) {
			//들어온 것이 없기 때문에 다시 재 입력하라는 페이지로 돌아가라고 말한다. 
			response.sendRedirect("/Movie/addNewMovie?errorCode="
					+ MovieValidateConst.MISSING_MOVIE_TITLE);
			return;
		}
		
		if( rate == null || rate.length() == 0) {
			//들어온 것이 없기 때문에 다시 재 입력하라는 페이지로 돌아가라고 말한다. 
			response.sendRedirect("/Movie/addNewMovie?errorCode="
					+ MovieValidateConst.MISSING_RATE);
			return;
		}
		//rate가 숫자가 아닐 때, try catch 로 잡은 다음 다시 적어라고 알려주는 code.. 
		try {
			double ratePont = Double.parseDouble(rate);
		} 
		catch (NumberFormatException nfe) {
			//들어온 것이 없기 때문에 다시 재 입력하라는 페이지로 돌아가라고 말한다. 
			response.sendRedirect("/Movie/addNewMovie?errorCode="
					+ MovieValidateConst.MISSING_RATE);
			return;
		}
		
		//runningTime 
		if( runningTime == null || runningTime.length() == 0) {
			//들어온 것이 없기 때문에 다시 재 입력하라는 페이지로 돌아가라고 말한다. 
			response.sendRedirect("/Movie/addNewMovie?errorCode="
					+ MovieValidateConst.MISSING_RUNNING_TIME);
			return;
		} 
		if( runningTime.length() > 5 ) {
			response.sendRedirect("/Movie/addNewMovie?errorCode="
					+ MovieValidateConst.MISSING_RUNNING_TIME);
		}
		Pattern p = Pattern.compile("^[0-2]{0,1}[0-9]{1}:[0-5][0-9]$");
		Matcher m = p.matcher(runningTime);
		if ( ! m.matches() ) {
			response.sendRedirect("/Movie/addNewMovie?errorCode="
					+ MovieValidateConst.MISSING_RUNNING_TIME);
			return;
		}
		
		if( openDate == null || openDate.length() == 0) {
			//들어온 것이 없기 때문에 다시 재 입력하라는 페이지로 돌아가라고 말한다. 
			response.sendRedirect("/Movie/addNewMovie?errorCode="
					+ MovieValidateConst.MISSING_OPEN_DATE);
			return;
		}
		
		if( grade == null || grade.length() == 0) {
			//들어온 것이 없기 때문에 다시 재 입력하라는 페이지로 돌아가라고 말한다. 
			response.sendRedirect("/Movie/addNewMovie?errorCode="
					+ MovieValidateConst.MISSING_GRADE);
			return;
		}
		
		if( directors == null || directors.size() == 0) {
			//들어온 것이 없기 때문에 다시 재 입력하라는 페이지로 돌아가라고 말한다. 
			response.sendRedirect("/Movie/addNewMovie?errorCode="
					+ MovieValidateConst.MISSING_DIRECTORS);
			return;
		}
		
		if( actors == null || actors.size() == 0) {
			//들어온 것이 없기 때문에 다시 재 입력하라는 페이지로 돌아가라고 말한다. 
			response.sendRedirect("/Movie/addNewMovie?errorCode="
					+ MovieValidateConst.MISSING_ACTORS);
			return;
		}
		
		if( genres == null || genres.size() == 0) {
			//들어온 것이 없기 때문에 다시 재 입력하라는 페이지로 돌아가라고 말한다. 
			response.sendRedirect("/Movie/addNewMovie?errorCode="
					+ MovieValidateConst.MISSING_GENRES);
			return;
		}
		
		MovieVO movie = new MovieVO();
		movie.setTitle(movieTitle);
		movie.setRate(Double.parseDouble(rate));
		movie.setRunningTime(runningTime);
		movie.setOpenDate(openDate);
		movie.setGradeId(Integer.parseInt(grade));
				
		int newMovieId = movieDAO.insertNewMovie(movie);
		
		if (newMovieId > 0) {
			GenreVO genre = new GenreVO();
			genre.setMovieId(newMovieId);
			for (String selectedGenre : genres) {
				genre.setGenreId(Integer.parseInt(selectedGenre));
				genreDAO.insertNewGenreOfNewMovie(genre);
			}
			
			ActorVO actor = new ActorVO();
			actor.setMovieId(newMovieId);
			for (String selectedActor : actors) {
				actor.setActorId(Integer.parseInt(selectedActor));
				actorDAO.insertNewActorOfNewMovie(actor);
			}
			
			DirectorVO director = new DirectorVO();
			director.setMovieId(newMovieId);
			for (String selectedDirector : directors) {
				director.setDirectorId(Integer.parseInt(selectedDirector));
				directorDAO.insertNewDirectorOfNewMovie(director);
			}
			
		}
		
		
		
	}

}
