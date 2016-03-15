package com.ktds.leinalee.articles.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.leinalee.articles.biz.ArticlesBiz;
import com.ktds.leinalee.articles.dao.ArticlesDAO;
import com.ktds.leinalee.articles.vo.ArticlesVO;

/**
 * Servlet implementation class RecommendArticle
 */
public class RecommendArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ArticlesBiz articlesBiz;       
	private ArticlesDAO articlesDAO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecommendArticleServlet() {
        super();
        articlesDAO = new ArticlesDAO();
		articlesBiz = new ArticlesBiz();
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
		String articleId = request.getParameter("articleId");
		List<ArticlesVO> articles = articlesDAO.recommendArticle(articleId);
		request.setAttribute("articles", articles);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/articles/details.jsp");
		
		rd.forward(request, response);
	}

}
