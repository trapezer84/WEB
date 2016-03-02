package com.ktds.leinalee.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.leinalee.dao.ActorDAO;
import com.ktds.leinalee.dao.DirectorDAO;
import com.ktds.leinalee.dao.MovieDAO;
import com.ktds.leinalee.vo.ActorVO;
import com.ktds.leinalee.vo.DirectorVO;
import com.ktds.leinalee.vo.MovieVO;

/**
 * Servlet implementation class DetailServlet
 */
public class DetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	private MovieDAO movieDAO;
	private DirectorDAO directorDAO;
	private ActorDAO actorDAO;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailServlet() {
        super();
        movieDAO = new MovieDAO();
        directorDAO = new DirectorDAO();
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
		int movieId = Integer.parseInt(request.getParameter("movieId"));
		
		//영화 정보 가져옴
		MovieVO movie = movieDAO.getOneMovieInfoByMovieId(movieId);
		request.setAttribute("movie", movie);
		
		//감독 정보 가져옴
		List<DirectorVO> directors = directorDAO.getOneDirectorDAObyMovieId(movieId);
		request.setAttribute("directors", directors);
		
		//출연진 정보 가져옴
		List<ActorVO> actors = actorDAO.getOActorDAObyMovieId(movieId);
		request.setAttribute("actors", actors);
		//장르 정보 가져옴
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/detail.jsp");
		rd.forward(request, response);
	}

}
