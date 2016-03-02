package com.ktds.leinalee.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.leinalee.dao.GradeDAO;
import com.ktds.leinalee.vo.GradeVO;

/**
 * Servlet implementation class GradeServlet
 */
public class GradeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	GradeDAO gradeDAO = new GradeDAO();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GradeServlet() {
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
		List<GradeVO> grades = gradeDAO.getAllGrade();
		request.setAttribute("allGrades", grades);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/grade.jsp");
		rd.forward(request, response);
		
	}

}
