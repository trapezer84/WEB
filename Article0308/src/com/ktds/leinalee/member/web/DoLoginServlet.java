package com.ktds.leinalee.member.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.leinalee.member.biz.MemberBiz;
import com.ktds.leinalee.member.vo.MemberVO;


/**
 * Servlet implementation class DoLoginServlet
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
		// 로그인 성공 여부만 확인한 뒤, Redirect로 이동하기 때문에 Get으로 넘어오는 것을 막는다.
		response.sendError(HttpServletResponse.SC_FORBIDDEN, "로그인할 수 없습니다.");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// ID, PW를 받아온다.
		String memberId = request.getParameter("userId");
		String memberPassword = request.getParameter("userPw");
		
		// loginMember VO에 입력한다.
		MemberVO loginMember = new MemberVO();
		loginMember.setMemberId(memberId);
		loginMember.setPassword(memberPassword);
		
		// 로그인 성공 여부를 받아온다.
		boolean isLoginSuccess = memberBiz.login(loginMember, request);
		
		// 로그인이 성공했다면
		if ( isLoginSuccess ) {
			response.sendRedirect("/list");
			return;
		}
		// 로그인이 실패했다면
		else {
			response.sendRedirect("/?errorCode=1");
			return;
		}
	}
}




