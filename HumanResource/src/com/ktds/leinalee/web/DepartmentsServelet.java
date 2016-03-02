package com.ktds.leinalee.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.leinalee.dao.DepartmentsDAO;
import com.ktds.leinalee.vo.DepartmentsVO;

/**
 * Servlet implementation class DepartmentsServelet
 */
public class DepartmentsServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private DepartmentsDAO departmentsDAO;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DepartmentsServelet() {
        super();
        departmentsDAO = new DepartmentsDAO();
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
	
		List<DepartmentsVO> departments = departmentsDAO.getAllDepartments();
		
		request.setAttribute("allDepartments", departments);
		
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/view/dpt.jsp");
		rd.forward(request, response);
		
	}

}
