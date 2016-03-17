package com.ktds.smahn.article.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.smahn.article.biz.ArticleBiz;
import com.ktds.smahn.article.vo.ArticleListVO;
import com.ktds.smahn.article.vo.ArticleSearchVO;

/**
 * Servlet implementation class ListServlet
 */
public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleBiz articleBiz;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListServlet() {
		super();
		articleBiz = new ArticleBiz();
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

		int pageNO = 0;
		ArticleSearchVO searchVO = new ArticleSearchVO();
		HttpSession session = request.getSession();
		
		try {
			// 데이터가 없다면 page는 null 그렇기 때문에 numberFormatException발생..? 그래서 catch에다가 search session을 이용한다라..?
			pageNO = Integer.parseInt(request.getParameter("pageNO"));
			
			// 검색 종류 및 키워드 가져오기
			searchVO.setSearchList(request.getParameter("searchList"));
			searchVO.setSearchKeyword(request.getParameter("searchKeyword"));

			// 정상적일 때만 pageNO을 설정하도록 한다. 
			searchVO.setPageNO(pageNO);
			
		} catch (NumberFormatException nfe) {
			// 그런데 이 searchVO도 null인 경우가 있다.
			searchVO = (ArticleSearchVO) session.getAttribute("_SEARCH_");
			
			// 그러면 다시 0으로 맞춘다.
			if (searchVO == null) {
				searchVO = new ArticleSearchVO();
				searchVO.setPageNO(0);
				// 그리고 Keyword를 공백으로 맞춘다.
				searchVO.setSearchKeyword("");
			}
			
		}
		
		System.out.println(searchVO.getSearchList());

		// search를 session에 넣는다. session 정보로 detail을 본다음 다시 목록보기로 돌아가기 위해서
		// session은 메모리가 허용하는 곳 까지 모두 저장할 수 있다.
		session.setAttribute("_SEARCH_", searchVO);
		
		
		// search list에 따라서 다르게 article list를 받아오도록 설정해줘야한다. 
		ArticleListVO articles = articleBiz.getArticleList(searchVO);

		request.setAttribute("articles", articles);
		request.setAttribute("searchVO", searchVO);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/article/list.jsp");
		rd.forward(request, response);
	}

}
