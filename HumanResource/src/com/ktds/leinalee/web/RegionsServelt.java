package com.ktds.leinalee.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.leinalee.dao.RegionsDAO;
import com.ktds.leinalee.vo.RegionsVO;

/**
 * Servlet implementation class RegionsServelt
 */
public class RegionsServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	RegionsDAO regionsDAO = new RegionsDAO();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegionsServelt() {
        super();
        // TODO Auto-generated constructor stub
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
		List<RegionsVO> regions = regionsDAO.getAllRegions();
		
		request.setAttribute("allRegions", regions);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/rs.jsp");
		
		rd.forward(request, response);
		
	}

}
