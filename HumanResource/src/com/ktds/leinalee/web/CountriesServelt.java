package com.ktds.leinalee.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.leinalee.dao.CountriesDAO;
import com.ktds.leinalee.vo.CountriesVO;

/**
 * Servlet implementation class CountriesServelt
 */
public class CountriesServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	CountriesDAO countriesDAO = new CountriesDAO();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CountriesServelt() {
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
		List<CountriesVO> countries = countriesDAO.getAllCountries();
		
		request.setAttribute("allCountries", countries);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/ctr.jsp");
		rd.forward(request, response);
		
	}

}
