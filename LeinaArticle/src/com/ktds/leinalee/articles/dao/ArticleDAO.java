package com.ktds.leinalee.articles.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ktds.leinalee.Const;
import com.ktds.leinalee.articles.vo.ArticleSearchVO;
import com.ktds.leinalee.articles.vo.ArticleVO;
import com.ktds.leinalee.util.xml.XML;

public class ArticleDAO {

	/**
	 * DB에서 전체 게시글 리스트 조회
	 * @param searchVO 
	 * 
	 * @return articles
	 */
	public List<ArticleVO> getAllArticlesOne(ArticleSearchVO searchVO) {

		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		List<ArticleVO> articles = new ArrayList<ArticleVO>();

		try {

			ArticleVO article = null;

			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);

			// article을 꺼내온다.
			String query = XML.getNodeString("//query/article/getAllArticlesOne/text()");
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, searchVO.getEndIndex());
			stmt.setInt(2, searchVO.getStartIndex());
					
			rs = stmt.executeQuery();

			while (rs.next()) {
				article = new ArticleVO();
				article.setArticleId(rs.getInt("ARTICLE_ID"));
				article.setHits(rs.getInt("HITS_NUM"));
				article.setRecommends(rs.getInt("LIKE_NUM"));
				article.setTitle(rs.getString("TITLE"));
				article.setNickName(rs.getString("NICK_NAME"));
				//fileCount를 넣는 이유는 한 게시글에 여러 파일을 등록하기 위해서
				article.setFileCount(rs.getInt("FILE_COUNT"));
				
				articles.add(article);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);

		} finally {
			closeDB(conn, stmt, rs);
		}

		return articles;
	}

	/**
	 * ArticleId에 해당하는 상세정보 조회
	 * 
	 * @param articleId
	 * @return article
	 */
	public ArticleVO getOneArticleByArticleId(int articleId) {

		ArticleVO article = new ArticleVO();

		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/article/getOneArticleByArticleId/text()");
			stmt = conn.prepareStatement(query);

			stmt.setInt(1, articleId);

			rs = stmt.executeQuery();

			int hitCount = 0;

			if (rs.next()) {
				article.setArticleId(rs.getInt("ARTICLE_ID"));
				article.setTitle(rs.getString("TITLE"));
				article.setDescript(rs.getString("CONTENTS"));
				article.setNickName(rs.getString("NICK_NAME"));
				// Hits를 꺼내서 +1 해준 뒤
				hitCount = rs.getInt("HITS_NUM") + 1;
				// + 1 한 값을 articleVO에 입력
				article.setHits(hitCount);
				article.setRecommends(rs.getInt("LIKE_NUM"));
				article.setMemberId(rs.getString("MEMBER_ID"));
			}

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			closeDB(conn, stmt, rs);
		}

		return article;
	}

	/**
	 * DB에서 조회수 +1 update
	 * 
	 * @param articleId
	 */
	public void updateHitsByArticleId(int articleId) {

		int insertCount = 0;
		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;

		try {

			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/article/updateHitsByArticleId/text()");
			stmt = conn.prepareStatement(query);
			System.out.println(query);

			stmt.setInt(1, articleId);

			insertCount = stmt.executeUpdate();

			if (insertCount > 0) {
				stmt.close();
				System.out.println("업데이트 성공");
			}

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			closeDB(conn, stmt, null);
		}

	}

	/**
	 * DB에서 추천수 +1 update
	 * 
	 * @param articleId
	 */
	public void updateRecommendsByArticleId(int articleId) {

		int insertCount = 0;
		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;

		try {

			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/article/updateRecommendsByArticleId/text()");
			stmt = conn.prepareStatement(query);
			System.out.println(query);

			stmt.setInt(1, articleId);

			insertCount = stmt.executeUpdate();

			if (insertCount > 0) {
				stmt.close();
				System.out.println("업데이트 성공");
			}

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			closeDB(conn, stmt, null);
		}

	}

	/**
	 * DB에서 ARTICLE_ID 삭제
	 * 
	 * @param articleId
	 */
	public void deleteArticleByArticleId(int articleId) {

		int insertCount = 0;
		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;

		try {

			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/article/deleteArticleByArticleId/text()");
			stmt = conn.prepareStatement(query);
			System.out.println(query);

			stmt.setInt(1, articleId);

			insertCount = stmt.executeUpdate();

			if (insertCount > 0) {
				stmt.close();
				System.out.println("게시글 삭제 성공");
			}

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			closeDB(conn, stmt, null);
		}

	}

	/**
	 * 새로운 게시글 등록
	 * 
	 * @param articleId
	 */
	public int addNewArticle(ArticleVO newArticle) {

		int insertCount = 0;
		int articleId = 0; 
		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/article/addNewArticle/text()");
			stmt = conn.prepareStatement(query);
			System.out.println(query);

			stmt.setString(1, newArticle.getMemberId());
			stmt.setString(2, newArticle.getTitle());
			stmt.setString(3, newArticle.getDescript());
			stmt.setInt(4, newArticle.getBoardId());

			insertCount = stmt.executeUpdate();

			if (insertCount > 0) {
				stmt.close();
				System.out.println("게시글 등록 성공");
				
				String queryForArticleId = XML.getNodeString("//query/article/getCurrentArticleId/text()");
				stmt = conn.prepareStatement(queryForArticleId);
				rs = stmt.executeQuery();

				if (rs.next()) {
					articleId = rs.getInt(1);
					return articleId;
				}
			}

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			closeDB(conn, stmt, rs);
		}

		return articleId;
	}

	public int updateArticle(ArticleVO chagedArticle) {

		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;

		try {

			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);

			String query = "";
			if (chagedArticle.getTitle() != null && chagedArticle.getTitle().length() > 0) {
				if (chagedArticle.getDescript() != null && chagedArticle.getDescript().length() > 0) {
					query = XML.getNodeString("//query/article/updateArticle/text()");
				} else {
					query = XML.getNodeString("//query/article/updateArticleOnlyTitle/text()");
				}
			} else {
				if (chagedArticle.getDescript() != null && chagedArticle.getDescript().length() > 0) {
					query = XML.getNodeString("//query/article/updateArticleOnlyDescript/text()");
				}
			}

			// 1. 쿼리를 선택할 때, title/descript/BOTH인지 check하고 적용시키기

			stmt = conn.prepareStatement(query);
			if (chagedArticle.getTitle() != null && chagedArticle.getTitle().length() > 0) {
				if (chagedArticle.getDescript() != null && chagedArticle.getDescript().length() > 0) {
					stmt.setString(1, chagedArticle.getTitle());
					stmt.setString(2, chagedArticle.getDescript());
					stmt.setInt(3, chagedArticle.getArticleId());
				} else {
					stmt.setString(1, chagedArticle.getTitle());
					stmt.setInt(2, chagedArticle.getArticleId());
				}
			} else {
				if (chagedArticle.getDescript() != null && chagedArticle.getDescript().length() > 0) {
					stmt.setString(1, chagedArticle.getDescript());
					stmt.setInt(2, chagedArticle.getArticleId());
				}
			}

			return stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			closeDB(conn, stmt, null);
		}
	}

	/**
	 * 게시물의 총 개수를 가져오는 메소드
	 * 
	 * @return
	 */
	public int getAllArticleCount() {
		
		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);

			String query = XML.getNodeString("//query/article/getAllArticleCount/text()");
			stmt = conn.prepareStatement(query);
			rs = stmt.executeQuery();

			int articleCount = 0;
			rs.next();
			articleCount = rs.getInt(1);
			
			return articleCount;
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			closeDB(conn, stmt, rs);
		}

	}

	private void loadOracleDriver() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	private void closeDB(Connection conn, PreparedStatement stmt, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}

	}

}
