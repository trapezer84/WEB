package com.ktds.smahn.article.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.smahn.article.biz.ArticleBiz;
import com.ktds.smahn.article.vo.ArticleVO;
import com.ktds.smahn.file.biz.FileBiz;
import com.ktds.smahn.file.vo.FileVO;

/**
 * Servlet implementation class DetailServlet
 */
public class DetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ArticleBiz articleBiz;
    private FileBiz fileBiz;
   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailServlet() {
        super();
        articleBiz = new ArticleBiz();
        fileBiz = new FileBiz();
        
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

		ArticleVO article = articleBiz.showDetail(articleId);
		
		request.setAttribute("article", article);
	    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/article/detail.jsp");
	    rd.forward(request, response);
		
		
		
	}

}
