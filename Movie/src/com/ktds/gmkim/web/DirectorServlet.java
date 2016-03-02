package com.ktds.gmkim.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.gmkim.dao.DirectorDAO;
import com.ktds.gmkim.vo.DirectorVO;

/**
 * Servlet implementation class DirectorServlet
 */
public class DirectorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private DirectorDAO directorDAO = null;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DirectorServlet() {
        super();
        
        directorDAO = new DirectorDAO();
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
		
		List<DirectorVO> directors = directorDAO.getAllDirectors();
		
		request.setAttribute("allDirectors", directors);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/director.jsp");
		
		rd.forward(request, response);
		
	}

}
