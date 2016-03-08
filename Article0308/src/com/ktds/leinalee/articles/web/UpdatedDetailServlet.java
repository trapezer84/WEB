package com.ktds.leinalee.articles.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.leinalee.articles.biz.ArticlesBiz;
import com.ktds.leinalee.articles.dao.DetailDAO;
import com.ktds.leinalee.articles.dao.UpdatedDetailDAO;
import com.ktds.leinalee.articles.vo.ArticlesVO;

/**
 * Servlet implementation class UpdatedDetailServlet
 */
public class UpdatedDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ArticlesBiz articlesBiz;
	private UpdatedDetailDAO updatedDetailDAO;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatedDetailServlet() {
        super();
		articlesBiz = new ArticlesBiz();        
		updatedDetailDAO = new UpdatedDetailDAO();
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
		ArticlesVO articles = updatedDetailDAO.getArticleByArticleTitle(articleId);
		request.setAttribute("articles", articles);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/articles/details.jsp");
		
		rd.forward(request, response);
	}

}
