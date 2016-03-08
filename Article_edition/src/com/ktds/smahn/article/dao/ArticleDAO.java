package com.ktds.smahn.article.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ktds.smahn.Const;
import com.ktds.smahn.article.vo.ArticleVO;
import com.ktds.smahn.util.xml.XML;

public class ArticleDAO {
	
	/**
	 * DB에서 전체 게시글 리스트 조회
	 * @return articles
	 */
	public List<ArticleVO> getAllArticles() {
		
		loadOracleDriver();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		List<ArticleVO> articles = new ArrayList<ArticleVO>();
		
		try {
			
			ArticleVO article = null;

			conn = DriverManager.getConnection(Const.DB_URL, Const.DB_ID, Const.DB_PASSWORD);

			// article을 꺼내온다.
			String query = XML.getNodeString("//query/article/getAllArticles/text()");
			stmt = conn.prepareStatement(query);

			rs = stmt.executeQuery();

				while( rs.next() ){
				article = new ArticleVO();
				article.setArticleId(rs.getInt("ARTICLE_ID"));
				article.setHits(rs.getInt("HITS"));
				article.setRecommends(rs.getInt("RECOMMENDS"));
				article.setTitle(rs.getString("TITLE"));
				article.setNickName(rs.getString("NICK_NAME"));
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

			if ( rs.next() ) {
				article.setArticleId(rs.getInt("ARTICLE_ID"));
				article.setTitle(rs.getString("TITLE"));
				article.setDescript(rs.getString("DESCRIPT"));
				article.setNickName(rs.getString("NICK_NAME"));
				// Hits를 꺼내서 +1 해준 뒤
				hitCount = rs.getInt("HITS") + 1;
				// + 1 한 값을 articleVO에 입력
				article.setHits(hitCount);
				article.setRecommends(rs.getInt("RECOMMENDS"));
				article.setMemberId(rs.getString("MEMBER_ID"));
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			closeDB(conn, stmt, rs);
		}
		
		return article;
	}

	/**
	 * DB에서 조회수 +1 update
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
			
			if( insertCount > 0 ){
				stmt.close();
				System.out.println("업데이트 성공");
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			closeDB(conn, stmt, null);
		}
		
	}

	/**
	 * DB에서 추천수 +1 update 
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
			
			if( insertCount > 0 ){
				stmt.close();
				System.out.println("업데이트 성공");
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			closeDB(conn, stmt, null);
		}
		
	}

	/**
	 * DB에서 ARTICLE_ID 삭제 
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
			
			if( insertCount > 0 ){
				stmt.close();
				System.out.println("게시글 삭제 성공");
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			closeDB(conn, stmt, null);
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
