package com.ktds.leinalee.articles.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ktds.leinalee.Consts;
import com.ktds.leinalee.articles.vo.ArticlesVO;
import com.ktds.leinalee.util.xml.XML;

public class ArticlesDAO {

	public List<ArticlesVO> getArticles() {

		// 오라클 드라이버를 불러온다.
		loadOracleDriver();

		// Connection, Statement, ResultSet 변수를 생성한다.
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		List<ArticlesVO> articleList = new ArrayList<ArticlesVO>();

		try {

			// DB에 연결한다.
			conn = DriverManager.getConnection(Consts.DB_URL, Consts.DB_ARTICLE_USER, Consts.DB_ARTICLE_PASSWORD);

			// Query를 입력한다.
			String query = XML.getNodeString("//query/articles/getArticles/text()");
			stmt = conn.prepareStatement(query);

			// 쿼리를 실행하고 결과를 ResultSet에 넣는다.
			rs = stmt.executeQuery();

			// 새로운 VO 인스턴스 생성한다.
			ArticlesVO memberArticle = null;

			while (rs.next()) {

				memberArticle = new ArticlesVO();

				memberArticle.setArticleId(rs.getInt("ARTICLE_ID"));
				memberArticle.setTitle(rs.getString("TITLE"));
				memberArticle.setNickName(rs.getString("NICK_NAME"));
				memberArticle.setHits(rs.getInt("HITS"));
				memberArticle.setRecommends(rs.getInt("RECOMMENDS"));

				articleList.add(memberArticle);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			closeDB(conn, stmt, rs);
		}

		return articleList;
	}

	public List<ArticlesVO> getArticleByArticleTitle(String articleId) {

		// 오라클 드라이버를 불러온다.
		loadOracleDriver();

		// Connection, Statement, ResultSet 변수를 생성한다.
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		List<ArticlesVO> articleList = new ArrayList<ArticlesVO>();

		try {

			// DB에 연결한다.
			conn = DriverManager.getConnection(Consts.DB_URL, Consts.DB_ARTICLE_USER, Consts.DB_ARTICLE_PASSWORD);

			// Query를 입력한다.
			String query = XML.getNodeString("//query/articles/getArticleByArticleTitle/text()");
			stmt = conn.prepareStatement(query);

			// Query의 ?에 데이터 입력한다.
			stmt.setString(1, articleId);

			// 쿼리를 실행하고 결과를 ResultSet에 넣는다.
			rs = stmt.executeQuery();
			// insertCount = stmt.executeUpdate();

			// 새로운 VO 인스턴스 생성한다.
			ArticlesVO memberArticle = null;

			while (rs.next()) {

				memberArticle = new ArticlesVO();

				memberArticle.setArticleId(rs.getInt("ARTICLE_ID"));
				memberArticle.setTitle(rs.getString("TITLE"));
				memberArticle.setNickName(rs.getString("NICK_NAME"));
				memberArticle.setMemberId(rs.getString("MEMBER_ID"));
				memberArticle.setDescript(rs.getString("DESCRIPT"));
				memberArticle.setHits(rs.getInt("HITS"));
				memberArticle.setRecommends(rs.getInt("RECOMMENDS"));

				articleList.add(memberArticle);
			}

			// HITS UPDATE
			String queryForUpdate = XML.getNodeString("//query/articles/updateHits/text()");
			stmt.close();
			stmt = conn.prepareStatement(queryForUpdate);

			// Query의 ?에 데이터 입력한다.
			stmt.setString(1, articleId);

			// 쿼리를 실행하고 결과를 ResultSet에 넣는다.
			rs.close();
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			closeDB(conn, stmt, rs);
		}

		return articleList;

	}

	public List<ArticlesVO> recommendArticle(String articleId) {

		// 오라클 드라이버를 불러온다.
		loadOracleDriver();

		// Connection, Statement, ResultSet 변수를 생성한다.
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		List<ArticlesVO> articleList = new ArrayList<ArticlesVO>();

		try {

			// DB에 연결한다.
			conn = DriverManager.getConnection(Consts.DB_URL, Consts.DB_ARTICLE_USER, Consts.DB_ARTICLE_PASSWORD);

			// Query를 입력한다.
			String query = XML.getNodeString("//query/articles/getArticleByArticleTitle/text()");
			stmt = conn.prepareStatement(query);

			// Query의 ?에 데이터 입력한다.
			stmt.setString(1, articleId);

			// 쿼리를 실행하고 결과를 ResultSet에 넣는다.
			rs = stmt.executeQuery();
			// insertCount = stmt.executeUpdate();

			// 새로운 VO 인스턴스 생성한다.
			ArticlesVO memberArticle = null;

			while (rs.next()) {

				memberArticle = new ArticlesVO();

				memberArticle.setArticleId(rs.getInt("ARTICLE_ID"));
				memberArticle.setTitle(rs.getString("TITLE"));
				memberArticle.setNickName(rs.getString("NICK_NAME"));
				memberArticle.setMemberId(rs.getString("MEMBER_ID"));
				memberArticle.setDescript(rs.getString("DESCRIPT"));
				memberArticle.setHits(rs.getInt("HITS"));
				memberArticle.setRecommends(rs.getInt("RECOMMENDS"));

				articleList.add(memberArticle);
			}

			// HITS UPDATE
			String queryForRecommend = XML.getNodeString("//query/articles/recommendArticle/text()");
			stmt.close();
			stmt = conn.prepareStatement(queryForRecommend);

			// Query의 ?에 데이터 입력한다.
			stmt.setString(1, articleId);

			// 쿼리를 실행하고 결과를 ResultSet에 넣는다.
			rs.close();
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			closeDB(conn, stmt, rs);
		}

		return articleList;

	}

	// Load Oracle Driver
	private void loadOracleDriver() {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	// Close DB
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
