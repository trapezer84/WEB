package com.ktds.smahn.article.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.smahn.article.biz.ArticleBiz;
import com.ktds.smahn.util.Root;
	
/**
 * Servlet implementation class increaseRecommendsServlet
 */
public class IncreaseRecommendsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private ArticleBiz articleBiz;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IncreaseRecommendsServlet() {
        super();
        articleBiz = new ArticleBiz();
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

		int articleId = Integer.parseInt(request.getParameter("articleId"));
			
		articleBiz.increaseRecommends(articleId);
		
		response.sendRedirect(Root.get(this) + "/detail?articleId=" + articleId);
		
	}

}
