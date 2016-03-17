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
import com.ktds.smahn.history.biz.OperationHistoryBiz;
import com.ktds.smahn.history.vo.ActionCode;
import com.ktds.smahn.history.vo.BuildDescription;
import com.ktds.smahn.history.vo.Description;
import com.ktds.smahn.history.vo.OperationHistoryVO;
import com.ktds.smahn.member.vo.MemberVO;

/**
 * Servlet implementation class ListServlet
 */
public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleBiz articleBiz;
	private OperationHistoryBiz historyBiz;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListServlet() {
		super();
		articleBiz = new ArticleBiz();
		historyBiz = new OperationHistoryBiz();
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

		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("_MEMBER_");
		
		int pageNO = 0;

		OperationHistoryVO historyVO = new OperationHistoryVO();
		historyVO.setIp(request.getRemoteHost());
		historyVO.setMemberId(member.getMemberId());
		historyVO.setUrl(request.getRequestURI());
		historyVO.setActionCode(ActionCode.ARTICLE);
		
		try {
			pageNO = Integer.parseInt(request.getParameter("pageNO"));
			
			historyVO.setDescription( BuildDescription.get	(
																Description.LIST_PAGING, member.getMemberId(), pageNO+""
															)
					);
		} catch (NumberFormatException nfe) {
			// catch에 걸렸다는 의미는 list 페이지에서 가장 첫번째로 접근했다는 것 

			historyVO.setDescription( BuildDescription.get	(
																Description.LIST, member.getMemberId()
															)
					);
		}
		
		historyBiz.addHistory(historyVO);

		ArticleSearchVO searchVO = new ArticleSearchVO();
		searchVO.setPageNO(pageNO);

		ArticleListVO articles = articleBiz.getArticleList(searchVO);

		request.setAttribute("articles", articles);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/article/list.jsp");
		rd.forward(request, response);
	}

}
