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
import com.ktds.leinalee.articles.dao.DetailDAO;
import com.ktds.leinalee.articles.vo.ArticlesVO;

/**
 * Servlet implementation class detailServlet
 */
public class DetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ArticlesBiz articlesBiz;
	private DetailDAO detailDAO;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailServlet() {
        super();
		articlesBiz = new ArticlesBiz();        
		detailDAO = new DetailDAO();
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
		
		String articleId = request.getParameter("articleId");
		ArticlesVO articles = detailDAO.getArticleByArticleTitle(articleId);
		request.setAttribute("articles", articles);
		
		
//		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/articles/details.jsp");
//		rd.forward(request, response);
		
		response.sendRedirect("/updated?articleId="+ articleId);
		
	}

}
