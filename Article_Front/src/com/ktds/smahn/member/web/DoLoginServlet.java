package com.ktds.smahn.member.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.smahn.history.biz.OperationHistoryBiz;
import com.ktds.smahn.history.vo.ActionCode;
import com.ktds.smahn.history.vo.BuildDescription;
import com.ktds.smahn.history.vo.Description;
import com.ktds.smahn.history.vo.OperationHistoryVO;
import com.ktds.smahn.member.biz.MemberBiz;
import com.ktds.smahn.member.vo.MemberVO;

/**
 * Servlet implementation class DoLoginServlet
 * 로그인 버튼 눌렀을 때 처리하는 서블릿
 */
public class DoLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MemberBiz memberBiz;
    private OperationHistoryBiz historyBiz;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoLoginServlet() {
        super();
        memberBiz = new MemberBiz();
        historyBiz = new OperationHistoryBiz();
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
		// 로그인 성공
		if ( isLoginSuccess ) {
			
			// 자동로그인 기능
			Cookie autoLogin = new Cookie("autoLogin", "true");
//			autoLogin.setMaxAge(999);
			autoLogin.setMaxAge(0); //autoLogin을 지우는 방법
			response.addCookie(autoLogin);
			
			// 쿠키에 userId && password를 입력
			Cookie userId = new Cookie("userId", memberId);
			userId.setMaxAge(999);
			response.addCookie(userId);
			
			Cookie userPassword = new Cookie("userPassword", memberPassword);
			userPassword.setMaxAge(999);
			response.addCookie(userPassword);
			
			OperationHistoryVO historyVO = new OperationHistoryVO();
			historyVO.setIp(request.getRemoteHost());
			historyVO.setMemberId(memberId);
			historyVO.setUrl(request.getRequestURI());
			historyVO.setActionCode(ActionCode.LOGIN);
			historyVO.setDescription( BuildDescription.get	(
																Description.LOGIN, memberId
															)
									);
			historyBiz.addHistory(historyVO);
			
			response.sendRedirect("/list");
			return;
		}
		// 로그인 실패
		else {
			
			OperationHistoryVO historyVO = new OperationHistoryVO();
			historyVO.setIp(request.getRemoteHost());
			historyVO.setMemberId(memberId);
			historyVO.setUrl(request.getRequestURI());
			historyVO.setActionCode(ActionCode.LOGIN);
			historyVO.setDescription( BuildDescription.get	(
																Description.LOGIN_FAIL, request.getRemoteHost(), memberId
															)
									);
			historyBiz.addHistory(historyVO);
			
			response.sendRedirect("/login?errorCode=1");
			return;
		}
		
	}

}
