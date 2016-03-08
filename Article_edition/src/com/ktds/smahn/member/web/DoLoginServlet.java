package com.ktds.smahn.member.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.smahn.member.biz.MemberBiz;
import com.ktds.smahn.member.vo.MemberVO;

/**
 * Servlet implementation class DoLoginServlet
 * 로그인 버튼 눌렀을 때 처리하는 서블릿
 */
public class DoLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MemberBiz memberBiz;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoLoginServlet() {
        super();
        memberBiz = new MemberBiz();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(HttpServletResponse.SC_FORBIDDEN, "로그인 할 수 없습니다.");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String memberId = request.getParameter("userId");
		String memberPassword = request.getParameter("userPw");
		
		MemberVO loginMember = new MemberVO();
		loginMember.setMemberId(memberId);
		loginMember.setPassword(memberPassword);
		
		boolean isLoginSuccess = memberBiz.login(loginMember, request);
		
		// 로그인 성공 여부에 따라 다음 페이지를 보여줄 것인지, 에러코드와 함께 그 페이지에 머물를것인지를 선택한다.
		if ( isLoginSuccess ) {
			response.sendRedirect("/list");
			return;
		}
		else {
			response.sendRedirect("/login?errorCode=1");
			return;
		}
		
	}

}
