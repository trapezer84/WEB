package com.ktds.leinalee.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.leinalee.dao.MovieDAO;
import com.ktds.leinalee.vo.MovieVO;

/**
 * Servlet implementation class MovieServlet
 */
public class MovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	MovieDAO movieDAO; 
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
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<MovieVO> movies = movieDAO.getAllMovie();
		
		request.setAttribute("allMovies", movies);
		RequestDispatcher rs = request.getRequestDispatcher("/WEB-INF/view/movie.jsp");
		rs.forward(request, response);
		
		
	}

}
