package com.ktds.smahn.article.biz;

import java.util.List;

import com.ktds.smahn.article.dao.ArticleDAO;
import com.ktds.smahn.article.vo.ArticleVO;
import com.ktds.smahn.member.vo.MemberVO;

public class ArticleBiz {

	private ArticleDAO articleDAO;
	
	public ArticleBiz(){
		articleDAO = new ArticleDAO();
	}
	
	/**
	 * 게시물 리스트 조회
	 * @return
	 */
	public List<ArticleVO> showList(){		
		List<ArticleVO> articles = articleDAO.getAllArticles();		
		return articles;
	}

	/**
	 * 상세 정보 조회
	 * @param articleId
	 * @return
	 */
	public ArticleVO showDetail(int articleId) {		
		ArticleVO article = articleDAO.getOneArticleByArticleId(articleId);
		return article;
	}
	
	/**
	 * 조회수 증가
	 * @param articleId
	 */
	public void increaseHits(int articleId){	
		articleDAO.updateHitsByArticleId(articleId);
	}
	
	/**
	 * 추천수 증가
	 * @param articleId
	 */
	public void increaseRecommends(int articleId) {		
		articleDAO.updateRecommendsByArticleId(articleId);		
	}
	
	/**
	 * 게시글 삭제
	 * @param articleId
	 */
	public void deleteArticle(int articleId) {
		articleDAO.deleteArticleByArticleId(articleId);
	}
	
	/**
	 * 글 작성자가 맞는지 확인 > 글 삭제 
	 * @param loginMember
	 * @param targetArticleInfo
	 * @return
	 */
	public void isEqual(MemberVO loginMember, ArticleVO targetArticleInfo) {
		
		if (loginMember.getMemberId().equals(targetArticleInfo.getMemberId())) {
			deleteArticle(targetArticleInfo.getArticleId());	
		}
	}
	
}
