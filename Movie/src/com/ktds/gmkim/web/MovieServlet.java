package com.ktds.gmkim.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.gmkim.dao.MovieDAO;
import com.ktds.gmkim.vo.MovieVO;

/**
 * Servlet implementation class MovieServlet
 */
public class MovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	MovieDAO movieDAO = null;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MovieServlet() {
        super();
        
        movieDAO = new MovieDAO();
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
		
		List<MovieVO> movies = movieDAO.getAllMovies();
		
		request.setAttribute("allMovies", movies);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/movie.jsp");
		
		rd.forward(request, response);
	}

}
