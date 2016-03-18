package com.ktds.smahn.favo.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.smahn.favo.biz.FavoriteBiz;
import com.ktds.smahn.favo.vo.FavoriteVO;
import com.ktds.smahn.history.biz.OperationHistoryBiz;
import com.ktds.smahn.history.vo.OperationHistoryVO;
import com.ktds.smahn.member.vo.MemberVO;

/**
 * Servlet implementation class FavoriteServlet
 */
public class FavoriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FavoriteBiz favoriteBiz;
	private OperationHistoryBiz historyBiz;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FavoriteServlet() {
		super();
		favoriteBiz = new FavoriteBiz();
		historyBiz = new OperationHistoryBiz();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendError(HttpServletResponse.SC_FORBIDDEN, "잘못된 요청입니다.");
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

		FavoriteVO favoriteVO = new FavoriteVO();
		favoriteVO.setArticleId(articleId);
		favoriteVO.setMemberId(member.getMemberId());
		favoriteBiz.insertOrDeleteFavoriteData(favoriteVO);

		OperationHistoryVO historyVO = (OperationHistoryVO) request.getAttribute("OperationHistoryVO");
		historyVO.setActionCode("AR_F");
		String message = "[%s]가 [%s]글을 즐겨찾기에 등록했습니다.";

//		if (favoriteBiz.isExistFavoriteData(favoriteVO)) {
//			message = "[%s]가 [%s]글을 즐겨찾기를 삭제했습니다.";
//		}
		// message의 결과에 따라 반영 
		boolean isExistFavoriateData = favoriteBiz.isExistFavoriteData(favoriteVO);
		if (isExistFavoriateData) {
			message = "[%s]가 [%s]글을 즐겨찾기를 삭제했습니다.";
		}

		// 파라미터 #1 #2 
		historyVO.setDescription(String.format(message, member.getMemberId(), articleId+""));
		
		historyBiz.addHistory(historyVO);
		
		StringBuffer json = new StringBuffer();
		json.append("{");
		json.append("\"result\" : true");
		json.append(", \"isFavorite\" : " + isExistFavoriateData);
		json.append("}");
		
		PrintWriter out = response.getWriter();
		out.println(json.toString());
		out.flush();
		out.close();
		
	}
	

}
