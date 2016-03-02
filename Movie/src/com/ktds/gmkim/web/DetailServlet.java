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
import com.ktds.gmkim.dao.MovieDAO;
import com.ktds.gmkim.vo.ActorVO;
import com.ktds.gmkim.vo.DirectorVO;
import com.ktds.gmkim.vo.GenreVO;
import com.ktds.gmkim.vo.MovieVO;

/**
 * Servlet implementation class DetailServlet
 */
public class DetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private MovieDAO movieDAO;
	private DirectorDAO directorDAO;
	private ActorDAO actorDAO;
	private GenreDAO genreDAO;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailServlet() {
        super();
        
        movieDAO = new MovieDAO();
        directorDAO = new DirectorDAO();
        actorDAO = new ActorDAO();
        genreDAO = new GenreDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int movieId = Integer.parseInt(request.getParameter("movieId"));
		
		// 영화 정보 가져옴
		MovieVO movieVO = movieDAO.getOneMovieInfoByMovieId(movieId);
		
		// 감독 정보 가져옴
		List<DirectorVO> directors = directorDAO.getDirectorsByMovieId(movieId);
		
		// 출연진 정보 가져옴
		List<ActorVO> actors = actorDAO.getActorsByMovieId(movieId);
		
		// 장르 정보 가져옴
		List<GenreVO> genres = genreDAO.getGenresByMovieId(movieId);
		
		request.setAttribute("movieVO", movieVO );
		request.setAttribute("directors", directors );
		request.setAttribute("actors", actors );
		request.setAttribute("genres", genres );
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/details.jsp");
		
		rd.forward(request, response);
	}

}
