package com.ktds.leinalee.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.leinalee.dao.GenreMovieCountDAO;
import com.ktds.leinalee.vo.GenreMovieCountVO;

/**
 * Servlet implementation class GenreMovieCountServlet
 */
public class GenreMovieCountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	GenreMovieCountDAO movieDAO = new GenreMovieCountDAO();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GenreMovieCountServlet() {
        super();
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
		List<GenreMovieCountVO> movies = movieDAO.getAllGenreMovieCount();
		
		request.setAttribute("allGenreMovieCount", movies);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/gmcount.jsp");
		rd.forward(request, response);
		
	}

}
