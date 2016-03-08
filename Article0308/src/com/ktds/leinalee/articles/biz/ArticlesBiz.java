package com.ktds.leinalee.articles.biz;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ktds.leinalee.articles.dao.ArticlesDAO;
import com.ktds.leinalee.articles.vo.ArticlesVO;


public class ArticlesBiz {

	private ArticlesDAO articlesDAO;
	
	public ArticlesBiz() {
		articlesDAO = new ArticlesDAO();
	}
	
	public List<ArticlesVO> getArticleList(HttpServletRequest request) {
		
		// 1. 회원 정보를 가져온다.
		List<ArticlesVO> articleList = articlesDAO.getArticles();
		
		// 4. 결과를 보고한다.
		return articleList;
	}
	
}
