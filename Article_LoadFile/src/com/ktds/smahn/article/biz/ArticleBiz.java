package com.ktds.smahn.article.biz;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ktds.smahn.article.dao.ArticleDAO;
import com.ktds.smahn.article.vo.ArticleListVO;
import com.ktds.smahn.article.vo.ArticleSearchVO;
import com.ktds.smahn.article.vo.ArticleVO;
import com.ktds.smahn.member.vo.MemberVO;
import com.ktds.smahn.util.MultipartHttpServletRequest;
import com.ktds.smahn.util.web.Paging;

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
	public ArticleListVO getArticleList(ArticleSearchVO searchVO) {
		
		// 1. 전체 게시글의 수
		int allArticleCount = articleDAO.getAllArticleCount();
		// 1-1. 기본으로 페이지를 만들어준다. 
		Paging paging = new Paging();
		paging.setTotalArticleCount(allArticleCount);
		// 1-2. page 가져올 때 계산 쉽게 하기 위해서 page number은 0부터 시작 
		paging.setPageNumber(searchVO.getPageNO()+"");
		
		searchVO.setStartIndex(paging.getStartArticleNumber());
		searchVO.setEndIndex(paging.getEndArticleNumber());
		
		List<ArticleVO> articles = articleDAO.getAllArticles(searchVO);
		
		// 2. DAO로부터 받아온 결과를 출력
		ArticleListVO articleList = new ArticleListVO();
		articleList.setArticleList(articles);
		// 2-2. 페이지 추가 
		articleList.setPaging(paging);
		
		return articleList;
		
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
	public int addNewArticle(ArticleVO newArticle) {

//		// 1. 세션의 정보
//		HttpSession session = request.getSession();
//		MemberVO loginMember = (MemberVO) session.getAttribute("_MEMBER_");
//
//		// 2. 등록하는 정보를 받아오기
//		String title = request.getParameter("title");
//		String description = request.getParameter("descript");
//		description = description.replaceAll("\n", "<br/>");
//
//		//3. 들어온 정보를 확인하기 
////		if ( title == null ) {
////			response.sendRedirect("/Movie/addNewActor?errorCode=1");
////			return;
////		}
//		
//		// 4. 하나의 ArticleVO로 만들기
//		ArticleVO newArticleInfo = new ArticleVO();
//		newArticleInfo.setTitle(title);
//		newArticleInfo.setDescript(description);
//		newArticleInfo.setMemberId(loginMember.getMemberId());
//
//		articleDAO.addNewArticle(newArticleInfo);

		// <br/>태그를 replace시킨다.
		String description = newArticle.getDescript();
		description = description.replaceAll("\n", "<br/>");
		newArticle.setDescript(description);

		return articleDAO.addNewArticle(newArticle);
	}
	
	public boolean modifyArticle(MultipartHttpServletRequest multipartRequest) {
		// 1. 어떤 글인지 check 
		int articleId = Integer.parseInt(multipartRequest.getParameter("articleId"));
		
		// 1-1. 원본을 가져온다.
		ArticleVO originArticle = showDetail(articleId);
		
		// 2. 바뀐 내용이 있는지 check 
		// 바뀐게 없으면 0 , 바뀐게 있으면 1 
		int changeCount = 0; 
		String title = multipartRequest.getParameter("title");
		String descript = multipartRequest.getParameter("descript");
		
		ArticleVO chagedArticle = new ArticleVO();
		// 2-1. title이 다른가? 
		if (!originArticle.getTitle().equals(title)) {
			changeCount++;
			
			chagedArticle.setTitle(title);
		}
		
		descript = descript.replace("\n", "<br/>");

		// 2-2. descript가 다른가?
		if (!originArticle.getDescript().equals(descript)) {
			changeCount++;
			chagedArticle.setDescript(descript);
		}
		
				
		// 업데이트 되었다면 
		if ( changeCount == 0 ) {
			throw new RuntimeException("변경된 사항이 없습니다.");
		} 
		else {
			chagedArticle.setArticleId(articleId);
			return articleDAO.updateArticle(chagedArticle) > 0;
		}
		
	}

}
