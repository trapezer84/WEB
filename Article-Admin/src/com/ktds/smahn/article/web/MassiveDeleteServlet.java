package com.ktds.smahn.article.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.smahn.article.biz.ArticleBiz;
import com.ktds.smahn.member.vo.MemberVO;
import com.ktds.smahn.util.Root;

/**
 * Servlet implementation class MassiveDeleteServlet
 */
public class MassiveDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ArticleBiz articleBiz;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MassiveDeleteServlet() {
        super();
        articleBiz = new ArticleBiz();
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
		String[] deleteArticleIds = request.getParameterValues("deleteArticleId");
		
		
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("_MEMBER_");
		
		articleBiz.deleteArticles(deleteArticleIds, member);
		
		response.sendRedirect(Root.get(this) + "/list");
		
	}

}
