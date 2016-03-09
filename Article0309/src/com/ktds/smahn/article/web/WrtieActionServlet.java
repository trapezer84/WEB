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
 * Servlet implementation class WrtieActionServlet
 */
public class WrtieActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ArticleBiz articleBiz;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WrtieActionServlet() {
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
		

		//5. Biz에서 정보 처리 
		articleBiz.addNewArticle(request);
		
		//6. 보내주기
		response.sendRedirect("/list");
		
//		boolean insertResult = actorDAO.addNewActor(actorName);
//		if ( insertResult ) {
//			response.sendRedirect("/Movie/movie");
//			
//		}
//		else {
//			response.sendRedirect("/Movie/addNewActor?errorCode=2");
//		}
	}

}
