package com.ktds.leinalee.articles.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.leinalee.articles.biz.ArticleBiz;
import com.ktds.leinalee.articles.vo.ArticleVO;
import com.ktds.leinalee.files.biz.FileBiz;
import com.ktds.leinalee.members.vo.MemberVO;

/**
 * Servlet implementation class DeleteServlet
 */
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ArticleBiz articleBiz;
	private FileBiz fileBiz;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
        super();
        articleBiz = new ArticleBiz();
        fileBiz = new FileBiz();
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
		//같다면 글을 삭제한다. 
		//다를 경우에는 삭제하지 않는다
		articleBiz.isEqual(loginMember, targetArticleInfo);
		//파일도 함께 삭제 
		fileBiz.deleteFile(articleId);
		
		response.sendRedirect("/list");
	}

}
