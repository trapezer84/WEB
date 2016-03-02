package com.ktds.leinalee.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.leinalee.dao.LocationsDAO;
import com.ktds.leinalee.vo.LocationsVO;

/**
 * Servlet implementation class LocationsServlet
 */
public class LocationsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	LocationsDAO locationDAO;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LocationsServlet() {
        super();
        locationDAO = new LocationsDAO();
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
		
		List<LocationsVO> locations = locationDAO.getAllLocations();
		
		request.setAttribute("allLocations", locations);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/locations.jsp");
		rd.forward(request, response);
		
	}

}
