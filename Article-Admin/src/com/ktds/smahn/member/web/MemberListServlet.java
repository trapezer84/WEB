package com.ktds.smahn.member.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.smahn.member.biz.MemberBiz;
import com.ktds.smahn.member.vo.MemberListVO;
import com.ktds.smahn.member.vo.MemberSearchVO;
import com.ktds.smahn.member.vo.MemberVO;

/**
 * Servlet implementation class MemberListServlet
 */
public class MemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	private MemberBiz memberBiz;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberListServlet() {
        super();
        memberBiz = new MemberBiz();
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
		
		int pageNO = 0;

		try {
			pageNO = Integer.parseInt(request.getParameter("pageNO"));
		} catch (NumberFormatException nfe) {
		}
		
		MemberSearchVO searchVO = new MemberSearchVO();
		searchVO.setPageNO(pageNO);
		
		MemberListVO members = memberBiz.getMemberList(searchVO);
		
		request.setAttribute("members", members);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/member/memberList.jsp");
		rd.forward(request, response);
	}

}
