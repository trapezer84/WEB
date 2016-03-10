package com.ktds.smahn.article.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.smahn.article.biz.ArticleBiz;
import com.ktds.smahn.article.vo.ArticleVO;
import com.ktds.smahn.member.vo.MemberVO;

/**
 * Servlet implementation class DeleteServlet
 */
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ArticleBiz articleBiz;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
        super();
        articleBiz = new ArticleBiz();
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
		int articleId = Integer.parseInt(request.getParameter("articleId"));
		
		//내가 지우려는 글이 내가 쓴 글이 맞는지 확인하기 위해 2개 확인
		//1. 세션의 정보 
		HttpSession session = request.getSession();
		MemberVO loginMember = (MemberVO) session.getAttribute("_MEMBER_");
		//2. article에 들어있는 memberId를 소환 
		ArticleVO targetArticleInfo = articleBiz.showDetail(articleId);
		
		//로그인한 사용자와 삭제하는 글을 작성한 사용자가 같은지 비교한 후 
		articleBiz.isEqual(loginMember, targetArticleInfo);
		
		//같다면 글을 삭제한다. 
		//다를 경우에는 삭제하지 않는다
		response.sendRedirect("/list");
	}

}
