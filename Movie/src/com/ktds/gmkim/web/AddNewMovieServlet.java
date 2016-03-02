package com.ktds.gmkim.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.gmkim.dao.ActorDAO;
import com.ktds.gmkim.dao.DirectorDAO;
import com.ktds.gmkim.dao.GenreDAO;
import com.ktds.gmkim.dao.GradeDAO;
import com.ktds.gmkim.dao.MovieValidateConst;
import com.ktds.gmkim.vo.ActorVO;
import com.ktds.gmkim.vo.DirectorVO;
import com.ktds.gmkim.vo.GenreVO;
import com.ktds.gmkim.vo.GradeVO;

/**
 * Servlet implementation class AddNewMovieServlet
 */
public class AddNewMovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private GenreDAO genreDAO;
	private GradeDAO gradeDAO;
	private DirectorDAO directorDAO;
	private ActorDAO actorDAO;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddNewMovieServlet() {
        super();
        genreDAO = new GenreDAO();
        gradeDAO = new GradeDAO();
        directorDAO = new DirectorDAO();
        actorDAO = new ActorDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String errorCode = request.getParameter("errorCode");
		
		//삼항 연산자 
		errorCode = (errorCode == null ? "" : errorCode);
		
		if (errorCode.equals(MovieValidateConst.MISSING_MOVIE_TITLE)){
			request.setAttribute("errorMessage", "제목을 입력하세요");
		} else if (errorCode.equals(MovieValidateConst.MISSING_ACTORS)) {
			request.setAttribute("errorMessage", "출연진을 선택하세요");
		} else if (errorCode.equals(MovieValidateConst.MISSING_DIRECTORS)) {
			request.setAttribute("errorMessage", "감독을 선택하세요");
		} else if (errorCode.equals(MovieValidateConst.MISSING_GENRES)) {
			request.setAttribute("errorMessage", "장르를 선택하세요");
		} else if (errorCode.equals(MovieValidateConst.MISSING_GRADE)) {
			request.setAttribute("errorMessage", "상영등급을 선택하세요");
		} else if (errorCode.equals(MovieValidateConst.MISSING_OPEN_DATE)) {
			request.setAttribute("errorMessage", "개봉일을 입력하세요");
		} else if (errorCode.equals(MovieValidateConst.MISSING_RATE)) {
			request.setAttribute("errorMessage", "평점을 입력하세요");
		} else if (errorCode.equals(MovieValidateConst.MISSING_RUNNING_TIME)) {
			request.setAttribute("errorMessage", "상영시간을 입력하세요");
		} 
		
		// 1. 장르 리스트
		List<GenreVO> genreList = genreDAO.getAllGenres();
		
		// 2. 등급 리스트
		List<GradeVO> gradeList = gradeDAO.getAllGrades();
		
		// 3. 감독 리스트
		List<DirectorVO> directorList = directorDAO.getAllDirectors();
		
		// 4. 배우 리스트
		List<ActorVO> actorList = actorDAO.getAllActors();
		
		request.setAttribute("genreList",  genreList);
		request.setAttribute("gradeList",  gradeList);
		request.setAttribute("directorList",  directorList);
		request.setAttribute("actorList",  actorList);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/addNewMovie.jsp");
		rd.forward(request, response);
		
	}

}
