package com.ktds.gmkim.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddNewActorServlet
 */
public class AddNewActorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddNewActorServlet() {
        super();
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
		
		String errorCode = request.getParameter("errorCode");
		if( errorCode != null && errorCode.equals("1")) {
			request.setAttribute("ErrorMessage",  "배우명을 등록하세요.");
		}
		else if ( errorCode != null && errorCode.equals("2")) {
			request.setAttribute("ErrorMessage", "등록에 실패했습니다.");
		}
		
		// 단순 jsp를 보여주는것도 Servlet을 통과한다.
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/addNewActor.jsp");
		
		rd.forward(request, response);
	}

}
