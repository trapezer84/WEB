package com.ktds.smahn.member.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.smahn.member.biz.MemberBiz;
import com.ktds.smahn.member.vo.MemberVO;
import com.ktds.smahn.util.Root;

/**
 * Servlet implementation class MemberMassiveDeleteServlet
 */
public class MemberMassiveDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private MemberBiz memberBiz;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberMassiveDeleteServlet() {
        super();
        memberBiz = new MemberBiz();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(HttpServletResponse.SC_FORBIDDEN, "잘 못 된 요청입니다.");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// html에서 radio check 박스에 선택 된 것만 넘어온다. 컬렉션들로 구성된 것들은 모두 배열로 들어온다. 하나만 선택되더라도
		String[] deleteMemberIds = request.getParameterValues("deleteMemberId");
		
		
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("_MEMBER_");
		
		memberBiz.deleteMembers(deleteMemberIds, member);
		
		response.sendRedirect(Root.get(this) + "/memberList");
	}

}
