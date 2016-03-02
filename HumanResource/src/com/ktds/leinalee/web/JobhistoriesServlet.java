package com.ktds.leinalee.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.leinalee.dao.JobHistoryDAO;
import com.ktds.leinalee.vo.JobHistoryVO;

/**
 * Servlet implementation class JobhistoriesServlet
 */
public class JobhistoriesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	JobHistoryDAO jobHisotryDAO;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JobhistoriesServlet() {
        super();
        jobHisotryDAO = new JobHistoryDAO();
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
		List<JobHistoryVO> jobHistory = jobHisotryDAO.getAllJobHistory();
		
		request.setAttribute("getAllJobHistory", jobHistory);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/jh.jsp");
		rd.forward(request, response);
	}

}
