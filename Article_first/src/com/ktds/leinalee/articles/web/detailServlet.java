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
 * Servlet implementation class detailServlet
 */
public class detailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ArticlesBiz articlesBiz;
	private ArticlesDAO articlesDAO;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public detailServlet() {
        super();
		articlesBiz = new ArticlesBiz();        
        articlesDAO = new ArticlesDAO();
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
		List<ArticlesVO> articles = articlesDAO.getArticleByArticleTitle(articleId);
		request.setAttribute("articles", articles);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/articles/details.jsp");
		
		rd.forward(request, response);
////		---------------------
//		String title = request.getParameter("title");
//		List<ArticlesVO> article = articlesBiz.getArticle(request, title);
//		
//		request.setAttribute("article", article);
//		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/articles/details.jsp");
//		rd.forward(request, response);
		
//		--------------------
//		List<ArticlesVO> articleList = articlesBiz.getArticleList(request);
//
//		// article 정보 가져오기
//		request.setAttribute("articleList", articleList);
//
//		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/articles/list.jsp");
//		rd.forward(request, response);
		
	}

}
