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
 * Servlet implementation class ListActionServlet
 */
public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ArticlesBiz articlesBiz;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListServlet() {
		super();

		articlesBiz = new ArticlesBiz();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<ArticlesVO> articleList = articlesBiz.getArticleList(request);

		// article 정보 가져오기
		request.setAttribute("articleList", articleList);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/articles/list.jsp");
		rd.forward(request, response);
		
	}

}
