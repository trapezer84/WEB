package com.ktds.smahn.article.biz;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ktds.smahn.article.dao.ArticleDAO;
import com.ktds.smahn.article.vo.ArticleVO;
import com.ktds.smahn.member.vo.MemberVO;

public class ArticleBiz {

	private ArticleDAO articleDAO;

	public ArticleBiz() {
		articleDAO = new ArticleDAO();
	}

	/**
	 * 게시물 리스트 조회
	 * 
	 * @return
	 */
	public List<ArticleVO> showList() {
		List<ArticleVO> articles = articleDAO.getAllArticles();
		return articles;
	}

	/**
	 * 상세 정보 조회
	 * 
	 * @param articleId
	 * @return
	 */
	public ArticleVO showDetail(int articleId) {
		ArticleVO article = articleDAO.getOneArticleByArticleId(articleId);
		return article;
	}

	/**
	 * 조회수 증가
	 * 
	 * @param articleId
	 */
	public void increaseHits(int articleId) {
		articleDAO.updateHitsByArticleId(articleId);
	}

	/**
	 * 추천수 증가
	 * 
	 * @param articleId
	 */
	public void increaseRecommends(int articleId) {
		articleDAO.updateRecommendsByArticleId(articleId);
	}

	/**
	 * 게시글 삭제
	 * 
	 * @param articleId
	 */
	public void deleteArticle(int articleId) {
		articleDAO.deleteArticleByArticleId(articleId);
	}

	/**
	 * 글 작성자가 맞는지 확인 > 글 삭제
	 * 
	 * @param loginMember
	 * @param targetArticleInfo
	 * @return
	 */
	public void isEqual(MemberVO loginMember, ArticleVO targetArticleInfo) {

		if (loginMember.getMemberId().equals(targetArticleInfo.getMemberId())) {
			deleteArticle(targetArticleInfo.getArticleId());
		}
	}

	/**
	 * 새로운 게시글 등록
	 * 
	 * @param newArticle
	 */
	public void addNewArticle(HttpServletRequest request) {

		// 1. 세션의 정보
		HttpSession session = request.getSession();
		MemberVO loginMember = (MemberVO) session.getAttribute("_MEMBER_");

		// 2. 등록하는 정보를 받아오기
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		description = description.replaceAll("\n", "<br/>");

		//3. 들어온 정보를 확인하기 
//		if ( title == null ) {
//			response.sendRedirect("/Movie/addNewActor?errorCode=1");
//			return;
//		}
		
		// 4. 하나의 ArticleVO로 만들기
		ArticleVO newArticleInfo = new ArticleVO();
		newArticleInfo.setTitle(title);
		newArticleInfo.setDescript(description);
		newArticleInfo.setMemberId(loginMember.getMemberId());

		articleDAO.addNewArticle(newArticleInfo);

		//		// <br/>태그를 replace시킨다.
//		String description = newArticle.getDescript();
//		description = description.replaceAll("\n", "<br/>");
//		newArticle.setDescript(description);

//		articleDAO.addNewArticle(newArticle);
	}

}
