package com.ktds.smahn.article.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.smahn.article.biz.ArticleBiz;
import com.ktds.smahn.article.vo.ArticleVO;
import com.ktds.smahn.favo.biz.FavoriteBiz;
import com.ktds.smahn.favo.vo.FavoriteVO;
import com.ktds.smahn.file.biz.FileBiz;
import com.ktds.smahn.history.biz.OperationHistoryBiz;
import com.ktds.smahn.history.vo.ActionCode;
import com.ktds.smahn.history.vo.BuildDescription;
import com.ktds.smahn.history.vo.Description;
import com.ktds.smahn.history.vo.OperationHistoryVO;
import com.ktds.smahn.member.vo.MemberVO;
import com.sun.corba.se.spi.orbutil.fsm.Action;

/**
 * Servlet implementation class DetailServlet
 */
public class DetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleBiz articleBiz;
	private FileBiz fileBiz;
	private OperationHistoryBiz historyBiz;
	private FavoriteBiz favoriteBiz;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DetailServlet() {
		super();
		articleBiz = new ArticleBiz();
		fileBiz = new FileBiz();
		historyBiz = new OperationHistoryBiz();
		favoriteBiz = new FavoriteBiz();
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

		int articleId = Integer.parseInt(request.getParameter("articleId"));

		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("_MEMBER_");
		
		ArticleVO article = articleBiz.showDetail(articleId);
		
		FavoriteVO favoriteVO = new FavoriteVO();
		favoriteVO.setArticleId(articleId);
		favoriteVO.setMemberId(member.getMemberId());
		
		boolean isExistFavoriteData = favoriteBiz.isExistFavoriteData(favoriteVO);
		
		OperationHistoryVO historyVO = (OperationHistoryVO) request.getAttribute("OperationHistoryVO");
		historyVO.setActionCode(ActionCode.ARTICLE);
		historyVO.setDescription(BuildDescription.get(Description.DETAIL, historyVO.getMemberId(), articleId + ""));
		historyVO.setEtc(BuildDescription.get(Description.DETAIL_DESCRIPTION 
											, article.getTitle()
											, article.getMemberId()
											, article.getDescript()
											)
						);
		historyBiz.addHistory(historyVO);

		request.setAttribute("article", article);
		request.setAttribute("isExistFavoriteData", isExistFavoriteData);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/article/detail.jsp");
		rd.forward(request, response);

	}

}
