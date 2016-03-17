package com.ktds.smahn.member.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.smahn.article.vo.ArticleVO;
import com.ktds.smahn.member.biz.MemberBiz;
import com.ktds.smahn.member.vo.MemberVO;
import com.ktds.smahn.util.Root;

/**
 * Servlet implementation class ModifyMemberInfoServlet
 */
public class ModifyMemberInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MemberBiz memberBiz;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModifyMemberInfoServlet() {
		super();
		memberBiz = new MemberBiz();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// update를 하는 코드를 거의 정해져있다.

		// 1. 어떠한 글을 수정할 것인지 정해야 한다.
		// 1-1. 수정하고자 하는 글의 ID를 파라미터로 받아와야 한다.
		String memberId = request.getParameter("memberId");

		// 2. 수정하고자 하는 글의 정보를 가져와야 한다.
		MemberVO member = memberBiz.showDetail(memberId);

//		String nickName = member.getNickName();
//		String newPassword;
//		String email = member.getEmail();

		request.setAttribute("member", member);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/member/editMemberInfo.jsp");
		rd.forward(request, response);
		

	}

}
